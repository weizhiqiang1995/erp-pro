/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "公共接口", tags = "公共接口", modelName = "API模块")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 获取接口列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryAllSysEveReqMapping", value = "获取接口列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "appId", name = "appId", value = "微服务的APPID", required = "required")})
    @RequestMapping("/post/ApiController/queryAllSysEveReqMapping")
    public void queryAllSysEveReqMapping(InputObject inputObject, OutputObject outputObject) {
        apiService.queryAllSysEveReqMapping(inputObject, outputObject);
    }

    /**
     * 获取接口详情
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryApiDetails", value = "获取接口详情", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "appId", name = "appId", value = "微服务的APPID", required = "required"),
        @ApiImplicitParam(id = "rowId", name = "id", value = "接口id", required = "required")})
    @RequestMapping("/post/ApiController/queryApiDetails")
    public void queryApiDetails(InputObject inputObject, OutputObject outputObject) {
        apiService.queryApiDetails(inputObject, outputObject);
    }

    /**
     * 获取限制条件
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryLimitRestrictions", value = "获取限制条件", method = "GET", allUse = "2")
    @RequestMapping("/post/ApiController/queryLimitRestrictions")
    public void queryLimitRestrictions(InputObject inputObject, OutputObject outputObject) {
        apiService.queryLimitRestrictions(inputObject, outputObject);
    }

    /**
     * 获取所有微服务列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryApiMicroservices", value = "获取所有微服务列表", method = "GET", allUse = "2")
    @RequestMapping("/post/ApiController/queryApiMicroservices")
    public void queryApiMicroservices(InputObject inputObject, OutputObject outputObject) {
        apiService.queryApiMicroservices(inputObject, outputObject);
    }

}
