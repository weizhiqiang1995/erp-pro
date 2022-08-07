/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.wages.WagesStaffWorkTimeMation;
import com.skyeye.eve.service.WagesStaffMationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "员工薪资管理", tags = "员工薪资管理", modelName = "薪资模块")
public class WagesStaffMationController {

    @Autowired
    private WagesStaffMationService wagesStaffMationService;

    /**
     * 获取待设定薪资员工列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/WagesStaffMationController/queryWagesStaffWaitAllocatedMationList")
    public void queryWagesStaffWaitAllocatedMationList(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.queryWagesStaffWaitAllocatedMationList(inputObject, outputObject);
    }

    /**
     * 根据员工id获取该员工拥有的薪资字段
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/WagesStaffMationController/queryStaffWagesModelFieldMationListByStaffId")
    public void queryStaffWagesModelFieldMationListByStaffId(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.queryStaffWagesModelFieldMationListByStaffId(inputObject, outputObject);
    }

    /**
     * 保存员工薪资设定
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/WagesStaffMationController/saveStaffWagesModelFieldMation")
    public void saveStaffWagesModelFieldMation(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.saveStaffWagesModelFieldMation(inputObject, outputObject);
    }

    /**
     * 获取已设定薪资员工列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/WagesStaffMationController/queryWagesStaffDesignMationList")
    public void queryWagesStaffDesignMationList(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.queryWagesStaffDesignMationList(inputObject, outputObject);
    }

    /**
     * 获取员工薪资条薪资
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/WagesStaffMationController/queryWagesStaffPaymentDetail")
    public void queryWagesStaffPaymentDetail(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.queryWagesStaffPaymentDetail(inputObject, outputObject);
    }

    /**
     * 获取应出勤的班次以及小时
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "setLastMonthBe", value = "获取应出勤的班次以及小时", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = WagesStaffWorkTimeMation.class)
    @RequestMapping("/post/WagesStaffMationController/setLastMonthBe")
    public void setLastMonthBe(InputObject inputObject, OutputObject outputObject) {
        wagesStaffMationService.setLastMonthBe(inputObject, outputObject);
    }

}
