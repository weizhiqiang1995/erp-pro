/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.organization.service.CompanyJobScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyJobScoreController {

    @Autowired
    private CompanyJobScoreService companyJobScoreService;

    /**
     * 获取职位定级信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreList")
    public void queryCompanyJobScoreList(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.queryCompanyJobScoreList(inputObject, outputObject);
    }

    /**
     * 新增职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/insertCompanyJobScoreMation")
    public void insertCompanyJobScoreMation(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.insertCompanyJobScoreMation(inputObject, outputObject);
    }

    /**
     * 编辑职位定级信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreMationToEditById")
    public void queryCompanyJobScoreMationToEditById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.queryCompanyJobScoreMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/editCompanyJobScoreMationById")
    public void editCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.editCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 删除职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/deleteCompanyJobScoreMationById")
    public void deleteCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.deleteCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 启用职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/enableCompanyJobScoreMationById")
    public void enableCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.enableCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 禁用职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/disableCompanyJobScoreMationById")
    public void disableCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.disableCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 获取已经启用的职位定级信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/queryEnableCompanyJobScoreList")
    public void queryEnableCompanyJobScoreList(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.queryEnableCompanyJobScoreList(inputObject, outputObject);
    }

    /**
     * 获取职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreDetailMationById")
    public void queryCompanyJobScoreDetailMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.queryCompanyJobScoreDetailMationById(inputObject, outputObject);
    }

}