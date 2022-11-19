/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.entity.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.organization.company.Company;
import com.skyeye.organization.service.CompanyMationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CompanyMationController
 * @Description: 企业管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/18 10:50
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "企业管理", tags = "企业管理", modelName = "组织模块")
public class CompanyMationController {

    @Autowired
    private CompanyMationService companyMationService;

    /**
     * 获取公司信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companymation001", value = "获取公司信息列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TableSelectInfo.class)
    @RequestMapping("/post/CompanyMationController/queryCompanyMationList")
    public void queryCompanyMationList(InputObject inputObject, OutputObject outputObject) {
        companyMationService.queryList(inputObject, outputObject);
    }

    /**
     * 添加/编辑公司信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCompanyMation", value = "添加/编辑公司信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Company.class)
    @RequestMapping("/post/CompanyMationController/insertCompanyMation")
    public void insertCompanyMation(InputObject inputObject, OutputObject outputObject) {
        companyMationService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除公司信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCompanyMationById", value = "删除公司信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyMationController/deleteCompanyMationById")
    public void deleteCompanyMationById(InputObject inputObject, OutputObject outputObject) {
        companyMationService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查询公司信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCompanyMationById", value = "根据id查询公司信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyMationController/queryCompanyMationById")
    public void queryCompanyMationById(InputObject inputObject, OutputObject outputObject) {
        companyMationService.selectById(inputObject, outputObject);
    }

    /**
     * 获取总公司信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyMationController/queryOverAllCompanyMationList")
    public void queryOverAllCompanyMationList(InputObject inputObject, OutputObject outputObject) {
        companyMationService.queryOverAllCompanyMationList(inputObject, outputObject);
    }

    /**
     * 获取公司信息列表展示为树
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCompanyMationListTree", value = "获取公司信息列表展示为树", method = "POST", allUse = "2")
    @RequestMapping("/post/CompanyMationController/queryCompanyMationListTree")
    public void queryCompanyMationListTree(InputObject inputObject, OutputObject outputObject) {
        companyMationService.queryCompanyMationListTree(inputObject, outputObject);
    }

    /**
     * 获取公司列表展示为下拉选择框
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companymation008", value = "获取公司列表展示为下拉选择框", method = "GET", allUse = "2")
    @RequestMapping("/post/CompanyMationController/queryCompanyListToSelect")
    public void queryCompanyListToSelect(InputObject inputObject, OutputObject outputObject) {
        companyMationService.queryCompanyListToSelect(inputObject, outputObject);
    }

    /**
     * 获取企业组织机构图
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companymation009", value = "获取公司列表展示为下拉选择框", method = "GET", allUse = "1")
    @RequestMapping("/post/CompanyMationController/queryCompanyOrganization")
    public void queryCompanyOrganization(InputObject inputObject, OutputObject outputObject) {
        companyMationService.queryCompanyOrganization(inputObject, outputObject);
    }

    /**
     * 获取公司信息列表展示为表格供其他需要选择
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companymation010", value = "获取公司信息列表展示为表格供其他需要选择", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/CompanyMationController/queryCompanyMationListToChoose")
    public void queryCompanyMationListToChoose(InputObject inputObject, OutputObject outputObject) {
        companyMationService.queryPageList(inputObject, outputObject);
    }

}
