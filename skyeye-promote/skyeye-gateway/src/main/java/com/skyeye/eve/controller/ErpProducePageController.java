/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.ErpProducePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErpProducePageController {

    @Autowired
    private ErpProducePageService erpProducePageService;

    /**
     * 统计当前部门月度领料图
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/ErpProducePageController/queryDepartmentPickMaterial")
    public void queryDepartmentPickMaterial(InputObject inputObject, OutputObject outputObject) {
        erpProducePageService.queryDepartmentPickMaterial(inputObject, outputObject);
    }

    /**
     * 统计当前部门月度补料图
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/ErpProducePageController/queryDepartmentPatchMaterial")
    public void queryDepartmentPatchMaterial(InputObject inputObject, OutputObject outputObject) {
        erpProducePageService.queryDepartmentPatchMaterial(inputObject, outputObject);
    }

    /**
     * 统计当前部门月度退料图
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/ErpProducePageController/queryDepartmentReturnMaterial")
    public void queryDepartmentReturnMaterial(InputObject inputObject, OutputObject outputObject) {
        erpProducePageService.queryDepartmentReturnMaterial(inputObject, outputObject);
    }

    /**
     * 统计当前部门月度新建加工单图
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/ErpProducePageController/queryDepartmentMachin")
    public void queryDepartmentMachin(InputObject inputObject, OutputObject outputObject) {
        erpProducePageService.queryDepartmentMachin(inputObject, outputObject);
    }

}
