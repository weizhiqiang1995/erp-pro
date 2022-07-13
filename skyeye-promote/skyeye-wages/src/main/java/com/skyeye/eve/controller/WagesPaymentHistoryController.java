/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.WagesPaymentHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WagesPaymentHistoryController {

    @Autowired
    private WagesPaymentHistoryService wagesPaymentHistoryService;

    /**
     * 获取所有已发放薪资发放历史列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesPaymentHistoryController/queryAllGrantWagesPaymentHistoryList")
    public void queryAllGrantWagesPaymentHistoryList(InputObject inputObject, OutputObject outputObject) {
        wagesPaymentHistoryService.queryAllGrantWagesPaymentHistoryList(inputObject, outputObject);
    }

    /**
     * 获取我的薪资发放历史列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesPaymentHistoryController/queryMyWagesPaymentHistoryList")
    public void queryMyWagesPaymentHistoryList(InputObject inputObject, OutputObject outputObject) {
        wagesPaymentHistoryService.queryMyWagesPaymentHistoryList(inputObject, outputObject);
    }

    /**
     * 获取所有待发放薪资列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesPaymentHistoryController/queryAllNotGrantWagesPaymentHistoryList")
    public void queryAllNotGrantWagesPaymentHistoryList(InputObject inputObject, OutputObject outputObject) {
        wagesPaymentHistoryService.queryAllNotGrantWagesPaymentHistoryList(inputObject, outputObject);
    }

}
