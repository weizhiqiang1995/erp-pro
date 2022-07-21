/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.PlanProjectFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanProjectFlowController {

    @Autowired
    private PlanProjectFlowService planProjectFlowService;

    /**
     * 获取项目规划-项目流程图表列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectFlowController/queryPlanProjectFlowList")
    public void queryPlanProjectFlowList(InputObject inputObject, OutputObject outputObject) {
        planProjectFlowService.queryPlanProjectFlowList(inputObject, outputObject);
    }

    /**
     * 添加项目规划-项目流程图表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectFlowController/insertPlanProjectFlowMation")
    public void insertPlanProjectFlowMation(InputObject inputObject, OutputObject outputObject) {
        planProjectFlowService.insertPlanProjectFlowMation(inputObject, outputObject);
    }

    /**
     * 删除项目规划-项目流程图表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectFlowController/deletePlanProjectFlowMationById")
    public void deletePlanProjectFlowMationById(InputObject inputObject, OutputObject outputObject) {
        planProjectFlowService.deletePlanProjectFlowMationById(inputObject, outputObject);
    }

    /**
     * 编辑项目规划-项目流程图表信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectFlowController/queryPlanProjectFlowMationToEditById")
    public void queryPlanProjectFlowMationToEditById(InputObject inputObject, OutputObject outputObject) {
        planProjectFlowService.queryPlanProjectFlowMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑项目规划-项目流程图表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectFlowController/editPlanProjectFlowMationById")
    public void editPlanProjectFlowMationById(InputObject inputObject, OutputObject outputObject) {
        planProjectFlowService.editPlanProjectFlowMationById(inputObject, outputObject);
    }

    /**
     * 获取项目流程图内容进行设计
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectFlowController/queryPlanProjectFlowJsonContentMationById")
    public void queryPlanProjectFlowJsonContentMationById(InputObject inputObject, OutputObject outputObject) {
        planProjectFlowService.queryPlanProjectFlowJsonContentMationById(inputObject, outputObject);
    }

    /**
     * 设计项目流程图
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/PlanProjectFlowController/editPlanProjectFlowJsonContentMationById")
    public void editPlanProjectFlowJsonContentMationById(InputObject inputObject, OutputObject outputObject) {
        planProjectFlowService.editPlanProjectFlowJsonContentMationById(inputObject, outputObject);
    }

}
