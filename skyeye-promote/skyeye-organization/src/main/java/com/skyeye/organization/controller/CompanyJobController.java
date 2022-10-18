/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
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
     * 获取公司部门职位信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobController/queryCompanyJobList")
    public void queryCompanyJobList(InputObject inputObject, OutputObject outputObject) {
        companyJobService.queryCompanyJobList(inputObject, outputObject);
    }

    /**
     * 添加公司部门职位信息信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobController/insertCompanyJobMation")
    public void insertCompanyJobMation(InputObject inputObject, OutputObject outputObject) {
        companyJobService.insertCompanyJobMation(inputObject, outputObject);
    }

    /**
     * 删除公司部门职位信息信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobController/deleteCompanyJobMationById")
    public void deleteCompanyJobMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobService.deleteCompanyJobMationById(inputObject, outputObject);
    }

    /**
     * 编辑公司部门职位信息信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobController/queryCompanyJobMationToEditById")
    public void queryCompanyJobMationToEditById(InputObject inputObject, OutputObject outputObject) {
        companyJobService.queryCompanyJobMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑公司部门职位信息信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobController/editCompanyJobMationById")
    public void editCompanyJobMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobService.editCompanyJobMationById(inputObject, outputObject);
    }

    /**
     * 获取公司部门职位信息列表展示为树根据公司id
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
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
        @ApiImplicitParam(id = "departmentId", name = "departmentId", value = "部门id")})
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
    @RequestMapping("/post/CompanyJobController/queryCompanyJobSimpleListByToSelect")
    public void queryCompanyJobSimpleListByToSelect(InputObject inputObject, OutputObject outputObject) {
        companyJobService.queryCompanyJobSimpleListByToSelect(inputObject, outputObject);
    }

}