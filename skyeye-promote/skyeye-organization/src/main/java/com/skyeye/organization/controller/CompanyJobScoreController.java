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
import com.skyeye.eve.entity.organization.jobscore.JobScore;
import com.skyeye.eve.entity.organization.jobscore.JobScoreQueryDo;
import com.skyeye.organization.service.CompanyJobScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CompanyJobScoreController
 * @Description: 职位定级管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/25 22:38
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "职位定级管理", tags = "职位定级管理", modelName = "组织模块")
public class CompanyJobScoreController {

    @Autowired
    private CompanyJobScoreService companyJobScoreService;

    /**
     * 获取职位定级信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjobscore001", value = "获取职位定级信息列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = JobScoreQueryDo.class)
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreList")
    public void queryCompanyJobScoreList(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCompanyJobScoreMation", value = "获取职位定级信息列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = JobScore.class)
    @RequestMapping("/post/CompanyJobScoreController/writeCompanyJobScoreMation")
    public void writeCompanyJobScoreMation(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjobscore003", value = "根据id获取职位定级信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreMationById")
    public void queryCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.selectById(inputObject, outputObject);
    }

    /**
     * 删除职位定级信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCompanyJobScoreMationById", value = "删除职位定级信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/CompanyJobScoreController/deleteCompanyJobScoreMationById")
    public void deleteCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取已经启用的职位定级信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "companyjobscore008", value = "获取已经启用的职位定级信息列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "jobId", name = "jobId", value = "职位id", required = "required")})
    @RequestMapping("/post/CompanyJobScoreController/queryEnableCompanyJobScoreList")
    public void queryEnableCompanyJobScoreList(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.queryEnableCompanyJobScoreList(inputObject, outputObject);
    }

    /**
     * 根据职位定级ids批量获取信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCompanyJobScoreListByIds", value = "根据职位定级ids批量获取信息列表", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "职位定级ids，逗号隔开", required = "required")})
    @RequestMapping("/post/CompanyJobScoreController/queryCompanyJobScoreListByIds")
    public void queryCompanyJobScoreListByIds(InputObject inputObject, OutputObject outputObject) {
        companyJobScoreService.selectByIds(inputObject, outputObject);
    }

}
