/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.entity.DsFormComponent;
import com.skyeye.dsform.service.DsFormComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DsFormComponentController
 * @Description: 表单组件管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/24 18:59
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本组件仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "表单组件管理", tags = "表单组件管理", modelName = "动态表单模块")
public class DsFormComponentController {

    @Autowired
    private DsFormComponentService dsFormComponentService;

    /**
     * 获取动态表单组件列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDsFormComponentList", value = "获取动态表单组件列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DsFormComponentController/queryDsFormComponentList")
    public void queryDsFormComponentList(InputObject inputObject, OutputObject outputObject) {
        dsFormComponentService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑表单组件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDsFormComponent", value = "添加/编辑表单组件", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = DsFormComponent.class)
    @RequestMapping("/post/DsFormComponentController/writeDsFormComponent")
    public void writeDsFormComponent(InputObject inputObject, OutputObject outputObject) {
        dsFormComponentService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除动态表单组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDsFormComponentMationById", value = "删除动态表单组件信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DsFormComponentController/deleteDsFormComponentMationById")
    public void deleteDsFormComponentMationById(InputObject inputObject, OutputObject outputObject) {
        dsFormComponentService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取动态表单组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDsFormComponentMationById", value = "根据id获取动态表单组件信息", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DsFormComponentController/queryDsFormComponentMationById")
    public void queryDsFormComponentMationById(InputObject inputObject, OutputObject outputObject) {
        dsFormComponentService.selectById(inputObject, outputObject);
    }

    /**
     * 获取所有的动态表单组件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAllDsFormComponentList", value = "获取所有的动态表单组件", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "serviceClassName", name = "serviceClassName", value = "业务对象的服务层className", required = "required"),
        @ApiImplicitParam(id = "dsFormPageType", name = "dsFormPageType", value = "表单布局类型", required = "required")})
    @RequestMapping("/post/DsFormComponentController/queryAllDsFormComponentList")
    public void queryAllDsFormComponentList(InputObject inputObject, OutputObject outputObject) {
        dsFormComponentService.queryAllDsFormComponentList(inputObject, outputObject);
    }

}
