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
import com.skyeye.eve.entity.api.ApiMation;
import com.skyeye.service.ApiMationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "公共接口", tags = "公共接口", modelName = "API模块")
public class ApiMationController {

    @Autowired
    private ApiMationService apiMationService;

    /**
     * 新增/编辑api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeApiMation", value = "新增/编辑api接口信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ApiMation.class)
    @RequestMapping("/post/ApiMationController/writeApiMation")
    public void writeApiMation(InputObject inputObject, OutputObject outputObject) {
        apiMationService.writeApiMation(inputObject, outputObject);
    }

    /**
     * 删除api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteApiMationById", value = "删除api接口信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ApiMationController/deleteApiMationById")
    public void deleteApiMationById(InputObject inputObject, OutputObject outputObject) {
        apiMationService.deleteApiMationById(inputObject, outputObject);
    }

}
