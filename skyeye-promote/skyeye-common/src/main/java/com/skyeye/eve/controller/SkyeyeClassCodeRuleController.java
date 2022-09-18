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
import com.skyeye.eve.entity.coderule.SkyeyeClassCodeRuleApiMation;
import com.skyeye.eve.service.SkyeyeClassCodeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SkyeyeClassCodeRuleController
 * @Description: 需要获取编码的服务类服务服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "获取编码的服务类管理", tags = "获取编码的服务类管理", modelName = "系统公共模块")
public class SkyeyeClassCodeRuleController {

    @Autowired
    private SkyeyeClassCodeRuleService skyeyeClassCodeRuleService;

    /**
     * 批量新增需要获取编码的服务类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeClassCodeRule", value = "批量新增需要获取编码的服务类", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = SkyeyeClassCodeRuleApiMation.class)
    @RequestMapping("/post/SkyeyeClassCodeRuleController/writeClassCodeRule")
    public void writeClassCodeRule(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassCodeRuleService.writeClassCodeRule(inputObject, outputObject);
    }

    /**
     * 获取需要获取编码的服务类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getClassCodeRuleData", value = "获取需要获取编码的服务类", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "className", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "appId")})
    @RequestMapping("/post/SkyeyeClassCodeRuleController/getClassCodeRuleData")
    public void getClassCodeRuleData(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassCodeRuleService.getClassCodeRuleData(inputObject, outputObject);
    }

}
