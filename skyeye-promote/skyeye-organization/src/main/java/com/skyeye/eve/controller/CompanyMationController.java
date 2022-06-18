/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CompanyMationService;
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
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/queryCompanyMationList")
    public void queryCompanyMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryCompanyMationList(inputObject, outputObject);
    }

    /**
     * 添加公司信息信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/insertCompanyMation")
    public void insertCompanyMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.insertCompanyMation(inputObject, outputObject);
    }

    /**
     * 删除公司信息信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/deleteCompanyMationById")
    public void deleteCompanyMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.deleteCompanyMationById(inputObject, outputObject);
    }

    /**
     * 编辑公司信息信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/queryCompanyMationToEditById")
    public void queryCompanyMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryCompanyMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑公司信息信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/editCompanyMationById")
    public void editCompanyMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.editCompanyMationById(inputObject, outputObject);
    }

    /**
     * 获取总公司信息列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/queryOverAllCompanyMationList")
    public void queryOverAllCompanyMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryOverAllCompanyMationList(inputObject, outputObject);
    }

    /**
     * 获取公司信息列表展示为树
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "queryCompanyMationListTree", value = "获取公司信息列表展示为树", method = "POST", allUse = "2")
    @RequestMapping("/post/CompanyMationController/queryCompanyMationListTree")
    public void queryCompanyMationListTree(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryCompanyMationListTree(inputObject, outputObject);
    }

    /**
     * 获取公司列表展示为下拉选择框
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/queryCompanyListToSelect")
    public void queryCompanyListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryCompanyListToSelect(inputObject, outputObject);
    }

    /**
     * 获取企业组织机构图
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/queryCompanyOrganization")
    public void queryCompanyOrganization(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryCompanyOrganization(inputObject, outputObject);
    }

    /**
     * 获取公司信息列表展示为表格供其他需要选择
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/queryCompanyMationListToChoose")
    public void queryCompanyMationListToChoose(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryCompanyMationListToChoose(inputObject, outputObject);
    }

    /**
     * 根据公司ids获取公司信息列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyMationController/queryCompanyMationListByIds")
    public void queryCompanyMationListByIds(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyMationService.queryCompanyMationListByIds(inputObject, outputObject);
    }

}
