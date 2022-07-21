/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONUtil;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.constans.DiskCloudConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.*;
import com.skyeye.eve.dao.FileConsoleDao;
import com.skyeye.eve.entity.diskcloud.filerecycle.FileRecycleQueryDo;
import com.skyeye.eve.entity.diskcloud.fileshare.FileShareQueryDo;
import com.skyeye.eve.service.FileConsoleService;
import com.skyeye.exception.CustomException;
import com.skyeye.jedis.JedisClientService;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName: FileConsoleServiceImpl
 * @Description: 文件管理系统服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:21
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class FileConsoleServiceImpl implements FileConsoleService {

    @Autowired
    private FileConsoleDao fileConsoleDao;

    @Autowired
    public JedisClientService jedisClient;

    @Autowired
    private RedisCache redisCache;

    /**
     * 文件上传时保存文件的路径
     */
    private static final Integer FILE_PATH_TYPE = Constants.FileUploadPath.FILE_CONSOLE.getType()[0];

    @Value("${IMAGES_PATH}")
    private String tPath;

    @Value("${server.port}")
    private String sysPort;

    private static final Logger LOGGER = LoggerFactory.getLogger(FileConsoleServiceImpl.class);

    /**
     * 删除指定文件夹或文件的父目录在redis中的缓存信息
     *
     * @param id
     */
    public void deleteParentFolderRedis(String id) {
        //获取文件或者文件夹父id
        Map<String, Object> fileParent = fileConsoleDao.quertWinFileOrFolderParentById(id);
        if (fileParent != null && !fileParent.isEmpty()) {
            String[] str = fileParent.get("parentId").toString().split(",");
            //删除父目录的redis的key
            jedisClient.delKeys(DiskCloudConstants.SYS_FILE_MATION_FOLDER_LIST_MATION + str[str.length - 1] + "*");
        }
    }

    /**
     * 删除文件
     *
     * @param fileAddress   文件地址
     * @param fileThumbnail 文件缩略图地址
     * @param fileType      文件类型
     */
    public void deleteFileByMation(String fileAddress, String fileThumbnail, String fileType) {
        FileUtil.deleteFile(fileAddress);
        FileUtil.deleteFile(fileThumbnail);//删除缩略图
        if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileType, 5)) {//ace文件
            FileUtil.deleteFile(fileAddress.substring(0, fileAddress.lastIndexOf(".")) + ".pdf");//删除ace转换文件
        }
    }

    /**
     * 根据当前用户获取目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFileFolderByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 父目录id
        String parentId = map.get("parentId").toString();
        if (ToolUtil.isBlank(parentId) || "0".equals(parentId)) {
            String cacheKey = DiskCloudConstants.getSysFileMationFolderListMation(StringUtils.EMPTY, StringUtils.EMPTY);
            // 加载一级目录，缓存30天
            List<Map<String, Object>> beans = redisCache.getList(cacheKey, key ->
                DiskCloudConstants.getFileConsoleISDefaultFolder(), RedisConstants.THIRTY_DAY_SECONDS);
            outputObject.setBeans(beans);
        } else {
            // 加载子目录
            String userId = inputObject.getLogParams().get("id").toString();
            map.put("folderType", this.getFolderType(parentId));
            String cacheKey = DiskCloudConstants.getSysFileMationFolderListMation(parentId, userId);
            List<Map<String, Object>> beans = redisCache.getList(cacheKey, key -> {
                map.put("userId", userId);
                return fileConsoleDao.queryFileFolderByUserIdAndParentId(map);
            }, RedisConstants.THIRTY_DAY_SECONDS);
            outputObject.setBeans(beans);
        }
    }

    private String getFolderType(String folderId) {
        if ("3".equals(folderId)) {
            // 企业网盘
            return DiskCloudConstants.FOLDER_TYPE.ENTERPRISE_NETWORK_DISK.getFolderType();
        } else {
            Map<String, Object> parentFolder = fileConsoleDao.queryFolderMationById(folderId);
            if (parentFolder != null && !parentFolder.isEmpty()) {
                if (parentFolder.get("parentId").toString().indexOf("3,") == 0) {
                    // 企业网盘
                    return DiskCloudConstants.FOLDER_TYPE.ENTERPRISE_NETWORK_DISK.getFolderType();
                } else {
                    // 私人
                    return DiskCloudConstants.FOLDER_TYPE.PRIVATE_NETWORK_DISK.getFolderType();
                }
            } else {
                // 私人
                return DiskCloudConstants.FOLDER_TYPE.PRIVATE_NETWORK_DISK.getFolderType();
            }
        }
    }

    /**
     * 添加目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertFileFolderByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String parentId = map.get("parentId").toString();
        if ("1".equals(parentId) || "2".equals(parentId) || "3".equals(parentId)) {
            // 收藏夹   我的文档   企业网盘
            map.put("parentId", parentId + ",");
        } else {
            // 根据当前所属目录查询该目录的父id
            Map<String, Object> folder = fileConsoleDao.queryFolderMationById(parentId);
            if (!ObjectUtils.isEmpty(folder)) {
                map.put("parentId", folder.get("parentId").toString() + parentId + ",");
            } else {
                outputObject.setreturnMessage("错误的文件目录编码！");
                return;
            }
        }
        DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
        map.put("logoPath", DiskCloudConstants.SYS_FILE_CONSOLE_IS_FOLDER_LOGO_PATH);
        fileConsoleDao.insertFileFolderByUserId(map);
        // 删除父目录的redis的key
        jedisClient.delKeys(DiskCloudConstants.SYS_FILE_MATION_FOLDER_LIST_MATION + parentId + "*");
        outputObject.setBean(map);
    }

    /**
     * 获取这个目录下的所有文件+目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFilesListByFolderId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        String folderId = map.get("folderId").toString();
        map.put("folderType", this.getFolderType(folderId));
        this.setOrderByParams(map);
        List<Map<String, Object>> beans = fileConsoleDao.queryFilesListByFolderId(map);
        for (Map<String, Object> bean : beans) {
            if (!"folder".equals(bean.get("fileType").toString())) {
                // 不是文件夹
                String size = BytesUtil.sizeFormatNum2String(Long.parseLong(bean.get("fileSize").toString()));
                bean.put("fileSize", size);
            }
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 设置排序字段
     *
     * @param map
     */
    private void setOrderByParams(Map<String, Object> map) {
        String orderBy = map.get("orderBy").toString();
        if (ToolUtil.isBlank(orderBy)) {
            map.put("orderByStr", " k.orderBy ASC, k.`name` ASC");
        } else {
            if ("1".equals(orderBy)) {
                map.put("orderByStr", " k.`name` ASC");
            } else if ("2".equals(orderBy)) {
                map.put("orderByStr", " k.createTime DESC");
            } else if ("3".equals(orderBy)) {
                map.put("orderByStr", " k.orderBy ASC, k.`name` ASC");
            } else if ("4".equals(orderBy)) {
                map.put("orderByStr", " k.fileSize DESC");
            }
        }
        map.remove("orderBy");
    }

    /**
     * 删除目录以及目录下的所有文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteFileFolderById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        //获取要删除的文件
        String fileList = map.get("fileList").toString();
        List<Map<String, Object>> array = JSONUtil.toList(fileList, null);
        if (ObjectUtils.isEmpty(array)) {
            outputObject.setreturnMessage("请选择要删除的数据");
            return;
        }
        //获取当前登录人id
        String userId = inputObject.getLogParams().get("id").toString();
        //文件访问基础路径
        String basePath = tPath.replace("images", "");
        for (int i = 0; i < array.size(); i++) {
            //获取id和fileType
            String id = array.get(i).get("rowId").toString();
            String fileType = array.get(i).get("fileType").toString();
            Map<String, Object> createUser = fileConsoleDao.queryThisFileCreaterByFileId(id);//获取文件创建人
            //如果当前登陆人是文件创建人则执行删除操作
            if (createUser != null && !createUser.isEmpty() && userId.equals(createUser.get("createId").toString())) {
                //删除父目录在redis的缓存信息
                deleteParentFolderRedis(id);
                if ("folder".equals(fileType)) {
                    // 删除自身目录
                    fileConsoleDao.deleteFileFolderById(id);
                    // 目录下的子文件
                    List<Map<String, Object>> files = fileConsoleDao.queryFilesByFolderId(id);
                    for (Map<String, Object> file : files) {
                        //删除文件
                        deleteFileByMation(basePath + file.get("fileAddress").toString(), basePath + file.get("fileThumbnail").toString(), file.get("fileType").toString());
                    }
                    fileConsoleDao.deleteFilesByFolderId(id);//删除子文件
                    fileConsoleDao.deleteFolderChildByFolderId(id);//删除子文件夹
                } else {//操作文件表
                    Map<String, Object> fileMation = fileConsoleDao.queryFilePaperPathById(id);
                    if (fileMation != null && !fileMation.isEmpty()) {
                        //删除文件
                        deleteFileByMation(basePath + fileMation.get("fileAddress").toString(), basePath + fileMation.get("fileThumbnail").toString(), fileMation.get("fileType").toString());
                        fileConsoleDao.deleteFilePaperById(id);
                    }
                }
            }
        }
    }

    /**
     * 编辑目录名称
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editFileFolderById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("userId", inputObject.getLogParams().get("id"));
        //删除父目录在redis的缓存信息
        deleteParentFolderRedis(map.get("id").toString());
        if ("folder".equals(map.get("fileType").toString())) {//操作目录表
            fileConsoleDao.editFileFolderById(map);
        } else {//操作文件表
            fileConsoleDao.editFilePaperNameById(map);
        }
    }

    /**
     * 上传文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(PutObject.getRequest().getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(PutObject.getRequest())) {
            Map<String, Object> user = inputObject.getLogParams();
            String userId = user.get("id").toString();
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) PutObject.getRequest();
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/" + userId;
            String trueFileName = "";
            String fileName = "";
            while (iter.hasNext()) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                fileName = file.getOriginalFilename();// 文件名称
                //得到文件扩展名
                String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if (file != null) {
                    FileUtil.createDirs(basePath);
                    // 自定义的文件名称
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;
                    String path = basePath + "/" + newFileName;
                    // 上传
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        throw new CustomException(e);
                    }
                    //初始化文件对象内容
                    trueFileName = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + "/" + userId + "/" + newFileName;
                    map.put("fileType", fileExtName);//文件类型
                    map.put("fileSizeType", "bytes");//文件大小单位
                    map.put("fileAddress", trueFileName);//文件地址
                    map.put("fileThumbnail", "-1");
                    DataCommonUtil.setCommonData(map, userId);
                    String folderId = getThisFolderChildParentId(map.get("folderId").toString());
                    map.put("folderId", folderId);
                    fileConsoleDao.insertUploadFileByUserId(map);
                }
            }
        }
    }

    /**
     * 获取当前文件夹子文件的parentId
     *
     * @param folderId 文件夹id
     * @return 当前文件夹子文件的parentId
     */
    private String getThisFolderChildParentId(String folderId) {
        // 删除父目录的redis的key
        jedisClient.delKeys(DiskCloudConstants.SYS_FILE_MATION_FOLDER_LIST_MATION + folderId + "*");
        if ("1".equals(folderId) || "2".equals(folderId) || "3".equals(folderId)) {
            // 收藏夹   我的文档   企业网盘
            folderId = String.format(Locale.ROOT, "%s", folderId);
        } else {
            // 根据当前所属目录查询该目录的父id
            Map<String, Object> folder = fileConsoleDao.queryFolderMationById(folderId);
            if (!ObjectUtils.isEmpty(folder)) {
                folderId = String.format(Locale.ROOT, "%s%s", folder.get("parentId").toString(), folderId);
            } else {
                throw new CustomException("该文件夹不存在.");
            }
        }
        folderId = String.format(Locale.ROOT, "%s,", folderId);
        return folderId;
    }

    /**
     * 上传文件合并块
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        List<Map<String, Object>> beans = fileConsoleDao.queryUploadFileChunksByMd5(map);
        List<File> fileList = new ArrayList<>();
        for (Map<String, Object> bean : beans) {
            File f = new File(tPath.replace("images", "") + bean.get("fileAddress").toString());
            fileList.add(f);
        }
        String fileName = map.get("name").toString();
        String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();//文件后缀
        String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
        String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE);
        String path = basePath + "/" + userId + "/" + newFileName;//文件路径
        FileChannel outChnnel = null;
        try {
            File outputFile = new File(path);
            // 创建文件
            outputFile.createNewFile();
            // 输出流
            outChnnel = new FileOutputStream(outputFile).getChannel();
            // 合并
            FileChannel inChannel;
            for (File file : fileList) {
                inChannel = new FileInputStream(file).getChannel();
                inChannel.transferTo(0, inChannel.size(), outChnnel);
                inChannel.close();
                //删除分片
                file.delete();
            }
        } catch (Exception ee) {
            throw new CustomException(ee);
        } finally {
            FileUtil.close(outChnnel);
        }
        fileConsoleDao.deleteUploadFileChunksByMd5(map);
        //初始化文件对象内容
        String trueFileName = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId + "/" + newFileName;
        map.put("fileType", fileExtName);//文件类型
        map.put("fileSizeType", "bytes");//文件大小单位
        map.put("fileAddress", trueFileName);//文件地址
        map.put("chunk", 0);//文件整合完之后的序号 默认0
        map.put("chunkSize", map.get("size"));//文件整合之后的大小
        map.put("md5", "");//文件整合之后的编码
        DataCommonUtil.setCommonData(map, userId);
        if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {
            // 图片
            map.put("fileThumbnail", trueFileName);//文件缩略图地址
        } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {
            // 电子书
            String picName = String.valueOf(System.currentTimeMillis()) + ".jpg";
            String newFilename = basePath + "/" + userId + "/" + picName;
            writeAndReadQpubFileThumbnail(path, newFilename);

            map.put("fileThumbnail", Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId + "/" + picName);//文件缩略图地址
        } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 2)) {
            // office文件缩略图地址
            map.put("fileThumbnail", DiskCloudConstants.FileMation.getIconByFileExt(fileExtName));
        } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 5)) {
            // ace文件缩略图地址
            map.put("fileThumbnail", DiskCloudConstants.FileMation.getIconByFileExt(fileExtName));
        } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {
            // 视频
            String ffmpegGPath = tPath + "/util/ffmpeg.exe";//工具路径
            String fileThumbnail = String.valueOf(System.currentTimeMillis()) + ".jpg";
            FileUtil.createDirs(basePath + "/" + userId + "/ffmpeg/");
            if (ToolUtil.take(path, basePath + "/" + userId + "/ffmpeg/" + fileThumbnail, ffmpegGPath)) {
                map.put("fileThumbnail", Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId + "/ffmpeg/" + fileThumbnail);
            } else {
                FileUtil.deleteFile(path);
                outputObject.setreturnMessage("上传失败。");
                return;
            }
        } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 4)) {
            // 压缩包
            map.put("fileThumbnail", "../../assets/images/rar.png");//文件缩略图地址
        } else {
            // 其他
            map.put("fileThumbnail", "../../assets/images/other-icon.png");//文件缩略图地址
        }
        String folderId = getThisFolderChildParentId(map.get("folderId").toString());
        map.put("folderId", folderId);
        fileConsoleDao.insertUploadFileByUserId(map);
    }

    /**
     * 文件分块上传检测是否上传
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = fileConsoleDao.queryUploadFileChunksByChunkMd5(map);
        if (bean != null && !bean.isEmpty()) {
            String fileAddress = tPath.replace("images", "") + bean.get("fileAddress").toString();
            File checkFile = new File(fileAddress);
            String chunkSize = map.get("chunkSize").toString();
            if (checkFile.exists() && checkFile.length() == Integer.parseInt(chunkSize)) {
            } else {
                fileConsoleDao.deleteUploadFileChunksByChunkMd5(map);
                outputObject.setreturnMessage("文件上传失败");
            }
        } else {
            outputObject.setreturnMessage("文件上传失败");
        }
    }

    /**
     * 文件获取路径
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryUploadFilePathById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = fileConsoleDao.queryUploadFilePathById(map);
        outputObject.setBean(bean);
    }

    /**
     * office文件编辑
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editUploadOfficeFileById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        int status = Integer.parseInt(map.get("status").toString());
        // 当我们关闭编辑窗口后，十秒钟左右onlyoffice会将它存储的我们的编辑后的文件，，此时status = 2
        if (status == 2 || status == 3) {//MustSave, Corrupted
            map.put("key", map.get("key").toString().split("-")[0]);
            try {
                // 新文件地址
                URL url = new URL(map.get("url").toString());
                java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                if (stream == null) {
                    outputObject.setreturnMessage("Stream is null");
                    return;
                }
                // 从请求中获取要覆盖的文件参数定义"path"
                Map<String, Object> fileMation = fileConsoleDao.queryUploadFilePathByKey(map);
                String fileAddress = tPath.replace("images", "") + fileMation.get("fileAddress").toString();
                File savedFile = new File(fileAddress);
                try (FileOutputStream out = new FileOutputStream(savedFile)) {
                    int read;
                    final byte[] bytes = new byte[1024];
                    while ((read = stream.read(bytes)) != -1) {
                        out.write(bytes, 0, read);
                    }
                    out.flush();
                }
                map.put("updateTime", DateUtil.getTimeAndToString());
                fileConsoleDao.editFileUpdateTimeByKey(map);
                connection.disconnect();
                outputObject.setErroCode(1);
            } catch (Exception e) {
                throw new CustomException(e);
            }
        }
    }

    /**
     * 根据当前用户获取总文件大小
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllFileSizeByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        Map<String, Object> bean = fileConsoleDao.queryAllFileSizeByUserId(map);
        if (!ObjectUtils.isEmpty(bean)) {
            String size = BytesUtil.sizeFormatNum2String(Long.parseLong(bean.get("fileSize").toString()));
            map.put("size", size);
        } else {
            map.clear();
            map.put("size", "0KB");
        }
        outputObject.setBean(map);
    }

    /**
     * 加入回收站
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertFileCatalogToRecycleById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        map.put("userId", userId);
        Map<String, Object> bean = fileConsoleDao.queryFileCatalogByUserIdAndId(map);
        if (!ObjectUtils.isEmpty(bean)) {
            //删除父目录在redis的缓存信息
            deleteParentFolderRedis(map.get("id").toString());

            map.clear();
            map.put("fileId", bean.get("id"));
            map.put("fileName", bean.get("fileName"));
            map.put("fileType", bean.get("fileType"));
            DataCommonUtil.setCommonData(map, userId);
            fileConsoleDao.insertFileCatalogToRecycleById(map);
        } else {
            outputObject.setreturnMessage("该文件不存在或者不属于您该账户下的文件。");
        }
    }

    /**
     * 我的回收站
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFileRecycleBinByUserId(InputObject inputObject, OutputObject outputObject) {
        FileRecycleQueryDo fileRecycleQuery = inputObject.getParams(FileRecycleQueryDo.class);
        fileRecycleQuery.setUserId(inputObject.getLogParams().get("id").toString());
        Page pages = PageHelper.startPage(fileRecycleQuery.getPage(), fileRecycleQuery.getLimit());
        List<Map<String, Object>> beans = fileConsoleDao.queryFileRecycleBinByUserId(fileRecycleQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 回收站内容还原
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteFileRecycleBinById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("userId", inputObject.getLogParams().get("id"));
        Map<String, Object> mation = fileConsoleDao.queryFileRecycleBinById(map);
        if (!CollectionUtils.isEmpty(mation)) {
            if ("1".equals(mation.get("fileType").toString())) {//文件夹
                fileConsoleDao.updateFileFolderRecycleBinById(mation);
            } else if ("2".equals(mation.get("fileType").toString())) {//文件
                fileConsoleDao.updateFileRecycleBinById(mation);
            } else {
                outputObject.setreturnMessage("参数错误。");
                return;
            }
            fileConsoleDao.deleteFileRecycleBinById(map);

            //删除父目录在redis的缓存信息
            deleteParentFolderRedis(map.get("id").toString());
        } else {
            outputObject.setreturnMessage("该文件不在您的操作范围之内。");
        }
    }

    /**
     * 文件分享
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertFileToShareById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> mation = fileConsoleDao.queryFileMationByIdAndUserId(map);
        if (!CollectionUtils.isEmpty(mation)) {
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            map.put("fileType", mation.get("fileType"));
            map.put("fileId", mation.get("id"));
            map.put("fileName", mation.get("fileName"));
            map.put("shareUrl", DiskCloudConstants.getFileShareUrl(map.get("id").toString()));//链接地址
            map.put("shareCode", ToolUtil.randomStr(0, 20));//链接code
            map.put("state", 1);//正常
            if ("2".equals(map.get("shareType").toString())) {//有提取码
                map.put("sharePassword", ToolUtil.getFourWord());
            }
            fileConsoleDao.insertFileToShareById(map);
            outputObject.setBean(map);
        } else {
            outputObject.setreturnMessage("该文件不在您的操作范围之内。");
        }
    }

    /**
     * 文件分享列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryShareFileListByUserId(InputObject inputObject, OutputObject outputObject) {
        FileShareQueryDo fileShareQuery = inputObject.getParams(FileShareQueryDo.class);
        fileShareQuery.setUserId(inputObject.getLogParams().get("id").toString());
        Page pages = PageHelper.startPage(fileShareQuery.getPage(), fileShareQuery.getLimit());
        List<Map<String, Object>> beans = fileConsoleDao.queryShareFileListByUserId(fileShareQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 删除文件分享外链
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteShareFileById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        fileConsoleDao.deleteShareFileById(map);
    }

    /**
     * 文件共享输入密码时获取文件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryShareFileMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = fileConsoleDao.queryShareFileMationById(map);
        if (bean != null && !bean.isEmpty()) {
            String userName = bean.get("userName").toString();
            StringBuilder sb = new StringBuilder(userName);
            if (userName.length() <= 3) {
                // 小于等于三个字
                userName = sb.replace(0, 1, "*").toString();
            } else if (userName.length() >= 4) {
                // 大于等于四个字
                userName = sb.replace(0, 2, "**").toString();
            }
            bean.put("userName", userName);
            outputObject.setBean(bean);
        }
    }

    /**
     * 文件共享输入密码时校验
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryShareFileMationCheckById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = fileConsoleDao.queryShareFileMationCheckById(map);
        if (bean != null && !bean.isEmpty()) {
            if (!bean.get("sharePassword").toString().toLowerCase().equals(map.get("sharePassword").toString().toLowerCase())) {
                // 密码输入正确
                outputObject.setreturnMessage("提取码输入错误");
                return;
            }
            outputObject.setBean(bean);
        }
    }

    /**
     * 获取分享文件基础信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryShareFileBaseMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> baseMation = fileConsoleDao.queryShareFileBaseMationById(map);
        outputObject.setBean(baseMation);
    }

    /**
     * 根据父id获取该id下分享的文件和文件夹
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryShareFileListByParentId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans;
        if ("-1".equals(map.get("folderId").toString())) {
            // 加载初始目录
            beans = fileConsoleDao.queryShareFileFirstListByParentId(map);
        } else {
            // 加载子目录
            beans = fileConsoleDao.queryShareFileListByParentId(map);
        }
        for (Map<String, Object> bean : beans) {
            if (!"folder".equals(bean.get("fileType").toString())) {
                // 不是文件夹
                String size = BytesUtil.sizeFormatNum2String(Long.parseLong(bean.get("fileSize").toString()));
                bean.put("fileSize", size);
            }
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 分享文件保存
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertShareFileListToSave(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> array = JSONUtil.toList(map.get("jsonStr").toString(), null);//获取数据信息
        Map<String, Object> user = inputObject.getLogParams();
        String userId = user.get("id").toString();
        if (array.size() > 0) {
            String folderId = getThisFolderChildParentId(map.get("folderId").toString());
            String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/" + userId;
            String visitPath = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId;
            List<Map<String, Object>> folderBeans = new ArrayList<>();
            List<Map<String, Object>> fileBeans = new ArrayList<>();
            Map<String, Object> bean;
            for (int i = 0; i < array.size(); i++) {
                bean = new HashMap<>();
                Map<String, Object> object = array.get(i);
                bean.put("id", object.get("rowId"));
                if ("folder".equals(object.get("rowType").toString())) {//文件夹
                    folderBeans.add(bean);
                } else {
                    fileBeans.add(bean);
                }
            }
            if (!folderBeans.isEmpty()) {//选择保存的文件夹不为空
                List<Map<String, Object>> folderNew = fileConsoleDao.queryShareFileFolderListByList(folderBeans);
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByList(folderNew);
                for (Map<String, Object> folder : folderNew) {//重置父id
                    String[] str = folder.get("parentId").toString().split(",");
                    folder.put("directParentId", str[str.length - 1]);
                    folder.put("newId", ToolUtil.getSurFaceId());
                }
                //将数据转化为树的形式，方便进行父id重新赋值
                folderNew = ToolUtil.listToTree(folderNew, "id", "directParentId", "children");
                ToolUtil.FileListParentISEdit(folderNew, folderId);//替换父id
                folderNew = ToolUtil.FileTreeTransList(folderNew);//将树转为list
                for (Map<String, Object> folder : folderNew) {
                    folder.put("createId", userId);
                    folder.put("createTime", DateUtil.getTimeAndToString());
                    folder.put("state", 1);
                }
                //为文件重置新parentId参数
                for (Map<String, Object> folder : folderNew) {
                    String parentId = folder.get("parentId").toString() + folder.get("id").toString() + ",";
                    String newParentId = folder.get("newParentId").toString() + folder.get("newId").toString() + ",";
                    for (Map<String, Object> file : fileNew) {
                        if (parentId.equals(file.get("parentId").toString())) {
                            file.put("newParentId", newParentId);
                        }
                    }
                }
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                    String fileExtName = file.get("fileType").toString().toLowerCase();
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
                    String path = basePath + "/" + newFileName;//文件新路径
                    String oldPath = tPath.replace("images", "") + file.get("fileAddress").toString();//原始路径
                    String trueFileName = visitPath + "/" + newFileName;//数据库存储路径
                    file.put("fileAddress", trueFileName);
                    if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {//图片
                        file.put("fileThumbnail", trueFileName);//缩略图
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {//电子书
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {//视频
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/ffmpeg/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/ffmpeg/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    }
                    ToolUtil.NIOCopyFile(oldPath, path);
                }
                if (!folderNew.isEmpty()) {
                    fileConsoleDao.insertShareFileFolderListByList(folderNew);
                }
                if (!fileNew.isEmpty()) {
                    fileConsoleDao.insertShareFileListByList(fileNew);
                }
            }
            if (!fileBeans.isEmpty()) {//选择保存的文件不为空
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByFileList(fileBeans);
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newParentId", folderId);
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                    String fileExtName = file.get("fileType").toString().toLowerCase();
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
                    String path = basePath + "/" + newFileName;//文件新路径
                    String oldPath = tPath.replace("images", "") + file.get("fileAddress").toString();//原始路径
                    String trueFileName = visitPath + "/" + newFileName;//数据库存储路径
                    file.put("fileAddress", trueFileName);
                    if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {//图片
                        file.put("fileThumbnail", trueFileName);//缩略图
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {//电子书
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 2)) {//office--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 5)) {//ace文件--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {//视频
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/ffmpeg/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/ffmpeg/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 4)) {//压缩包--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else {//其他--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    }
                }
                fileConsoleDao.insertShareFileListByList(fileNew);
            }

        }
    }

    /**
     * 文档在线预览
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFileToShowById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = fileConsoleDao.queryFileToShowById(map);
        String fileType = bean.get("fileType").toString();//文件类型
        String filePath = tPath.replace("images", "") + bean.get("fileAddress").toString();//文件路径

        if ("txt".equals(fileType)) {//ace文件
            File docFile = new File(filePath);
            File pdfFile;
            if (filePath.contains(".")) {
                pdfFile = new File(filePath.substring(0, filePath.lastIndexOf(".")) + ".pdf");
            } else {
                pdfFile = new File(filePath + ".pdf");
            }
            /*判断即将要转换的文件是否真实存在*/
            if (docFile.exists()) {
                /*判断改文件是否已经被转换过,若已经转换则直接预览*/
                if (!pdfFile.exists()) {
                    /*打开OpenOffice连接,*/
                    OpenOfficeConnection connection = new SocketOpenOfficeConnection(Integer.parseInt(sysPort));
                    try {
                        connection.connect();
                        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
                        converter.convert(docFile, pdfFile);
                        connection.disconnect();
                        filePath = pdfFile.getPath(); //文件转换之后的路径
                        PutObject.getResponse().setContentType("application/pdf");
                    } catch (Exception e) {
                        LOGGER.warn("connection failed, message is {}", e);
                    } finally {
                        // 发生exception时, connection不会自动切断, 程序会一直挂着
                        try {
                            if (connection != null) {
                                connection.disconnect();
                                connection = null;
                            }
                        } catch (Exception e) {
                            LOGGER.warn("close connection failed, message is {}", e);
                        }
                    }
                } else {
                    filePath = pdfFile.getPath(); //文件已经转换过
                    PutObject.getResponse().setContentType("application/pdf");
                }
            } else {
                outputObject.setreturnMessage("需要预览的文档在服务器中不存在!");
                return;
            }
        }

        /*将文件写入输出流,显示在界面上,实现预览效果*/
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            fis = new FileInputStream(filePath);
            os = PutObject.getResponse().getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
            }
            if ("java".equals(fileType) || "sql".equals(fileType) || "css".equals(fileType) || "tpl".equals(fileType)
                || "json".equals(fileType) || "js".equals(fileType)) {
                os.write(("<script type='text/javascript' id='javaTextTemplate'>document.getElementById('javaTextTemplate').remove();"
                    + "var content = '<textarea style=\"width: 100%;height: 100%;border: 0px;padding: 0px;margin: 0px; resize: none;\" readonly>' "
                    + "+ document.body.innerHTML + '</textarea>'; "
                    + "document.body.innerHTML = content;</script>").getBytes());
            }
            os.flush();
        } catch (IOException e) {
            LOGGER.warn("write failed, message is {}", e);
        } finally {
            FileUtil.close(os);
            FileUtil.close(fis);
        }
    }

    /**
     * 创建空文件
     *
     * @param fileExtName 文件后缀
     * @param userId      用户id
     * @param folderId    所属文件夹id
     */
    public void createNewFileOrFolder(String fileExtName, String userId, String folderId) {
        String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
        String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/" + userId;
        FileUtil.createDirs(basePath);
        String visitPath = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId;
        String path = basePath + "/" + newFileName;
        createFile(fileExtName, path);

        Map<String, Object> map = new HashMap<>();
        String trueFileName = visitPath + "/" + newFileName;
        map.put("folderId", folderId);
        map.put("fileType", fileExtName);//文件类型
        map.put("fileSizeType", "bytes");//文件大小单位
        map.put("size", 0);//文件大小
        map.put("name", "新建文件" + "." + fileExtName);//文件名
        map.put("fileAddress", trueFileName);//文件地址
        map.put("chunk", 0);//文件整合完之后的序号 默认0
        map.put("chunkSize", 0);//文件整合之后的大小
        map.put("md5", "");//文件整合之后的编码
        map.put("fileThumbnail", DiskCloudConstants.FileMation.getIconByFileExt(fileExtName));//文件缩略图地址
        DataCommonUtil.setCommonData(map, userId);
        folderId = getThisFolderChildParentId(folderId);
        map.put("folderId", folderId);
        fileConsoleDao.insertUploadFileByUserId(map);
    }

    /**
     * 创建文件
     *
     * @param fileExtName 文件后缀
     * @param path        文件地址
     */
    private void createFile(String fileExtName, String path) {
        if (DiskCloudConstants.FileMation.OFFICE_IS_DOCX.getFileExt().equalsIgnoreCase(fileExtName)) {
            FileUtil.createNewDocxFile(path);
        } else if (DiskCloudConstants.FileMation.OFFICE_IS_XLSX.getFileExt().equalsIgnoreCase(fileExtName)) {
            FileUtil.createNewExcelFile(path);
        } else if (DiskCloudConstants.FileMation.OFFICE_IS_PPT.getFileExt().equalsIgnoreCase(fileExtName)) {
            FileUtil.createNewPPtFile(path);
        } else {
            FileUtil.createNewSimpleFile(path);
        }

    }

    /**
     * 新建word文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertWordFileToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        createNewFileOrFolder("docx", inputObject.getLogParams().get("id").toString(), map.get("folderId").toString());
    }

    /**
     * 新建excel文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertExcelFileToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        createNewFileOrFolder("xlsx", inputObject.getLogParams().get("id").toString(), map.get("folderId").toString());
    }

    /**
     * 新建ppt文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertPPTFileToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        createNewFileOrFolder("ppt", inputObject.getLogParams().get("id").toString(), map.get("folderId").toString());
    }

    /**
     * 新建txt文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertTXTFileToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        createNewFileOrFolder("txt", inputObject.getLogParams().get("id").toString(), map.get("folderId").toString());
    }

    /**
     * 新建html文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertHtmlFileToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        createNewFileOrFolder("html", inputObject.getLogParams().get("id").toString(), map.get("folderId").toString());
    }

    /**
     * 创建副本
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertDuplicateCopyToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> array = JSONUtil.toList(map.get("jsonStr").toString(), null);//获取数据信息
        Map<String, Object> user = inputObject.getLogParams();
        String userId = user.get("id").toString();
        if (array.size() > 0) {
            String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/" + userId;
            String visitPath = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId;
            String folderId = getThisFolderChildParentId(map.get("folderId").toString());
            List<Map<String, Object>> folderBeans = new ArrayList<>();
            List<Map<String, Object>> fileBeans = new ArrayList<>();
            Map<String, Object> bean;
            for (int i = 0; i < array.size(); i++) {
                bean = new HashMap<>();
                Map<String, Object> object = array.get(i);
                bean.put("id", object.get("rowId"));
                if ("folder".equals(object.get("rowType").toString())) {//文件夹
                    folderBeans.add(bean);
                } else {
                    fileBeans.add(bean);
                }
            }
            if (!folderBeans.isEmpty()) {//选择保存的文件夹不为空
                List<Map<String, Object>> folderNew = fileConsoleDao.queryShareFileFolderListByList(folderBeans);
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByList(folderNew);
                for (Map<String, Object> folder : folderNew) {//重置父id
                    String[] str = folder.get("parentId").toString().split(",");
                    folder.put("directParentId", str[str.length - 1]);
                    folder.put("newId", ToolUtil.getSurFaceId());
                }
                //将数据转化为树的形式，方便进行父id重新赋值
                folderNew = ToolUtil.listToTree(folderNew, "id", "directParentId", "children");
                ToolUtil.FileListParentISEdit(folderNew, folderId);//替换父id
                folderNew = ToolUtil.FileTreeTransList(folderNew);//将树转为list
                for (Map<String, Object> folder : folderNew) {
                    folder.put("createId", userId);
                    folder.put("createTime", DateUtil.getTimeAndToString());
                    folder.put("state", 1);
                }
                //为文件重置新parentId参数
                for (Map<String, Object> folder : folderNew) {
                    String parentId = folder.get("parentId").toString() + folder.get("id").toString() + ",";
                    String newParentId = folder.get("newParentId").toString() + folder.get("newId").toString() + ",";
                    for (Map<String, Object> file : fileNew) {
                        if (parentId.equals(file.get("parentId").toString())) {
                            file.put("newParentId", newParentId);
                        }
                    }
                }
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                    String fileExtName = file.get("fileType").toString().toLowerCase();
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
                    String path = basePath + "/" + newFileName;//文件新路径
                    String oldPath = tPath.replace("images", "") + file.get("fileAddress").toString();//原始路径
                    String trueFileName = visitPath + "/" + newFileName;//数据库存储路径
                    file.put("fileAddress", trueFileName);
                    if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {//图片
                        file.put("fileThumbnail", trueFileName);//缩略图
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {//电子书
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 2)) {//office--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 5)) {//ace文件--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {//视频
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/ffmpeg/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/ffmpeg/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 4)) {//压缩包--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else {//其他--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    }
                }
                if (!folderNew.isEmpty()) {
                    fileConsoleDao.insertShareFileFolderListByList(folderNew);
                }
                if (!fileNew.isEmpty()) {
                    fileConsoleDao.insertShareFileListByList(fileNew);
                }
            }
            if (!fileBeans.isEmpty()) {//选择保存的文件不为空
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByFileList(fileBeans);
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newParentId", folderId);
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                    String fileExtName = file.get("fileType").toString().toLowerCase();
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
                    String path = basePath + "/" + newFileName;//文件新路径
                    String oldPath = tPath.replace("images", "") + file.get("fileAddress").toString();//原始路径
                    String trueFileName = visitPath + "/" + newFileName;//数据库存储路径
                    file.put("fileAddress", trueFileName);
                    if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {//图片
                        file.put("fileThumbnail", trueFileName);//缩略图
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {//电子书
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 2)) {//office--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 5)) {//ace文件--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {//视频
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/ffmpeg/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/ffmpeg/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 4)) {//压缩包--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else {//其他--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    }
                }
                if (!fileNew.isEmpty()) {
                    fileConsoleDao.insertShareFileListByList(fileNew);
                }
            }

        }
    }

    /**
     * 获取文件属性
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFileMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = fileConsoleDao.queryFileMationById(map);
        if (!"文件夹".equals(bean.get("fileType").toString())) {
            // 不是文件夹
            String size = BytesUtil.sizeFormatNum2String(Long.parseLong(bean.get("fileSize").toString()));
            bean.put("fileSize", size);
        }
        outputObject.setBean(bean);
    }

    /**
     * 文件打包
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertFileMationToPackageToFolder(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> array = JSONUtil.toList(map.get("jsonStr").toString(), null);
        if (array.size() > 0) {
            Map<String, Object> user = inputObject.getLogParams();
            String userId = user.get("id").toString();
            String folderId = getThisFolderChildParentId(map.get("folderId").toString());
            List<Map<String, Object>> folderBeans = new ArrayList<>();
            List<Map<String, Object>> fileBeans = new ArrayList<>();
            Map<String, Object> bean;
            for (int i = 0; i < array.size(); i++) {
                bean = new HashMap<>();
                Map<String, Object> object = array.get(i);
                bean.put("id", object.get("rowId"));
                if ("folder".equals(object.get("rowType").toString())) {
                    folderBeans.add(bean);
                } else {
                    fileBeans.add(bean);
                }
            }

            //加载数据
            List<Map<String, Object>> dowlLoadFile = new ArrayList<>();
            if (!folderBeans.isEmpty()) {
                // 选择保存的文件夹不为空
                List<Map<String, Object>> folderNew = fileConsoleDao.queryToPackageFileFolderListByList(folderBeans);
                List<Map<String, Object>> fileNew = fileConsoleDao.queryToPackageFileListByList(folderNew);
                dowlLoadFile.addAll(folderNew);
                dowlLoadFile.addAll(fileNew);
            }
            if (!fileBeans.isEmpty()) {
                // 选择保存的文件不为空
                List<Map<String, Object>> fileNew = fileConsoleDao.queryToPackageFileListByFileList(fileBeans);
                dowlLoadFile.addAll(fileNew);
            }

            if (!dowlLoadFile.isEmpty()) {
                for (Map<String, Object> folder : dowlLoadFile) {
                    // 重置父id
                    String[] str = folder.get("parentId").toString().split(",");
                    folder.put("directParentId", str[str.length - 1]);
                }
                //将数据转化为树的形式，方便进行父id重新赋值
                dowlLoadFile = ToolUtil.listToTree(dowlLoadFile, "id", "directParentId", "children");
                //打包
                String fileName = String.valueOf(System.currentTimeMillis());//压缩包文件名
                String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/" + userId;
                String visitPath = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId;
                String strZipPath = basePath + "/" + fileName + ".zip";
                File zipFile = new File(strZipPath);
                if (zipFile.exists()) {
                    outputObject.setreturnMessage("该文件已存在，生成失败。");
                    return;
                } else {
                    ZipOutputStream out = null;
                    try {
                        out = new ZipOutputStream(new FileOutputStream(strZipPath));
                        ToolUtil.recursionZip(out, dowlLoadFile, "", tPath.replace("images", ""), 2);
                    } catch (Exception ee) {
                        throw new CustomException(ee);
                    } finally {
                        FileUtil.close(out);
                    }
                }
                String fileExtName = "zip";
                String trueFileName = visitPath + "/" + fileName + "." + fileExtName;
                map.put("fileType", fileExtName);//文件类型
                map.put("fileSizeType", "bytes");//文件大小单位
                map.put("size", zipFile.length());//文件大小
                map.put("name", "压缩文件" + "." + fileExtName);//文件名
                map.put("fileAddress", trueFileName);//文件地址
                map.put("chunk", 0);//文件整合完之后的序号 默认0
                map.put("chunkSize", zipFile.length());//文件整合之后的大小
                map.put("md5", "");//文件整合之后的编码
                map.put("fileThumbnail", "../../assets/images/rar.png");//文件缩略图地址
                DataCommonUtil.setCommonData(map, userId);
                if ("1".equals(map.get("folderId").toString()) || "2".equals(map.get("folderId").toString()) || "3".equals(map.get("folderId").toString())) {//收藏夹   我的文档   企业网盘
                    map.put("folderId", map.get("folderId").toString() + ",");
                } else {
                    map.put("parentId", map.get("folderId"));
                    map.put("folderId", folderId);
                }
                fileConsoleDao.insertUploadFileByUserId(map);
            } else {
                outputObject.setreturnMessage("未找到要打包的文件.");
                return;
            }
        }
    }

    /**
     * 压缩包解压
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertFileMationPackageToFolder(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> file = fileConsoleDao.queryFilePackageMationById(map);
        String fileType = file.get("fileType").toString();
        if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileType, 4)) {//压缩包
            Map<String, Object> user = inputObject.getLogParams();
            String userId = user.get("id").toString();
            String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/" + userId + "/";
            String visitPath = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId;
            String parentId = file.get("parentId").toString();//压缩包父id
            String zipfile = tPath.replace("images", "") + file.get("fileAddress").toString();//压缩包文件
            if (new File(zipfile).exists()) {
                ZipEntry entry;
                ZipFile zip = null;
                // 封装解压后的路径
                BufferedOutputStream bos = null;
                // 封装待解压文件路径
                BufferedInputStream bis = null;
                List<Map<String, Object>> beans = new ArrayList<>();
                try {
                    // 设置,默认是UTF-8
                    Charset charset = Charset.forName("GBK");
                    zip = new ZipFile(zipfile, charset);
                    Map<String, Object> bean;
                    Enumeration<ZipEntry> enums = (Enumeration<ZipEntry>) zip.entries();
                    String fileName = "";//文件名称
                    String fileZipPath = "";//文件路径--作为文件父id
                    String newSaveFileName = "";//新文件保存名称
                    while (enums.hasMoreElements()) {
                        entry = enums.nextElement();
                        bean = new HashMap<>();
                        if (entry.isDirectory()) {
                            fileName = ToolUtil.getSubStr("/" + entry.getName(), 2);//文件名
                            fileZipPath = entry.getName().replace(fileName, "");//文件路径--作为文件父id
                            if (ToolUtil.isBlank(fileZipPath)) {
                                bean.put("parentId", "0");
                            } else {
                                bean.put("parentId", fileZipPath);
                            }
                            bean.put("originalName", entry.getName());
                            bean.put("id", entry.getName());
                            bean.put("newId", ToolUtil.getSurFaceId());
                            bean.put("realName", fileName.replace("/", ""));
                            bean.put("fileAddress", "");
                            bean.put("fileExtName", "folder");
                            beans.add(bean);
                        } else {
                            fileName = entry.getName().substring(("/" + entry.getName()).lastIndexOf("/"));//文件名
                            fileZipPath = entry.getName().replace(fileName, "");//文件路径--作为文件父id
                            newSaveFileName = String.valueOf(System.currentTimeMillis());
                            if (ToolUtil.isBlank(fileZipPath)) {
                                bean.put("parentId", "0");
                            } else {
                                bean.put("parentId", fileZipPath);
                            }
                            bean.put("originalName", entry.getName());
                            bean.put("id", entry.getName());
                            bean.put("newId", ToolUtil.getSurFaceId());
                            bean.put("realName", fileName);
                            bean.put("fileAddress", visitPath + "/" + newSaveFileName + "." + fileName.substring(fileName.lastIndexOf(".") + 1));
                            bean.put("fileExtName", fileName.substring(fileName.lastIndexOf(".") + 1));
                            beans.add(bean);
                            bos = new BufferedOutputStream(new FileOutputStream(basePath + newSaveFileName + "." + fileName.substring(fileName.lastIndexOf(".") + 1)));
                            // 获取条目流
                            bis = new BufferedInputStream(zip.getInputStream(entry));
                            byte[] buf = new byte[1024];
                            int len;
                            while ((len = bis.read(buf)) != -1) {
                                bos.write(buf, 0, len);
                            }
                            bos.close();
                        }
                    }
                } catch (Exception ee) {
                    throw new CustomException(ee);
                } finally {
                    FileUtil.close(bis);
                    FileUtil.close(bos);
                    FileUtil.close(zip);
                }
                String folderId = parentId.substring(0, parentId.lastIndexOf(",")).split(",")[0];
                jedisClient.delKeys(DiskCloudConstants.SYS_FILE_MATION_FOLDER_LIST_MATION + folderId + "*");//删除父目录的redis的key
                beans = ToolUtil.listToTree(beans, "id", "parentId", "children");
                ToolUtil.FileListParentISEdit(beans, parentId);//替换父id
                beans = ToolUtil.FileTreeTransList(beans);//将树转为list
                List<Map<String, Object>> folderList = ToolUtil.getFolderByList(beans);//获取集合中的文件夹
                List<Map<String, Object>> fileList = ToolUtil.getFileByList(beans);//获取集合中的文件
                for (Map<String, Object> item : folderList) {//文件夹
                    item.put("state", 1);
                    item.put("createId", userId);
                    item.put("createTime", DateUtil.getTimeAndToString());
                }
                if (!folderList.isEmpty()) {
                    fileConsoleDao.insertFolderByPackageAndUserId(folderList);
                }
                File f = null;
                for (Map<String, Object> item : fileList) {//文件
                    f = new File(tPath.replace("images", "") + item.get("fileAddress").toString());
                    item.put("fileSizeType", "bytes");//文件大小单位
                    item.put("size", f.length());//文件大小
                    item.put("chunk", 0);//文件整合完之后的序号 默认0
                    item.put("chunkSize", f.length());//文件整合之后的大小
                    item.put("md5", "");//文件整合之后的编码

                    String fileExtName = item.get("fileExtName").toString();
                    if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {//图片
                        item.put("fileThumbnail", item.get("fileAddress").toString());//文件缩略图地址
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {//电子书
                        String picName = String.valueOf(System.currentTimeMillis()) + ".jpg";
                        String newFilename = basePath + picName;
                        writeAndReadQpubFileThumbnail(tPath.replace("images", "") + item.get("fileAddress").toString(), newFilename);
                        // 文件缩略图地址
                        item.put("fileThumbnail", visitPath + "/" + picName);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 2)) {
                        //office文件缩略图地址
                        item.put("fileThumbnail", DiskCloudConstants.FileMation.getIconByFileExt(fileExtName));
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 5)) {
                        // ace文件缩略图地址
                        item.put("fileThumbnail", DiskCloudConstants.FileMation.getIconByFileExt(fileExtName));
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {//视频
                        String ffmpegGPath = tPath + "/util/ffmpeg.exe";//工具路径
                        String fileThumbnail = String.valueOf(System.currentTimeMillis()) + ".jpg";
                        FileUtil.createDirs(basePath + "ffmpeg/");
                        if (ToolUtil.take(tPath.replace("images", "") + item.get("fileAddress").toString(), basePath + "ffmpeg/" + fileThumbnail, ffmpegGPath)) {
                            item.put("fileThumbnail", visitPath + "/ffmpeg/" + fileThumbnail);
                        } else {
                            FileUtil.deleteFile(tPath.replace("images", "") + item.get("fileAddress").toString());
                            outputObject.setreturnMessage("上传失败。");
                            return;
                        }
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 4)) {//压缩包
                        item.put("fileThumbnail", "../../assets/images/rar.png");//文件缩略图地址
                    } else {//其他
                        item.put("fileThumbnail", "../../assets/images/other-icon.png");//文件缩略图地址
                    }

                    item.put("state", 1);
                    item.put("createId", userId);
                    item.put("createTime", DateUtil.getTimeAndToString());
                }
                if (!fileList.isEmpty()) {
                    fileConsoleDao.insertFileByPackageAndUserId(fileList);
                }
            } else {
                outputObject.setreturnMessage("该文件已不存在。");
            }
        } else {
            outputObject.setreturnMessage("文件类型不正确，无法进行解压。");
        }
    }

    /**
     * 文件或者文件夹复制
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertPasteCopyToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> array = JSONUtil.toList(map.get("jsonStr").toString(), null);//获取数据信息
        Map<String, Object> user = inputObject.getLogParams();
        String userId = user.get("id").toString();
        if (array.size() > 0) {
            String folderId = getThisFolderChildParentId(map.get("folderId").toString());
            String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/" + userId;
            String visitPath = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + userId;
            List<Map<String, Object>> folderBeans = new ArrayList<>();
            List<Map<String, Object>> fileBeans = new ArrayList<>();
            Map<String, Object> bean;
            for (int i = 0; i < array.size(); i++) {
                bean = new HashMap<>();
                Map<String, Object> object = array.get(i);
                bean.put("id", object.get("rowId"));
                if ("folder".equals(object.get("rowType").toString())) {//文件夹
                    folderBeans.add(bean);
                } else {
                    fileBeans.add(bean);
                }
            }
            if (!folderBeans.isEmpty()) {//选择保存的文件夹不为空
                List<Map<String, Object>> folderNew = fileConsoleDao.queryShareFileFolderListByList(folderBeans);
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByList(folderNew);
                for (Map<String, Object> folder : folderNew) {//重置父id
                    String[] str = folder.get("parentId").toString().split(",");
                    folder.put("directParentId", str[str.length - 1]);
                    folder.put("newId", ToolUtil.getSurFaceId());
                }
                //将数据转化为树的形式，方便进行父id重新赋值
                folderNew = ToolUtil.listToTree(folderNew, "id", "directParentId", "children");
                ToolUtil.FileListParentISEdit(folderNew, folderId);//替换父id
                folderNew = ToolUtil.FileTreeTransList(folderNew);//将树转为list
                for (Map<String, Object> folder : folderNew) {
                    folder.put("createId", userId);
                    folder.put("createTime", DateUtil.getTimeAndToString());
                    folder.put("state", 1);
                }
                //为文件重置新parentId参数
                for (Map<String, Object> folder : folderNew) {
                    String parentId = folder.get("parentId").toString() + folder.get("id").toString() + ",";
                    String newParentId = folder.get("newParentId").toString() + folder.get("newId").toString() + ",";
                    for (Map<String, Object> file : fileNew) {
                        if (parentId.equals(file.get("parentId").toString())) {
                            file.put("newParentId", newParentId);
                        }
                    }
                }
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                    String fileExtName = file.get("fileType").toString().toLowerCase();
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
                    String path = basePath + "/" + newFileName;//文件新路径
                    String oldPath = tPath.replace("images", "") + file.get("fileAddress").toString();//原始路径
                    String trueFileName = visitPath + "/" + newFileName;//数据库存储路径
                    file.put("fileAddress", trueFileName);
                    if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {//图片
                        file.put("fileThumbnail", trueFileName);//缩略图
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {//电子书
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 2)) {//office--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 5)) {//ace文件--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {//视频
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/ffmpeg/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/ffmpeg/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 4)) {//压缩包--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else {//其他--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    }
                }
                if (!folderNew.isEmpty()) {
                    fileConsoleDao.insertShareFileFolderListByList(folderNew);
                }
                if (!fileNew.isEmpty()) {
                    fileConsoleDao.insertShareFileListByList(fileNew);
                }
            }
            if (!fileBeans.isEmpty()) {//选择保存的文件不为空
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByFileList(fileBeans);
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newParentId", folderId);
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                    String fileExtName = file.get("fileType").toString().toLowerCase();
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + fileExtName;//新文件名
                    String path = basePath + "/" + newFileName;//文件新路径
                    String oldPath = tPath.replace("images", "") + file.get("fileAddress").toString();//原始路径
                    String trueFileName = visitPath + "/" + newFileName;//数据库存储路径
                    file.put("fileAddress", trueFileName);
                    if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 1)) {//图片
                        file.put("fileThumbnail", trueFileName);//缩略图
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 6)) {//电子书
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 2)) {//office--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 5)) {//ace文件--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 3)) {//视频
                        ToolUtil.NIOCopyFile(oldPath, path);
                        String oldFileThumbnail = tPath.replace("images", "") + file.get("fileThumbnail").toString();
                        String fileThunbnailName = String.valueOf(System.currentTimeMillis());
                        String fileThumbnailpath = basePath + "/ffmpeg/" + fileThunbnailName + ".png";//缩略图新路径
                        file.put("fileThumbnail", visitPath + "/ffmpeg/" + fileThunbnailName + ".png");//缩略图
                        ToolUtil.NIOCopyFile(oldFileThumbnail, fileThumbnailpath);
                    } else if (DiskCloudConstants.FileMation.judgeIsAllowedFileType(fileExtName, 4)) {//压缩包--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    } else {//其他--缩略图地址不变
                        ToolUtil.NIOCopyFile(oldPath, path);
                    }
                }
                fileConsoleDao.insertShareFileListByList(fileNew);
            }

        }
    }

    /**
     * 文件或者文件夹剪切
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertPasteCutToService(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> array = JSONUtil.toList(map.get("jsonStr").toString(), null);//获取数据信息
        Map<String, Object> user = inputObject.getLogParams();
        String userId = user.get("id").toString();
        if (array.size() > 0) {
            String folderId = getThisFolderChildParentId(map.get("folderId").toString());
            List<Map<String, Object>> folderBeans = new ArrayList<>();
            List<Map<String, Object>> fileBeans = new ArrayList<>();
            Map<String, Object> bean;
            for (int i = 0; i < array.size(); i++) {
                bean = new HashMap<>();
                Map<String, Object> object = array.get(i);
                bean.put("id", object.get("rowId"));
                if ("folder".equals(object.get("rowType").toString())) {//文件夹
                    Map<String, Object> parentId = fileConsoleDao.queryFolderMationById(bean.get("id").toString());
                    String oldParentId = parentId.get("parentId").toString();
                    jedisClient.delKeys(DiskCloudConstants.SYS_FILE_MATION_FOLDER_LIST_MATION + oldParentId.split(",")[oldParentId.split(",").length - 1] + "*");//删除父目录的redis的key
                    folderBeans.add(bean);
                } else {
                    fileBeans.add(bean);
                }
            }
            if (!folderBeans.isEmpty()) {//选择保存的文件夹不为空
                List<Map<String, Object>> folderNew = fileConsoleDao.queryShareFileFolderListByList(folderBeans);
                if (!folderNew.isEmpty()) {//删除之前的信息
                    fileConsoleDao.deleteShareFileFolderListByList(folderNew);
                }
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByList(folderNew);
                if (!fileNew.isEmpty()) {//删除之前的信息
                    fileConsoleDao.deleteShareFileListByList(fileNew);
                }
                for (Map<String, Object> folder : folderNew) {//重置父id
                    String[] str = folder.get("parentId").toString().split(",");
                    folder.put("directParentId", str[str.length - 1]);
                    folder.put("newId", ToolUtil.getSurFaceId());
                }
                // 将数据转化为树的形式，方便进行父id重新赋值
                folderNew = ToolUtil.listToTree(folderNew, "id", "directParentId", "children");
                ToolUtil.FileListParentISEdit(folderNew, folderId);//替换父id
                folderNew = ToolUtil.FileTreeTransList(folderNew);//将树转为list
                for (Map<String, Object> folder : folderNew) {
                    folder.put("createId", userId);
                    folder.put("createTime", DateUtil.getTimeAndToString());
                    folder.put("state", 1);
                }
                // 为文件重置新parentId参数
                for (Map<String, Object> folder : folderNew) {
                    String parentId = folder.get("parentId").toString() + folder.get("id").toString() + ",";
                    String newParentId = folder.get("newParentId").toString() + folder.get("newId").toString() + ",";
                    for (Map<String, Object> file : fileNew) {
                        if (parentId.equals(file.get("parentId").toString())) {
                            file.put("newParentId", newParentId);
                        }
                    }
                }
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                }
                if (!folderNew.isEmpty()) {
                    fileConsoleDao.insertShareFileFolderListByList(folderNew);
                }
                if (!fileNew.isEmpty()) {
                    fileConsoleDao.insertShareFileListByList(fileNew);
                }
            }
            if (!fileBeans.isEmpty()) {//选择保存的文件不为空
                List<Map<String, Object>> fileNew = fileConsoleDao.queryShareFileListByFileList(fileBeans);
                if (!fileNew.isEmpty()) {//删除之前的信息
                    fileConsoleDao.deleteShareFileListByFileList(fileNew);
                }
                //为文件重置新参数
                for (Map<String, Object> file : fileNew) {
                    file.put("newParentId", folderId);
                    file.put("newId", ToolUtil.getSurFaceId());
                    file.put("createId", userId);
                    file.put("createTime", DateUtil.getTimeAndToString());
                    file.put("state", 1);
                }
                fileConsoleDao.insertShareFileListByList(fileNew);
            }
        }
    }

    /**
     * office文件编辑获取修改时间作为最新的key
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryOfficeUpdateTimeToKey(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = fileConsoleDao.queryOfficeUpdateTimeToKey(map);
        outputObject.setBean(bean);
    }

    /**
     * 文件统计报表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFileNumStatistics(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        //文件总数量和总存储
        Map<String, Object> allNum = fileConsoleDao.queryAllNumFile(map);
        allNum.put("fileSizeZh", BytesUtil.sizeFormatNum2String(Long.parseLong(allNum.get("fileSize").toString())));
        //今日新增的文件总数量和总存储
        Map<String, Object> allNumToday = fileConsoleDao.queryAllNumFileToday(map);
        allNumToday.put("fileSizeZh", BytesUtil.sizeFormatNum2String(Long.parseLong(allNumToday.get("fileSize").toString())));
        //本周新增的文件总数量和总存储
        Map<String, Object> allNumThisWeek = fileConsoleDao.queryAllNumFileThisWeek(map);
        allNumThisWeek.put("fileSizeZh", BytesUtil.sizeFormatNum2String(Long.parseLong(allNumThisWeek.get("fileSize").toString())));
        //文件类型占比
        List<Map<String, Object>> fileTypeNum = fileConsoleDao.queryFileTypeNum(map);
        Map<String, Object> fileTypeNumEntity = new HashMap<>();
        String fileTypeNumStr = "";
        for (Map<String, Object> en : fileTypeNum) {
            fileTypeNumStr += en.get("name").toString() + ",";
        }
        fileTypeNumEntity.put("fileTypeNum", fileTypeNum);
        fileTypeNumEntity.put("fileTypeNumStr", fileTypeNumStr);
        //文件存储占比（前三）
        List<Map<String, Object>> fileStorageNum = fileConsoleDao.queryFileStorageNum(map);
        //本年度新增文件数
        List<Map<String, Object>> newFileNum = fileConsoleDao.queryNewFileNum(map);
        //近七天新增文件类型数
        List<Map<String, Object>> fileTypeNumSevenDay = fileConsoleDao.queryFileTypeNumSevenDay(map);

        map.clear();
        map.put("allNum", allNum);
        map.put("allNumToday", allNumToday);
        map.put("allNumThisWeek", allNumThisWeek);
        map.put("fileTypeNumEntity", fileTypeNumEntity);
        map.put("fileStorageNum", fileStorageNum);
        map.put("newFileNum", newFileNum);
        map.put("fileTypeNumSevenDay", fileTypeNumSevenDay);
        outputObject.setBean(map);
    }

    /**
     * 文件打包下载
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void insertFileMationToPackageDownload(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> array = JSONUtil.toList(map.get("jsonStr").toString(), null);//获取数据信息
        if (array.size() > 0) {
            String trueFileName = "";//文件存储路径
            Map<String, Object> user = inputObject.getLogParams();
            String userId = user.get("id").toString();
            //创建前端传来的数据对象
            List<Map<String, Object>> folderBeans = new ArrayList<>();
            List<Map<String, Object>> fileBeans = new ArrayList<>();
            Map<String, Object> bean;
            for (int i = 0; i < array.size(); i++) {
                bean = new HashMap<>();
                Map<String, Object> object = array.get(i);
                bean.put("id", object.get("rowId"));
                if ("folder".equals(object.get("rowType").toString())) {//文件夹
                    folderBeans.add(bean);
                } else {
                    fileBeans.add(bean);
                }
            }
            String basePath = tPath + Constants.FileUploadPath.getSavePath(FILE_PATH_TYPE) + "/temporaryfile/" + userId + "/";
            FileUtil.createDirs(basePath);
            //加载数据
            List<Map<String, Object>> dowlLoadFile = new ArrayList<>();
            if (!folderBeans.isEmpty()) {//选择保存的文件夹不为空
                List<Map<String, Object>> folderNew = fileConsoleDao.queryToPackageFileFolderListByList(folderBeans);
                List<Map<String, Object>> fileNew = fileConsoleDao.queryToPackageFileListByList(folderNew);
                dowlLoadFile.addAll(folderNew);
                dowlLoadFile.addAll(fileNew);
            }
            if (!fileBeans.isEmpty()) {//选择保存的文件不为空
                List<Map<String, Object>> fileNew = fileConsoleDao.queryToPackageFileListByFileList(fileBeans);
                dowlLoadFile.addAll(fileNew);
            }

            //创建压缩包
            if (!dowlLoadFile.isEmpty()) {
                for (Map<String, Object> folder : dowlLoadFile) {//重置父id
                    String[] str = folder.get("parentId").toString().split(",");
                    folder.put("directParentId", str[str.length - 1]);
                }
                //将数据转化为树的形式，方便进行父id重新赋值
                dowlLoadFile = ToolUtil.listToTree(dowlLoadFile, "id", "directParentId", "children");
                //打包
                String fileName = String.valueOf(System.currentTimeMillis());//压缩包文件名
                String strZipPath = basePath + fileName + ".zip";
                File zipFile = new File(strZipPath);
                if (zipFile.exists()) {
                    outputObject.setreturnMessage("该文件已存在，生成失败。");
                    return;
                } else {
                    ZipOutputStream out = null;
                    try {
                        out = new ZipOutputStream(new FileOutputStream(strZipPath));
                        ToolUtil.recursionZip(out, dowlLoadFile, "", tPath.replace("images", ""), 2);
                    } catch (Exception ee) {
                        throw new CustomException(ee);
                    } finally {
                        FileUtil.close(out);
                    }
                }
                trueFileName = Constants.FileUploadPath.getVisitPath(FILE_PATH_TYPE) + "temporaryfile/" + userId + "/" + fileName + ".zip";
            } else {
                outputObject.setreturnMessage("未找到要打包的文件.");
                return;
            }
            map.clear();
            map.put("fileAddress", trueFileName);//文件地址
            outputObject.setBean(map);
        } else {
            outputObject.setreturnMessage("未找到要打包的文件.");
            return;
        }
    }

    /**
     * 获取并写入epub电子书的缩略图
     *
     * @param epubFilePath     epub电子书文件地址
     * @param thumbnailPicPath 缩略图图片地址
     */
    private void writeAndReadQpubFileThumbnail(String epubFilePath, String thumbnailPicPath) {
        FileImageOutputStream imgout = null;
        try {
            EpubReader epubReader = new EpubReader();
            Book book = epubReader.readEpub(new FileInputStream(epubFilePath));
            Resource resource = book.getResources().getByHref("Images/cover.jpg");
            byte[] p = resource.getData();
            imgout = new FileImageOutputStream(new File(thumbnailPicPath));
            imgout.write(p, 0, p.length);
        } catch (IOException e) {
            throw new CustomException(e);
        } finally {
            FileUtil.close(imgout);
        }
    }

}
