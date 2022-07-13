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
import com.skyeye.eve.service.CompanyDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CompanyDepartmentController
 * @Description: 部门管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/4/8 19:40
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "部门管理", tags = "部门管理", modelName = "组织模块")
public class CompanyDepartmentController {

    @Autowired
    private CompanyDepartmentService companyDepartmentService;

    /**
     * 获取公司部门信息列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentList")
    public void queryCompanyDepartmentList(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentList(inputObject, outputObject);
    }

    /**
     * 添加公司部门信息信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/insertCompanyDepartmentMation")
    public void insertCompanyDepartmentMation(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.insertCompanyDepartmentMation(inputObject, outputObject);
    }

    /**
     * 删除公司部门信息信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/deleteCompanyDepartmentMationById")
    public void deleteCompanyDepartmentMationById(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.deleteCompanyDepartmentMationById(inputObject, outputObject);
    }

    /**
     * 编辑公司部门信息信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentMationToEditById")
    public void queryCompanyDepartmentMationToEditById(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑公司部门信息信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/editCompanyDepartmentMationById")
    public void editCompanyDepartmentMationById(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.editCompanyDepartmentMationById(inputObject, outputObject);
    }

    /**
     * 获取公司部门信息列表展示为树根据公司id
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentListTreeByCompanyId")
    public void queryCompanyDepartmentListTreeByCompanyId(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentListTreeByCompanyId(inputObject, outputObject);
    }

    /**
     * 根据公司id获取部门列表展示为下拉选择框
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentListByCompanyIdToSelect")
    public void queryCompanyDepartmentListByCompanyIdToSelect(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentListByCompanyIdToSelect(inputObject, outputObject);
    }

    /**
     * 获取部门列表展示为表格供其他选择
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentListToChoose")
    public void queryCompanyDepartmentListToChoose(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentListToChoose(inputObject, outputObject);
    }

    /**
     * 根据部门ids获取部门信息列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "companydepartment009", value = "根据部门ids获取部门信息列表", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "部门ids，逗号隔开", required = "required")})
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentListByIds")
    public void queryCompanyDepartmentListByIds(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentListByIds(inputObject, outputObject);
    }

    /**
     * 获取当前登录用户所属企业的所有部门信息
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryDepartmentListByCurrentUserBelong", value = "获取当前登录用户所属企业的所有部门信息", method = "GET", allUse = "2")
    @RequestMapping("/post/CompanyDepartmentController/queryDepartmentListByCurrentUserBelong")
    public void queryDepartmentListByCurrentUserBelong(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryDepartmentListByCurrentUserBelong(inputObject, outputObject);
    }

}
