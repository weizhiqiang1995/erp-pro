/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.ueditor.controller;

import com.alibaba.fastjson.JSONObject;
import com.skyeye.ueditor.service.EditUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class EditUploadController {

    @Autowired
    private EditUploadService editUploadService;

    /**
     * 上传富文本图片
     *
     * @param req
     * @return
     */
    @RequestMapping("/upload/editUploadController/uploadContentPic")
    public Map<String, Object> uploadContentPic(HttpServletRequest req) {
        return editUploadService.uploadContentPic(req);
    }

    /**
     * 回显富文本图片
     *
     * @param req
     * @param callback
     * @return
     */
    @RequestMapping("/upload/editUploadController/downloadContentPic")
    public String downloadContentPic(HttpServletRequest req, @RequestParam("callback") String callback) {
        return callback + "(" + JSONObject.toJSONString(editUploadService.downloadContentPic(req)) + ")";
    }

    @RequestMapping("/upload/editUploadController/ueeditorConif")
    public String ueeditorConif(HttpServletRequest request, @RequestParam("callback") String callback,
                                @RequestParam("fileBasePath") String fileBasePath) {
        return callback + "(" + PublicMsg.getUeditorConfig(fileBasePath) + ")";
    }

}
