/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.workplan.SysWorkPlanQueryDo;
import com.skyeye.eve.service.SysWorkPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysWorkPlanController
 * @Description: 工作计划管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "工作计划管理", tags = "工作计划管理", modelName = "工作计划模块")
public class SysWorkPlanController {

    @Autowired
    private SysWorkPlanService sysWorkPlanService;

    /**
     * 获取计划列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysworkplan001", value = "获取计划列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysWorkPlanQueryDo.class)
    @RequestMapping("/post/SysWorkPlanController/querySysWorkPlanList")
    public void querySysWorkPlanList(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.querySysWorkPlanList(inputObject, outputObject);
    }

    /**
     * 新增个人计划
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/insertSysWorkPlanISPeople")
    public void insertSysWorkPlanISPeople(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.insertSysWorkPlanISPeople(inputObject, outputObject);
    }

    /**
     * 新增部门计划
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/insertSysWorkPlanISDepartMent")
    public void insertSysWorkPlanISDepartMent(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.insertSysWorkPlanISDepartMent(inputObject, outputObject);
    }

    /**
     * 新增公司计划
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/insertSysWorkPlanISCompany")
    public void insertSysWorkPlanISCompany(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.insertSysWorkPlanISCompany(inputObject, outputObject);
    }

    /**
     * 取消定时发送
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/deleteSysWorkPlanTimingById")
    public void deleteSysWorkPlanTimingById(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.deleteSysWorkPlanTimingById(inputObject, outputObject);
    }

    /**
     * 删除计划
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/deleteSysWorkPlanById")
    public void deleteSysWorkPlanById(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.deleteSysWorkPlanById(inputObject, outputObject);
    }

    /**
     * 编辑计划时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/querySysWorkPlanToEditById")
    public void querySysWorkPlanToEditById(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.querySysWorkPlanToEditById(inputObject, outputObject);
    }

    /**
     * 编辑个人计划
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/editSysWorkPlanISPeople")
    public void editSysWorkPlanISPeople(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.editSysWorkPlanISPeople(inputObject, outputObject);
    }

    /**
     * 编辑部门计划或者公司计划
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/editSysWorkPlanISDepartMentOrCompany")
    public void editSysWorkPlanISDepartMentOrCompany(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.editSysWorkPlanISDepartMentOrCompany(inputObject, outputObject);
    }

    /**
     * 定时发送
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/editSysWorkPlanTimingSend")
    public void editSysWorkPlanTimingSend(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.editSysWorkPlanTimingSend(inputObject, outputObject);
    }

    /**
     * 计划详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/querySysWorkPlanDetailsById")
    public void querySysWorkPlanDetailsById(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.querySysWorkPlanDetailsById(inputObject, outputObject);
    }

    /**
     * 获取我的任务计划列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysworkplan013", value = "获取我的任务计划列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysWorkPlanQueryDo.class)
    @RequestMapping("/post/SysWorkPlanController/queryMySysWorkPlanListByUserId")
    public void queryMySysWorkPlanListByUserId(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.queryMySysWorkPlanListByUserId(inputObject, outputObject);
    }

    /**
     * 计划状态变更
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkPlanController/subEditWorkPlanStateById")
    public void subEditWorkPlanStateById(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.subEditWorkPlanStateById(inputObject, outputObject);
    }

    /**
     * 获取我创建的任务计划列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysworkplan015", value = "获取我创建的任务计划列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysWorkPlanQueryDo.class)
    @RequestMapping("/post/SysWorkPlanController/queryMyCreateSysWorkPlanList")
    public void queryMyCreateSysWorkPlanList(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.queryMyCreateSysWorkPlanList(inputObject, outputObject);
    }

    /**
     * 获取所有任务计划列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysworkplan016", value = "获取所有任务计划列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysWorkPlanQueryDo.class)
    @RequestMapping("/post/SysWorkPlanController/queryAllSysWorkPlanList")
    public void queryAllSysWorkPlanList(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.queryAllSysWorkPlanList(inputObject, outputObject);
    }

    /**
     * 获取我的下属的任务计划列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysworkplan017", value = "获取我的下属的任务计划列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysWorkPlanQueryDo.class)
    @RequestMapping("/post/SysWorkPlanController/queryMyBranchSysWorkPlanList")
    public void queryMyBranchSysWorkPlanList(InputObject inputObject, OutputObject outputObject) {
        sysWorkPlanService.queryMyBranchSysWorkPlanList(inputObject, outputObject);
    }

}
