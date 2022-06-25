/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CodeModelGroupService;

@Controller
public class CodeModelGroupController {

    @Autowired
    private CodeModelGroupService codeModelGroupService;

    /**
     * 获取模板分组列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelGroupList")
    @ResponseBody
    public void queryCodeModelGroupList(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.queryCodeModelGroupList(inputObject, outputObject);
    }

    /**
     * 新增模板分组列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/insertCodeModelGroupMation")
    @ResponseBody
    public void insertCodeModelGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.insertCodeModelGroupMation(inputObject, outputObject);
    }

    /**
     * 删除模板分组信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/deleteCodeModelGroupById")
    @ResponseBody
    public void deleteCodeModelGroupById(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.deleteCodeModelGroupById(inputObject, outputObject);
    }

    /**
     * 编辑模板分组信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelGroupMationToEditById")
    @ResponseBody
    public void queryCodeModelGroupMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.queryCodeModelGroupMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑模板分组信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/editCodeModelGroupMationById")
    @ResponseBody
    public void editCodeModelGroupMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.editCodeModelGroupMationById(inputObject, outputObject);
    }

    /**
     * 根据表名获取表的相关信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/queryTableParameterByTableName")
    @ResponseBody
    public void queryTableParameterByTableName(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.queryTableParameterByTableName(inputObject, outputObject);
    }

    /**
     * 根据表名获取表的相关转换信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/queryTableMationByTableName")
    @ResponseBody
    public void queryTableMationByTableName(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.queryTableMationByTableName(inputObject, outputObject);
    }

    /**
     * 根据分组id获取模板列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CodeModelGroupController/queryCodeModelListByGroupId")
    @ResponseBody
    public void queryCodeModelListByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception {
        codeModelGroupService.queryCodeModelListByGroupId(inputObject, outputObject);
    }

}
