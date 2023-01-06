/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.server.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.server.entity.ServiceBeanApi;
import com.skyeye.server.service.ServiceBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ServiceBeanController
 * @Description: 所有实现了SkyeyeBusinessService的服务类的注册服务
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "服务类注册", tags = "服务类注册", modelName = "系统公共模块")
public class ServiceBeanController {

    @Autowired
    private ServiceBeanService serviceBeanService;

    /**
     * 服务类注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "registerServiceBean", value = "服务类注册", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = ServiceBeanApi.class)
    @RequestMapping("/post/ServiceBeanController/registerServiceBean")
    public void registerServiceBean(InputObject inputObject, OutputObject outputObject) {
        serviceBeanService.registerServiceBean(inputObject, outputObject);
    }

    /**
     * 获取服务类信息(树结构)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryServiceClassForTree", value = "获取服务类信息(树结构)", method = "GET", allUse = "2")
    @RequestMapping("/post/ServiceBeanController/queryServiceClassForTree")
    public void queryServiceClassForTree(InputObject inputObject, OutputObject outputObject) {
        serviceBeanService.queryServiceClassForTree(inputObject, outputObject);
    }

}
