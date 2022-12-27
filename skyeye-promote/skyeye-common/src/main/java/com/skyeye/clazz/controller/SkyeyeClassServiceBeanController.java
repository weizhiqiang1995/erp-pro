/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.clazz.entity.classservice.SkyeyeClassServiceBeanApi;
import com.skyeye.clazz.service.SkyeyeClassServiceBeanService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SkyeyeClassServiceBeanController
 * @Description: 所有实现了SkyeyeBusinessService的服务类的注册服务
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "服务类注册", tags = "服务类注册", modelName = "系统公共模块")
public class SkyeyeClassServiceBeanController {

    @Autowired
    private SkyeyeClassServiceBeanService skyeyeClassServiceBeanService;

    /**
     * 服务类注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "registerServiceBean", value = "服务类注册", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = SkyeyeClassServiceBeanApi.class)
    @RequestMapping("/post/SkyeyeClassServiceBeanController/registerServiceBean")
    public void registerServiceBean(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassServiceBeanService.registerServiceBean(inputObject, outputObject);
    }

    /**
     * 获取服务类信息(树结构)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryServiceClassForTree", value = "获取服务类信息(树结构)", method = "GET", allUse = "2")
    @RequestMapping("/post/SkyeyeClassServiceBeanController/queryServiceClassForTree")
    public void queryServiceClassForTree(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassServiceBeanService.queryServiceClassForTree(inputObject, outputObject);
    }

    /**
     * 获取服务类详情信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryServiceClass", value = "获取服务类详情信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "业务对象的className", required = "required")})
    @RequestMapping("/post/SkyeyeClassServiceBeanController/queryServiceClass")
    public void queryServiceClass(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassServiceBeanService.queryServiceClass(inputObject, outputObject);
    }

}