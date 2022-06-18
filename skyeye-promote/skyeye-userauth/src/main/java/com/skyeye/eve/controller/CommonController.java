/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CommonService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "公共接口", tags = "公共接口", modelName = "基础模块")
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * 上传文件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CommonController/uploadFile")
    public void uploadFile(InputObject inputObject, OutputObject outputObject) throws Exception {
        commonService.uploadFile(inputObject, outputObject);
    }

    /**
     * 上传文件Base64
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CommonController/uploadFileBase64")
    public void uploadFileBase64(InputObject inputObject, OutputObject outputObject) throws Exception {
        commonService.uploadFileBase64(inputObject, outputObject);
    }

    /**
     * 代码生成器生成下载文件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CommonController/downloadFileByJsonData")
    public void downloadFileByJsonData(InputObject inputObject, OutputObject outputObject) throws Exception {
        commonService.downloadFileByJsonData(inputObject, outputObject);
    }

    /**
     * 获取win系统桌列表信息供展示
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CommonController/querySysWinMationById")
    public void querySysWinMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        commonService.querySysWinMationById(inputObject, outputObject);
    }

    /**
     * 获取所有在职的，拥有账号的员工
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CommonController/queryAllSysUserIsIncumbency")
    public void queryAllSysUserIsIncumbency(InputObject inputObject, OutputObject outputObject) throws Exception {
        commonService.queryAllSysUserIsIncumbency(inputObject, outputObject);
    }

    /**
     * 根据文件类型获取文件的保存地址以及访问地址
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "queryFilePathByFileType", value = "根据文件类型获取文件的保存地址以及访问地址", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "fileType", name = "fileType", value = "文件类型", required = "required,num")})
    @RequestMapping("/post/CommonController/queryFilePathByFileType")
    public void queryFilePathByFileType(InputObject inputObject, OutputObject outputObject) throws Exception {
        commonService.queryFilePathByFileType(inputObject, outputObject);
    }

}
