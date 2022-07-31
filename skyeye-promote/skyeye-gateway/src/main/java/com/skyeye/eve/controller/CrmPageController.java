/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CrmPageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "CRM统计", tags = "CRM统计", modelName = "统计模块")
public class CrmPageController {

    @Autowired
    private CrmPageService crmPageService;

    /**
     * 获取指定年度的客户新增量，联系人新增量
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CrmPageController/queryInsertNumByYear")
    public void queryInsertNumByYear(InputObject inputObject, OutputObject outputObject) {
        crmPageService.queryInsertNumByYear(inputObject, outputObject);
    }

    /**
     * 根据客户分类，客户来源，所属行业，客户分组统计客户数量
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "crmpage002", value = "根据客户分类，客户来源，所属行业，客户分组统计客户数量", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "crmCustomerType", name = "crmCustomerType", value = "客户分类的code", required = "required"),
        @ApiImplicitParam(id = "crmCustomerFrom", name = "crmCustomerFrom", value = "客户来源的code", required = "required"),
        @ApiImplicitParam(id = "crmCustomerIndustry", name = "crmCustomerIndustry", value = "所属行业的code", required = "required"),
        @ApiImplicitParam(id = "crmCustomerGroup", name = "crmCustomerGroup", value = "客户分组的code", required = "required")})
    @RequestMapping("/post/CrmPageController/queryCustomNumByOtherType")
    public void queryCustomNumByOtherType(InputObject inputObject, OutputObject outputObject) {
        crmPageService.queryCustomNumByOtherType(inputObject, outputObject);
    }

    /**
     * 客户跟单方式分析
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "crmpage003", value = "客户跟单方式分析", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "year", name = "year", value = "指定年度", required = "required"),
        @ApiImplicitParam(id = "crmDocumentaryType", name = "crmDocumentaryType", value = "跟单方式code", required = "required")})
    @RequestMapping("/post/CrmPageController/queryCustomDocumentaryType")
    public void queryCustomDocumentaryType(InputObject inputObject, OutputObject outputObject) {
        crmPageService.queryCustomDocumentaryType(inputObject, outputObject);
    }

    /**
     * 获取合同在指定年度的月新增量
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CrmPageController/queryNewContractNum")
    public void queryNewContractNum(InputObject inputObject, OutputObject outputObject) {
        crmPageService.queryNewContractNum(inputObject, outputObject);
    }

    /**
     * 获取员工跟单在指定年度的月新增量
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/CrmPageController/queryNewDocumentaryNum")
    public void queryNewDocumentaryNum(InputObject inputObject, OutputObject outputObject) {
        crmPageService.queryNewDocumentaryNum(inputObject, outputObject);
    }

}
