/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.FileConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.FileUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.upload.entity.Upload;
import com.skyeye.upload.entity.UploadChunks;
import com.skyeye.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * @ClassName: UploadServiceImpl
 * @Description: 文件上传、下载服务服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    public JedisClientService jedisClient;

    @Autowired
    private RedisCache redisCache;

    @Value("${IMAGES_PATH}")
    private String tPath;

    /**
     * 上传文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void uploadFile(InputObject inputObject, OutputObject outputObject) {
        Upload upload = inputObject.getParams(Upload.class);
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(PutObject.getRequest().getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        if (!multipartResolver.isMultipart(PutObject.getRequest())) {
            return;
        }
        String userId = inputObject.getLogParams().get("id").toString();
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) PutObject.getRequest();
        // 获取multiRequest 中所有的文件名
        Iterator iter = multiRequest.getFileNames();
        while (iter.hasNext()) {
            String fileAddress = uploadFile(multiRequest, iter, upload.getType(), userId);
            upload.setFileType(fileAddress.substring(fileAddress.lastIndexOf(".") + 1).toLowerCase());
            upload.setFileSizeType("bytes");
            upload.setFileAddress(fileAddress);
            upload.setFileThumbnail("-1");

            String cacheKey = getCacheKey(upload.getMd5());
            List<Upload> beans = redisCache.getList(cacheKey, key -> new ArrayList<>(), RedisConstants.ONE_DAY_SECONDS, Upload.class);
            beans.add(upload);
            jedisClient.set(cacheKey, JSONUtil.toJsonStr(beans));
        }
    }

    private String uploadFile(MultipartHttpServletRequest multiRequest, Iterator iter, Integer type, String userId) {
        String basePath = tPath + FileConstants.FileUploadPath.getSavePath(type, userId);
        MultipartFile file = multiRequest.getFile(iter.next().toString());
        if (file == null) {
            throw new CustomException("file is not null.");
        }
        String fileName = file.getOriginalFilename();
        // 得到文件扩展名
        String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        FileUtil.createDirs(basePath);
        // 自定义的文件名称
        String newFileName = String.format(Locale.ROOT, "%s.%s", System.currentTimeMillis(), fileExtName);
        String path = basePath + "/" + newFileName;
        // 上传
        try {
            file.transferTo(new File(path));
        } catch (IOException e) {
            throw new CustomException(e);
        }
        return FileConstants.FileUploadPath.getVisitPath(type, userId) + newFileName;
    }

    /**
     * 上传文件合并块
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void uploadFileChunks(InputObject inputObject, OutputObject outputObject) {
        UploadChunks uploadChunks = inputObject.getParams(UploadChunks.class);
        String cacheKey = getCacheKey(uploadChunks.getMd5());
        List<Upload> beans = redisCache.getList(cacheKey, key -> new ArrayList<>(), RedisConstants.ONE_DAY_SECONDS, Upload.class);
        List<File> fileList = new ArrayList<>();
        for (Upload bean : beans) {
            File f = new File(tPath.replace("images", "") + bean.getFileAddress());
            fileList.add(f);
        }
        String userId = inputObject.getLogParams().get("id").toString();
        String fileName = uploadChunks.getName();
        String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String newFileName = String.format(Locale.ROOT, "%s.%s", System.currentTimeMillis(), fileExtName);
        String path = tPath + FileConstants.FileUploadPath.getSavePath(uploadChunks.getType(), userId)
            + CommonCharConstants.SLASH_MARK + newFileName;
        FileChannel outChnnel = null;
        try {
            File outputFile = new File(path);
            // 创建文件
            outputFile.createNewFile();
            // 输出流
            outChnnel = new FileOutputStream(outputFile).getChannel();
            FileChannel inChannel;
            for (File file : fileList) {
                inChannel = new FileInputStream(file).getChannel();
                inChannel.transferTo(0, inChannel.size(), outChnnel);
                inChannel.close();
                // 删除分片
                file.delete();
            }
        } catch (Exception e) {
            throw new CustomException(e);
        } finally {
            FileUtil.close(outChnnel);
        }
        jedisClient.del(cacheKey);
        uploadChunks.setFileType(fileExtName);
        uploadChunks.setFileSizeType("bytes");
        newFileName = FileConstants.FileUploadPath.getVisitPath(uploadChunks.getType(), userId) + newFileName;
        uploadChunks.setFileAddress(newFileName);
        outputObject.setBean(uploadChunks);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    private String getCacheKey(String md5) {
        return String.format(Locale.ROOT, "upload:file:chunks:%s", md5);
    }

    /**
     * 文件分块上传检测是否上传
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void checkUploadFileChunks(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String md5 = map.get("md5").toString();
        String chunk = map.get("chunk").toString();
        String cacheKey = getCacheKey(md5);
        List<Upload> beans = redisCache.getList(cacheKey, key -> new ArrayList<>(), RedisConstants.ONE_DAY_SECONDS, Upload.class);
        Upload bean = null;
        int index = -1;
        for (int i = 0; i < beans.size(); i++) {
            Upload upload = beans.get(i);
            if (chunk.equals(upload.getChunk())) {
                bean = upload;
                index = i;
                break;
            }
        }
        if (bean != null) {
            String fileAddress = tPath.replace("images", "") + bean.getFileAddress();
            File checkFile = new File(fileAddress);
            String chunkSize = map.get("chunkSize").toString();
            if (checkFile.exists() && checkFile.length() == Integer.parseInt(chunkSize)) {
            } else {
                beans.remove(index);
                jedisClient.set(cacheKey, JSONUtil.toJsonStr(beans));
                outputObject.setreturnMessage("文件上传失败");
            }
        } else {
            outputObject.setreturnMessage("文件上传失败");
        }
    }
}
