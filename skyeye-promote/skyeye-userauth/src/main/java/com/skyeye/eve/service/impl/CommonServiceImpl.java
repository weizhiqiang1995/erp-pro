/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.constans.FileConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.FileUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.CommonDao;
import com.skyeye.personnel.dao.SysEveUserStaffDao;
import com.skyeye.eve.service.CommonService;
import com.skyeye.exception.CustomException;
import com.skyeye.personnel.service.impl.SysEveUserStaffServiceImpl;
import com.skyeye.win.dao.SysEveWinBgPicDao;
import com.skyeye.win.dao.SysEveWinLockBgPicDao;
import com.skyeye.win.dao.SysEveWinThemeColorDao;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName: CommonServiceImpl
 * @Description: 公共类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/4 17:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CommonServiceImpl implements CommonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private CommonDao commonDao;

    @Autowired
    private SysEveWinBgPicDao sysEveWinBgPicDao;

    @Autowired
    private SysEveWinLockBgPicDao sysEveWinLockBgPicDao;

    @Autowired
    private SysEveWinThemeColorDao sysEveWinThemeColorDao;

    @Autowired
    private SysEveUserStaffDao sysEveUserStaffDao;

    @Value("${IMAGES_PATH}")
    private String tPath;

    @Autowired
    private RedisCache redisCache;

    /**
     * 上传文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void uploadFile(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(PutObject.getRequest().getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (multipartResolver.isMultipart(PutObject.getRequest())) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) PutObject.getRequest();
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            int type = Integer.parseInt(map.get("type").toString());
            String basePath = tPath + FileConstants.FileUploadPath.getSavePath(type);
            Map<String, Object> bean = new HashMap<>();
            StringBuffer trueFileName = new StringBuffer();
            String fileName = "";
            while (iter.hasNext()) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                fileName = file.getOriginalFilename();// 文件名称
                //得到文件扩展名
                String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
                if (file != null) {
                    FileUtil.createDirs(basePath);
                    // 自定义的文件名称
                    String newFileName = String.format(Locale.ROOT, "%s.%s", System.currentTimeMillis(), fileExtName);
                    String path = basePath + "/" + newFileName;
                    LOGGER.info("upload file type is: {}, path is: {}", type, path);
                    // 上传
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException ex) {
                        throw new CustomException(ex);
                    }
                    newFileName = FileConstants.FileUploadPath.getVisitPath(type) + newFileName;
                    if (ToolUtil.isBlank(trueFileName.toString())) {
                        trueFileName.append(newFileName);
                    } else {
                        trueFileName.append(",").append(newFileName);
                    }
                }
            }
            bean.put("picUrl", trueFileName.toString());
            bean.put("type", type);
            bean.put("fileName", fileName);
            outputObject.setBean(bean);
        }
    }

    /**
     * 上传文件Base64
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void uploadFileBase64(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        int type = Integer.parseInt(map.get("type").toString());
        String imgStr = map.get("images").toString();
        imgStr = imgStr.replaceAll("\\+", "%2B").replaceAll(" ", "+");
        String[] d = imgStr.split("base64,");
        // 上传数据是否合法
        if (d != null && d.length == 2) {
            String dataPrix = d[0];
            String data = d[1];
            if (FileUtil.checkBase64IsImage(dataPrix)) {
                try {
                    byte[] bytes = Base64.decodeBase64(new String(data).getBytes());
                    // 决定存储路径
                    String basePath = tPath + FileConstants.FileUploadPath.getSavePath(type);
                    FileUtil.createDirs(basePath);
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + FileUtil.getBase64FileTypeByPrix(dataPrix);
                    // 写入文件
                    FileUtil.writeByteToPointPath(bytes, basePath + "/" + trueFileName);
                    Map<String, Object> bean = new HashMap<>();
                    bean.put("picUrl", FileConstants.FileUploadPath.getVisitPath(type) + trueFileName);
                    bean.put("type", type);
                    outputObject.setBean(bean);
                } catch (Exception ee) {
                    LOGGER.warn("uploadFileBase64 failed. {}", ee);
                    outputObject.setreturnMessage("上传失败，数据不合法");
                }
            } else {
                outputObject.setreturnMessage("文件类型不正确，只允许上传jpg,png,jpeg格式的图片");
            }
        } else {
            outputObject.setreturnMessage("上传失败，数据不合法");
        }
    }

    /**
     * 代码生成器生成下载文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void downloadFileByJsonData(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> array = JSONUtil.toList(map.get("jsonData").toString(), null);
        List<Map<String, Object>> inBeans = new ArrayList<>();
        Map<String, Object> user = inputObject.getLogParams();
        String zipName = ToolUtil.getSurFaceId() + ".zip";
        String basePath = tPath + FileConstants.FileUploadPath.CODE_GENERATOR.getSavePath();
        FileUtil.createDirs(basePath);
        String strZipPath = tPath + FileConstants.FileUploadPath.CODE_GENERATOR.getSavePath() + "/" + zipName;
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(strZipPath));
            byte[] buffer = new byte[1024];

            for (int i = 0; i < array.size(); i++) {
                JSONObject object = (JSONObject) array.get(i);
                String content = object.getStr("content");
                // 加入压缩包
                ByteArrayInputStream stream = new ByteArrayInputStream(content.getBytes());
                if ("javascript".equals(object.getStr("modelType").toLowerCase())) {
                    out.putNextEntry(new ZipEntry(object.getStr("fileName") + ".js"));
                } else {
                    out.putNextEntry(new ZipEntry(object.getStr("fileName") + "." + object.getStr("modelType").toLowerCase()));
                }
                int len;
                // 读入需要下载的文件的内容，打包到zip文件
                while ((len = stream.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
                out.closeEntry();
                Map<String, Object> bean = getCodeModelHoitoryObject(user, zipName, object, content);
                inBeans.add(bean);
            }
        } catch (Exception ex) {
            throw new CustomException(ex);
        } finally {
            FileUtil.close(out);
        }
        commonDao.insertCodeModelHistory(inBeans);
    }

    private Map<String, Object> getCodeModelHoitoryObject(Map<String, Object> user, String zipName, JSONObject object, String content) {
        Map<String, Object> bean = new HashMap<>();
        bean.put("tableName", object.getStr("tableName"));
        bean.put("groupId", object.getStr("groupId"));
        bean.put("modelId", object.getStr("modelId"));
        bean.put("content", content);
        bean.put("fileName", object.getStr("fileName"));
        if ("javascript".equals(object.getStr("modelType").toLowerCase())) {
            bean.put("fileType", "js");
        } else {
            bean.put("fileType", object.getStr("modelType").toLowerCase());
        }
        bean.put("filePath", zipName);
        DataCommonUtil.setCommonData(bean, user.get("id").toString());
        return bean;
    }

    /**
     * 获取win系统桌列表信息供展示
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysWinMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();

        // 获取win系统桌面图片列表供展示
        List<Map<String, Object>> winBgPic = redisCache.getList(Constants.getSysWinBgPicRedisKey(), key ->
            sysEveWinBgPicDao.querySysEveWinBgPicListToShow(map), RedisConstants.THIRTY_DAY_SECONDS);

        // 获取win系统锁屏桌面图片列表供展示
        List<Map<String, Object>> winLockBgPic = redisCache.getList(Constants.getSysWinLockBgPicRedisKey(), key ->
            sysEveWinLockBgPicDao.querySysEveWinBgPicListToShow(map), RedisConstants.THIRTY_DAY_SECONDS);

        // 获取win系统主题颜色列表供展示
        List<Map<String, Object>> winThemeColor = redisCache.getList(Constants.getSysWinThemeColorRedisKey(), key ->
            sysEveWinThemeColorDao.querySysEveWinThemeColorListToShow(map), RedisConstants.THIRTY_DAY_SECONDS);

        map.put("winBgPic", winBgPic);
        map.put("winLockBgPic", winLockBgPic);
        map.put("winThemeColor", winThemeColor);
        outputObject.setBean(map);
        outputObject.settotal(1);
    }

    /**
     * 获取所有在职的，拥有账号的员工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllSysUserIsIncumbency(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Integer> state = this.getIncumbencyState();
        map.put("state", state);
        List<Map<String, Object>> beans = sysEveUserStaffDao.queryAllSysUserIsIncumbency(map);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    private List<Integer> getIncumbencyState() {
        List<Integer> list = new ArrayList<>();
        list.add(SysEveUserStaffServiceImpl.State.ON_THE_JOB.getState());
        list.add(SysEveUserStaffServiceImpl.State.PROBATION.getState());
        list.add(SysEveUserStaffServiceImpl.State.PROBATION_PERIOD.getState());
        return list;
    }

    /**
     * 根据文件类型获取文件的保存地址以及访问地址
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFilePathByFileType(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Integer fileType = Integer.parseInt(map.get("fileType").toString());
        String savePath = tPath + FileConstants.FileUploadPath.getSavePath(fileType);
        FileUtil.createDirs(savePath);
        String visitPath = FileConstants.FileUploadPath.getVisitPath(fileType);

        Map<String, Object> result = new HashMap<>();
        result.put("savePath", savePath);
        result.put("visitPath", visitPath);
        outputObject.setBean(result);
        outputObject.settotal(1);
    }

}
