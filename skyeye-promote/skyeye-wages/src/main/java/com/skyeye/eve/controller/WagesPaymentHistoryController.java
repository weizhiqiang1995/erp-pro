/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.wages.WagesPaymentHistoryQueryDo;
import com.skyeye.eve.service.WagesPaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "薪资发放历史", tags = "薪资发放历史", modelName = "薪资模块")
public class WagesPaymentHistoryController {

    @Autowired
    private WagesPaymentHistoryService wagesPaymentHistoryService;

    /**
     * 获取所有已发放薪资发放历史列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "wagespaymenthistory001", value = "获取所有已发放薪资发放历史列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = WagesPaymentHistoryQueryDo.class)
    @RequestMapping("/post/WagesPaymentHistoryController/queryAllGrantWagesPaymentHistoryList")
    public void queryAllGrantWagesPaymentHistoryList(InputObject inputObject, OutputObject outputObject) {
        wagesPaymentHistoryService.queryAllGrantWagesPaymentHistoryList(inputObject, outputObject);
    }

    /**
     * 获取我的薪资发放历史列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/WagesPaymentHistoryController/queryMyWagesPaymentHistoryList")
    public void queryMyWagesPaymentHistoryList(InputObject inputObject, OutputObject outputObject) {
        wagesPaymentHistoryService.queryMyWagesPaymentHistoryList(inputObject, outputObject);
    }

    /**
     * 获取所有待发放薪资列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "wagespaymenthistory003", value = "获取所有待发放薪资列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = WagesPaymentHistoryQueryDo.class)
    @RequestMapping("/post/WagesPaymentHistoryController/queryAllNotGrantWagesPaymentHistoryList")
    public void queryAllNotGrantWagesPaymentHistoryList(InputObject inputObject, OutputObject outputObject) {
        wagesPaymentHistoryService.queryAllNotGrantWagesPaymentHistoryList(inputObject, outputObject);
    }

}
