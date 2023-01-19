/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "公共接口", tags = "公共接口", modelName = "基础模块")
public class CommonController {

    @Autowired
    private CommonService commonService;

    /**
     * 上传文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "common003", value = "上传文件", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "type", name = "type", value = "文件类型", required = "required,num")})
    @RequestMapping("/post/CommonController/uploadFile")
    public void uploadFile(InputObject inputObject, OutputObject outputObject) {
        commonService.uploadFile(inputObject, outputObject);
    }

    /**
     * 上传文件Base64
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CommonController/uploadFileBase64")
    public void uploadFileBase64(InputObject inputObject, OutputObject outputObject) {
        commonService.uploadFileBase64(inputObject, outputObject);
    }

    /**
     * 代码生成器生成下载文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CommonController/downloadFileByJsonData")
    public void downloadFileByJsonData(InputObject inputObject, OutputObject outputObject) {
        commonService.downloadFileByJsonData(inputObject, outputObject);
    }

    /**
     * 获取win系统桌列表信息供展示
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CommonController/querySysWinMationById")
    public void querySysWinMationById(InputObject inputObject, OutputObject outputObject) {
        commonService.querySysWinMationById(inputObject, outputObject);
    }

    /**
     * 根据文件类型获取文件的保存地址以及访问地址
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryFilePathByFileType", value = "根据文件类型获取文件的保存地址以及访问地址", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "fileType", name = "fileType", value = "文件类型", required = "required,num")})
    @RequestMapping("/post/CommonController/queryFilePathByFileType")
    public void queryFilePathByFileType(InputObject inputObject, OutputObject outputObject) {
        commonService.queryFilePathByFileType(inputObject, outputObject);
    }

    /**
     * 验证接口是否正确
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformpage010", value = "验证接口是否正确", method = "P0ST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "interfa", name = "interfa", value = "数据")})
    @RequestMapping("/post/CommonController/queryInterfaceIsTrueOrNot")
    public void queryInterfaceIsTrueOrNot(InputObject inputObject, OutputObject outputObject) {
        commonService.queryInterfaceIsTrueOrNot(inputObject, outputObject);
    }

    /**
     * 获取接口中的值
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformpage011", value = "获取接口中的值", method = "P0ST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "interfa", name = "interfa", value = "数据")})
    @RequestMapping("/post/CommonController/queryInterfaceValue")
    public void queryInterfaceValue(InputObject inputObject, OutputObject outputObject) {
        commonService.queryInterfaceValue(inputObject, outputObject);
    }

}
