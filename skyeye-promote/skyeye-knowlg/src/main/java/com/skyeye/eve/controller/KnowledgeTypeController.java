/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.eve.entity.knowlg.KnowledgeTypeMation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.KnowledgeTypeService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: KnowledgeTypeController
 * @Description: 知识库类型管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/21 10:35
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "知识库类型", tags = "知识库类型", modelName = "知识库模块")
public class KnowledgeTypeController {

    @Autowired
    private KnowledgeTypeService knowledgeTypeService;

    /**
     * 获取知识库类型列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype001", value = "获取知识库类型列表", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "name", name = "name", value = "知识库类型名称"),
        @ApiImplicitParam(id = "state", name = "state", value = "上线状态", required = "num"),
        @ApiImplicitParam(id = "notId", name = "notId", value = "不包含在查询列表中的数据")})
    @RequestMapping("/post/KnowledgeTypeController/queryKnowledgeTypeList")
    public void queryKnowledgeTypeList(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.queryKnowledgeTypeList(inputObject, outputObject);
    }


    /**
     * 添加知识库类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype002", value = "新增知识库类型", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = KnowledgeTypeMation.class)
    @RequestMapping("/post/KnowledgeTypeController/insertKnowledgeTypeMation")
    public void insertKnowledgeTypeMation(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.insertKnowledgeTypeMation(inputObject, outputObject);
    }

    /**
     * 删除知识库类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype003", value = "删除知识库类型", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库类型id", required = "required")})
    @RequestMapping("/post/KnowledgeTypeController/deleteKnowledgeTypeById")
    public void deleteKnowledgeTypeById(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.deleteKnowledgeTypeById(inputObject, outputObject);
    }

    /**
     * 上线知识库类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype004", value = "上线知识库类型", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库类型id", required = "required")})
    @RequestMapping("/post/KnowledgeTypeController/updateUpKnowledgeTypeById")
    public void updateUpKnowledgeTypeById(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.updateUpKnowledgeTypeById(inputObject, outputObject);
    }

    /**
     * 下线知识库类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype005", value = "下线知识库类型", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库类型id", required = "required")})
    @RequestMapping("/post/KnowledgeTypeController/updateDownKnowledgeTypeById")
    public void updateDownKnowledgeTypeById(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.updateDownKnowledgeTypeById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的知识库类型信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype006", value = "通过id查找对应的知识库类型信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库类型id", required = "required")})
    @RequestMapping("/post/KnowledgeTypeController/selectKnowledgeTypeById")
    public void selectKnowledgeTypeById(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.selectKnowledgeTypeById(inputObject, outputObject);
    }

    /**
     * 编辑知识库类型
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype007", value = "编辑知识库类型", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "知识库类型id", required = "required"),
        @ApiImplicitParam(id = "name", name = "name", value = "知识库类型名称", required = "required")})
    @RequestMapping("/post/KnowledgeTypeController/editKnowledgeTypeMationById")
    public void editKnowledgeTypeMationById(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.editKnowledgeTypeMationById(inputObject, outputObject);
    }

    /**
     * 获取已经上线的知识库类型，数据为tree格式
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "knowledgetype008", value = "获取已经上线的知识库类型，数据为tree格式", method = "GET", allUse = "2")
    @RequestMapping("/post/KnowledgeTypeController/queryUpKnowledgeTypeTreeMation")
    public void queryUpKnowledgeTypeTreeMation(InputObject inputObject, OutputObject outputObject) {
        knowledgeTypeService.queryUpKnowledgeTypeTreeMation(inputObject, outputObject);
    }

}
