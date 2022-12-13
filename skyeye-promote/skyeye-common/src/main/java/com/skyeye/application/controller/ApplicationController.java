/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.application.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.application.entity.Application;
import com.skyeye.application.service.ApplicationService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ApplicationController
 * @Description: 应用管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/13 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "应用管理", tags = "应用管理", modelName = "系统公共模块")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    /**
     * 应用注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "registerApplication", value = "应用注册", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = Application.class)
    @RequestMapping("/post/SkyeyeClassServiceBeanController/registerApplication")
    public void registerApplication(InputObject inputObject, OutputObject outputObject) {
        applicationService.registerApplication(inputObject, outputObject);
    }

}
