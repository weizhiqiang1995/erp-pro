/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.schedule.ScheduleDayQueryDo;
import com.skyeye.eve.rest.schedule.OtherModuleScheduleMation;
import com.skyeye.eve.rest.schedule.ScheduleMation;
import com.skyeye.eve.service.ScheduleDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "日程管理", tags = "日程管理", modelName = "日程模块")
public class ScheduleDayController {

    @Autowired
    private ScheduleDayService scheduleDayService;

    /**
     * 添加日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule001", value = "添加日程信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = ScheduleMation.class)
    @RequestMapping("/post/ScheduleDayController/insertScheduleDayMation")
    public void insertScheduleDayMation(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.insertScheduleDayMation(inputObject, outputObject);
    }

    /**
     * 获取当前用户的日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule002", value = "获取当前用户的日程信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "yearMonth", name = "yearMonth", value = "指定年月：YYYY-MM", required = "required"),
        @ApiImplicitParam(id = "checkWorkId", name = "checkWorkId", value = "班次id", required = "required")})
    @RequestMapping("/post/ScheduleDayController/queryScheduleDayMationByUserId")
    public void queryScheduleDayMationByUserId(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.queryScheduleDayMationByUserId(inputObject, outputObject);
    }

    /**
     * 根据用户获取今日日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ScheduleDayController/queryScheduleDayMationTodayByUserId")
    public void queryScheduleDayMationTodayByUserId(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.queryScheduleDayMationTodayByUserId(inputObject, outputObject);
    }

    /**
     * 修改日程日期信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ScheduleDayController/editScheduleDayMationById")
    public void editScheduleDayMationById(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.editScheduleDayMationById(inputObject, outputObject);
    }

    /**
     * 获取日程详细信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ScheduleDayController/queryScheduleDayMationById")
    public void queryScheduleDayMationById(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.queryScheduleDayMationById(inputObject, outputObject);
    }

    /**
     * 删除日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ScheduleDayController/deleteScheduleDayMationById")
    public void deleteScheduleDayMationById(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.deleteScheduleDayMationById(inputObject, outputObject);
    }

    /**
     * 获取我的日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule019", value = "获取我的日程", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = ScheduleDayQueryDo.class)
    @RequestMapping("/post/ScheduleDayController/queryMyScheduleList")
    public void queryMyScheduleList(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.queryMyScheduleList(inputObject, outputObject);
    }

    /**
     * 其他模块同步到日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertScheduleMationByOtherModule", value = "其他模块同步到日程", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = OtherModuleScheduleMation.class)
    @RequestMapping("/post/ScheduleDayController/insertScheduleMationByOtherModule")
    public void insertScheduleMationByOtherModule(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.insertScheduleMationByOtherModule(inputObject, outputObject);
    }

    /**
     * 根据ObjectId删除日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteScheduleMationByObjectId", value = "根据ObjectId删除日程", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "日程关联ID", required = "required")})
    @RequestMapping("/post/ScheduleDayController/deleteScheduleMationByObjectId")
    public void deleteScheduleMationByObjectId(InputObject inputObject, OutputObject outputObject) {
        scheduleDayService.deleteScheduleMationByObjectId(inputObject, outputObject);
    }

}
