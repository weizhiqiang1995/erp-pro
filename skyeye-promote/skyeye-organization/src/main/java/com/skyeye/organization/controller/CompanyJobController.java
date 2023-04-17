/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.organization.job.CompanyJob;
import com.skyeye.organization.service.CompanyJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "职位管理", tags = "职位管理", modelName = "组织模块")
public class CompanyJobController {

    @Autowired
    private CompanyJobService companyJobService;

    /**
     * 获取职位信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjob001", value = "获取职位信息列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TableSelectInfo.class)
    @RequestMapping("/post/CompanyJobController/queryCompanyJobList")
    public void queryCompanyJobList(InputObject inputObject, OutputObject outputObject) {
        companyJobService.queryList(inputObject, outputObject);
    }

    /**
     * 添加/编辑职位信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCompanyJobMation", value = "添加/编辑职位信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CompanyJob.class)
    @RequestMapping("/post/CompanyJobController/writeCompanyJobMation")
    public void writeCompanyJobMation(InputObject inputObject, OutputObject outputObject) {
        companyJobService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除职位信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCompanyJobMationById", value = "删除职位信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyJobController/deleteCompanyJobMationById")
    public void deleteCompanyJobMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取职位信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjob004", value = "根据id获取职位信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyJobController/queryCompanyJobMationToEditById")
    public void queryCompanyJobMationToEditById(InputObject inputObject, OutputObject outputObject) {
        companyJobService.selectById(inputObject, outputObject);
    }

    /**
     * 根据部门id获取该部门所有的职位信息展示为树
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjob006", value = "根据部门id获取该部门所有的职位信息展示为树", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "departmentId", name = "departmentId", value = "部门id", required = "required")})
    @RequestMapping("/post/CompanyJobController/queryCompanyJobListTreeByDepartmentId")
    public void queryCompanyJobListTreeByDepartmentId(InputObject inputObject, OutputObject outputObject) {
        companyJobService.queryCompanyJobListTreeByDepartmentId(inputObject, outputObject);
    }

    /**
     * 根据部门id获取职位列表展示为下拉选择框
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjob007", value = "根据部门id获取职位列表展示为下拉选择框", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "departmentId", name = "departmentId", value = "部门id", required = "required")})
    @RequestMapping("/post/CompanyJobController/queryCompanyJobListByToSelect")
    public void queryCompanyJobListByToSelect(InputObject inputObject, OutputObject outputObject) {
        companyJobService.queryCompanyJobListByToSelect(inputObject, outputObject);
    }

    /**
     * 根据部门id获取职位同级列表且不包含当前id的值展示为下拉选择框
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjob008", value = "根据部门id获取职位同级列表且不包含当前id的值展示为下拉选择框", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "departmentId", name = "departmentId", value = "部门id", required = "required"),
        @ApiImplicitParam(id = "id", name = "id", value = "不包含的职位id"),
        @ApiImplicitParam(id = "pId", name = "pId", value = "父职位id", required = "required")})
    @RequestMapping("/post/CompanyJobController/queryCompanyJobSimpleListByToSelect")
    public void queryCompanyJobSimpleListByToSelect(InputObject inputObject, OutputObject outputObject) {
        companyJobService.queryCompanyJobSimpleListByToSelect(inputObject, outputObject);
    }

    /**
     * 根据岗位ids获取岗位信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryJobListByIds", value = "根据岗位ids获取岗位信息列表", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "岗位ids，逗号隔开", required = "required")})
    @RequestMapping("/post/CompanyJobController/queryJobListByIds")
    public void queryJobListByIds(InputObject inputObject, OutputObject outputObject) {
        companyJobService.selectByIds(inputObject, outputObject);
    }

}
