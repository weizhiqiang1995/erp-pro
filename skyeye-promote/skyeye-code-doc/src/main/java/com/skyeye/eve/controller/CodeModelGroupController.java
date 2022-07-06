/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.codedoc.group.CodeModelGroupQueryDo;
import com.skyeye.eve.service.CodeModelGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CodeModelGroupController
 * @Description: 模板分组管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/6 9:46
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "模板分组", tags = "模板分组", modelName = "代码生成器")
public class CodeModelGroupController {

    @Autowired
    private CodeModelGroupService codeModelGroupService;

    /**
     * 获取模板分组列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "codemodel001", value = "获取模板分组列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CodeModelGroupQueryDo.class)
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelGroupList")
    public void queryCodeModelGroupList(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryCodeModelGroupList(inputObject, outputObject);
    }

    /**
     * 新增模板分组列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelGroupController/insertCodeModelGroupMation")
    public void insertCodeModelGroupMation(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.insertCodeModelGroupMation(inputObject, outputObject);
    }

    /**
     * 删除模板分组信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelGroupController/deleteCodeModelGroupById")
    public void deleteCodeModelGroupById(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.deleteCodeModelGroupById(inputObject, outputObject);
    }

    /**
     * 编辑模板分组信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelGroupMationToEditById")
    public void queryCodeModelGroupMationToEditById(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryCodeModelGroupMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑模板分组信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelGroupController/editCodeModelGroupMationById")
    public void editCodeModelGroupMationById(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.editCodeModelGroupMationById(inputObject, outputObject);
    }

    /**
     * 根据表名获取表的相关信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelGroupController/queryTableParameterByTableName")
    public void queryTableParameterByTableName(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryTableParameterByTableName(inputObject, outputObject);
    }

    /**
     * 根据表名获取表的相关转换信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelGroupController/queryTableMationByTableName")
    public void queryTableMationByTableName(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryTableMationByTableName(inputObject, outputObject);
    }

    /**
     * 根据分组id获取模板列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelListByGroupId")
    public void queryCodeModelListByGroupId(InputObject inputObject, OutputObject outputObject) {
        codeModelGroupService.queryCodeModelListByGroupId(inputObject, outputObject);
    }

}
