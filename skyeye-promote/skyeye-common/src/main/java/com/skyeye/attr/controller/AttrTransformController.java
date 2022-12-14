/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.attr.entity.AttrTransform;
import com.skyeye.attr.service.AttrTransformService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: AttrTransformServiceImpl
 * @Description: 提交到流程的属性信息管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "提交到流程的属性信息管理", tags = "提交到流程的属性信息管理", modelName = "系统公共模块")
public class AttrTransformController {

    @Autowired
    private AttrTransformService attrTransformService;

    /**
     * 根据service的className获取提交到流程的属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAttrTransformList", value = "根据service的className获取提交到流程的属性信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required")})
    @RequestMapping("/post/AttrTransformController/queryAttrTransformList")
    public void queryAttrTransformList(InputObject inputObject, OutputObject outputObject) {
        attrTransformService.queryList(inputObject, outputObject);
    }

    /**
     * 新增/编辑提交到流程的属性
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAttrTransform", value = "新增/编辑提交到流程的属性", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = AttrTransform.class)
    @RequestMapping("/post/AttrTransformController/writeAttrTransform")
    public void writeAttrTransform(InputObject inputObject, OutputObject outputObject) {
        attrTransformService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除提交到流程的属性
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteAttrTransformById", value = "删除提交到流程的属性", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AttrTransformController/deleteAttrTransformById")
    public void deleteAttrTransformById(InputObject inputObject, OutputObject outputObject) {
        attrTransformService.deleteById(inputObject, outputObject);
    }

    /**
     * 查询提交到流程的属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAttrTransformById", value = "查询提交到流程的属性信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/AttrTransformController/queryAttrTransformById")
    public void queryAttrTransformById(InputObject inputObject, OutputObject outputObject) {
        attrTransformService.selectById(inputObject, outputObject);
    }

}
