/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CompanyJobScoreService;
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
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreList")
    public void queryCompanyJobScoreList(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.queryCompanyJobScoreList(inputObject, outputObject);
    }

    /**
     * 新增职位定级信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/insertCompanyJobScoreMation")
    public void insertCompanyJobScoreMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.insertCompanyJobScoreMation(inputObject, outputObject);
    }

    /**
     * 编辑职位定级信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreMationToEditById")
    public void queryCompanyJobScoreMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.queryCompanyJobScoreMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑职位定级信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/editCompanyJobScoreMationById")
    public void editCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.editCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 删除职位定级信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/deleteCompanyJobScoreMationById")
    public void deleteCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.deleteCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 启用职位定级信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/enableCompanyJobScoreMationById")
    public void enableCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.enableCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 禁用职位定级信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/disableCompanyJobScoreMationById")
    public void disableCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.disableCompanyJobScoreMationById(inputObject, outputObject);
    }

    /**
     * 获取已经启用的职位定级信息列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/queryEnableCompanyJobScoreList")
    public void queryEnableCompanyJobScoreList(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.queryEnableCompanyJobScoreList(inputObject, outputObject);
    }

    /**
     * 获取职位定级信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreDetailMationById")
    public void queryCompanyJobScoreDetailMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyJobScoreService.queryCompanyJobScoreDetailMationById(inputObject, outputObject);
    }

}
