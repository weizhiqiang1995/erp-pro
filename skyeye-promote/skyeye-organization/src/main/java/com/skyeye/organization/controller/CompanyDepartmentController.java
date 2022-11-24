/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.organization.department.Department;
import com.skyeye.organization.service.CompanyDepartmentService;
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
     * 获取部门信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companydepartment001", value = "获取部门信息列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentList")
    public void queryCompanyDepartmentList(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑部门信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDepartmentMation", value = "添加/编辑公司部门信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Department.class)
    @RequestMapping("/post/CompanyDepartmentController/writeDepartmentMation")
    public void writeDepartmentMation(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除部门信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDepartmentMationById", value = "删除部门信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyDepartmentController/deleteCompanyDepartmentMationById")
    public void deleteCompanyDepartmentMationById(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询部门详情信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDepartmentMationById", value = "根据id查询部门详情信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyDepartmentController/queryDepartmentMationById")
    public void queryDepartmentMationById(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.selectById(inputObject, outputObject);
    }

    /**
     * 根据公司id获取部门信息列表展示为树
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companydepartment006", value = "根据公司id获取部门信息列表展示为树", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "companyId", name = "companyId", value = "公司id", required = "required")})
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentListTreeByCompanyId")
    public void queryCompanyDepartmentListTreeByCompanyId(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentListTreeByCompanyId(inputObject, outputObject);
    }

    /**
     * 根据公司id获取部门列表展示为下拉选择框
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companydepartment007", value = "根据公司id获取部门列表展示为下拉选择框", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "companyId", name = "companyId", value = "公司id", required = "required")})
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentListByCompanyIdToSelect")
    public void queryCompanyDepartmentListByCompanyIdToSelect(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentListByCompanyIdToSelect(inputObject, outputObject);
    }

    /**
     * 获取部门列表展示为表格供其他选择
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companydepartment008", value = "获取部门列表展示为表格供其他选择", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CompanyDepartmentController/queryCompanyDepartmentListToChoose")
    public void queryCompanyDepartmentListToChoose(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryCompanyDepartmentListToChoose(inputObject, outputObject);
    }

    /**
     * 根据部门ids获取部门信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDepartmentListByIds", value = "根据部门ids获取部门信息列表", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "部门ids，逗号隔开", required = "required")})
    @RequestMapping("/post/CompanyDepartmentController/queryDepartmentListByIds")
    public void queryDepartmentListByIds(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.selectByIds(inputObject, outputObject);
    }

    /**
     * 获取当前登录用户所属企业的所有部门信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDepartmentListByCurrentUserBelong", value = "获取当前登录用户所属企业的所有部门信息", method = "GET", allUse = "2")
    @RequestMapping("/post/CompanyDepartmentController/queryDepartmentListByCurrentUserBelong")
    public void queryDepartmentListByCurrentUserBelong(InputObject inputObject, OutputObject outputObject) {
        companyDepartmentService.queryDepartmentListByCurrentUserBelong(inputObject, outputObject);
    }

}
