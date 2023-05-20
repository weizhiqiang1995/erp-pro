/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.clazz.entity.classflowable.SkyeyeClassFlowableLinkApiMation;
import com.skyeye.clazz.service.SkyeyeClassFlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SkyeyeClassFlowableController
 * @Description: 工作流业务对象服务管理 todo 待删除
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/20 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "工作流业务对象服务管理", tags = "工作流业务对象服务管理", modelName = "系统公共模块")
public class SkyeyeClassFlowableController {

    @Autowired
    private SkyeyeClassFlowableService skyeyeClassFlowableService;

    /**
     * 批量新增工作流业务对象服务---common层使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeClassFlowable", value = "批量新增工作流业务对象服务", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = SkyeyeClassFlowableLinkApiMation.class)
    @RequestMapping("/post/SkyeyeClassFlowableController/writeClassFlowable")
    public void writeClassFlowable(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassFlowableService.writeClassFlowable(inputObject, outputObject);
    }

    /**
     * 获取工作流业务对象服务---common层使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getClassFlowableData", value = "获取工作流业务对象服务", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "className", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "appId", required = "required")})
    @RequestMapping("/post/SkyeyeClassFlowableController/getClassFlowableData")
    public void getClassFlowableData(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassFlowableService.getClassFlowableData(inputObject, outputObject);
    }

    /**
     * 获取所有工作流业务对象服务列表---前台使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryClassFlowableDataList", value = "获取所有工作流业务对象服务列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SkyeyeClassFlowableController/queryClassFlowableDataList")
    public void queryClassFlowableDataList(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassFlowableService.queryClassFlowableDataList(inputObject, outputObject);
    }

}
