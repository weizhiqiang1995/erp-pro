/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.upload.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.constans.FileConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.object.PutObject;
import com.skyeye.common.util.FileUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.upload.entity.Upload;
import com.skyeye.upload.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;
import java.io.IOException;
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
        Map<String, Object> user = inputObject.getLogParams();
        String userId = user.get("id").toString();
        // 将request变成多部分request
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) PutObject.getRequest();
        // 获取multiRequest 中所有的文件名
        Iterator iter = multiRequest.getFileNames();
        String basePath = tPath + FileConstants.FileUploadPath.getSavePath(upload.getType());
        while (iter.hasNext()) {
            // 一次遍历所有文件
            MultipartFile file = multiRequest.getFile(iter.next().toString());
            if (file == null) {
                continue;
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
            upload.setFileType(fileExtName);
            upload.setFileSizeType("bytes");
            newFileName = FileConstants.FileUploadPath.getVisitPath(upload.getType()) + newFileName;
            upload.setFileAddress(newFileName);
            upload.setFileThumbnail("-1");
            List<Upload> beans;
            if (ToolUtil.isBlank(jedisClient.get(Constants.getSysEnclosureFileModuleByMd5(upload.getMd5())))) {
                beans = new ArrayList<>();
            } else {
                beans = JSONUtil.toList(jedisClient.get(Constants.getSysEnclosureFileModuleByMd5(upload.getMd5())), Upload.class);
            }
            beans.add(upload);
            jedisClient.set(Constants.getSysEnclosureFileModuleByMd5(upload.getMd5()), JSONUtil.toJsonStr(beans));
        }
    }
}
