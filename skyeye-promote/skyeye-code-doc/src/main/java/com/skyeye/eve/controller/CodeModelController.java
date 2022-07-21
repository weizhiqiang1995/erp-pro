/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.codedoc.model.CodeModelQueryDo;
import com.skyeye.eve.service.CodeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CodeModelController
 * @Description: 代码模板管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/6 10:03
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "代码模板管理", tags = "代码模板管理", modelName = "代码生成器")
public class CodeModelController {

    @Autowired
    private CodeModelService codeModelService;

    /**
     * 获取模板列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "codemodel006", value = "获取模板列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CodeModelQueryDo.class)
    @RequestMapping("/post/CodeModelController/queryCodeModelList")
    public void queryCodeModelList(InputObject inputObject, OutputObject outputObject) {
        codeModelService.queryCodeModelList(inputObject, outputObject);
    }

    /**
     * 新增模板列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CodeModelController/insertCodeModelMation")
    public void insertCodeModelMation(InputObject inputObject, OutputObject outputObject) {
        codeModelService.insertCodeModelMation(inputObject, outputObject);
    }

    /**
     * 删除模板信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CodeModelController/deleteCodeModelById")
    public void deleteCodeModelById(InputObject inputObject, OutputObject outputObject) {
        codeModelService.deleteCodeModelById(inputObject, outputObject);
    }

    /**
     * 编辑模板信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CodeModelController/queryCodeModelMationToEditById")
    public void queryCodeModelMationToEditById(InputObject inputObject, OutputObject outputObject) {
        codeModelService.queryCodeModelMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑模板信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CodeModelController/editCodeModelMationById")
    public void editCodeModelMationById(InputObject inputObject, OutputObject outputObject) {
        codeModelService.editCodeModelMationById(inputObject, outputObject);
    }

}
