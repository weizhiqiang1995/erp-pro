/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.coderule.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.coderule.entity.CodeMation;
import com.skyeye.coderule.entity.CodeRule;
import com.skyeye.coderule.service.CodeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CodeRuleController
 * @Description: 编码规则管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "编码管理", tags = "编码管理", modelName = "系统公共模块")
public class CodeRuleController {

    @Autowired
    private CodeRuleService codeRuleService;

    /**
     * 获取编码规则列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCodeRuleList", value = "获取编码规则列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CodeRuleController/queryCodeRuleList")
    public void queryCodeRuleList(InputObject inputObject, OutputObject outputObject) {
        codeRuleService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑编码规则
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCodeRuleMation", value = "新增/编辑编码规则", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CodeRule.class)
    @RequestMapping("/post/CodeRuleController/writeCodeRuleMation")
    public void writeCodeRuleMation(InputObject inputObject, OutputObject outputObject) {
        codeRuleService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据ID获取编码规则信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCodeRuleMationById", value = "根据ID获取编码规则信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CodeRuleController/queryCodeRuleMationById")
    public void queryCodeRuleMationById(InputObject inputObject, OutputObject outputObject) {
        codeRuleService.selectById(inputObject, outputObject);
    }

    /**
     * 根据ID删除编码规则
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCodeRuleMationById", value = "根据ID删除编码规则", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CodeRuleController/deleteCodeRuleMationById")
    public void deleteCodeRuleMationById(InputObject inputObject, OutputObject outputObject) {
        codeRuleService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取编码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getNextCodes", value = "获取编码", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = CodeMation.class)
    @RequestMapping("/post/CodeRuleController/getNextCodes")
    public void getNextCodes(InputObject inputObject, OutputObject outputObject) {
        codeRuleService.getNextCodes(inputObject, outputObject);
    }

    /**
     * 获取所有的编码规则
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getAllCodeRuleList", value = "获取所有的编码规则", method = "GET", allUse = "2")
    @RequestMapping("/post/CodeRuleController/getAllCodeRuleList")
    public void getAllCodeRuleList(InputObject inputObject, OutputObject outputObject) {
        codeRuleService.getAllCodeRuleList(inputObject, outputObject);
    }

}
