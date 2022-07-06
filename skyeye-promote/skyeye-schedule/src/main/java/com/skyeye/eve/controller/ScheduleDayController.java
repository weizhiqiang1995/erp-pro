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
import com.skyeye.eve.entity.schedule.OtherModuleScheduleMation;
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
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/insertScheduleDayMation")
    public void insertScheduleDayMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.insertScheduleDayMation(inputObject, outputObject);
    }

    /**
     * 获取当前用户的日程信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "syseveschedule002", value = "获取当前用户的日程信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "yearMonth", name = "yearMonth", value = "指定年月：YYYY-MM", required = "required"),
        @ApiImplicitParam(id = "checkWorkId", name = "checkWorkId", value = "班次id", required = "required")})
    @RequestMapping("/post/ScheduleDayController/queryScheduleDayMationByUserId")
    public void queryScheduleDayMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.queryScheduleDayMationByUserId(inputObject, outputObject);
    }

    /**
     * 根据用户获取今日日程信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/queryScheduleDayMationTodayByUserId")
    public void queryScheduleDayMationTodayByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.queryScheduleDayMationTodayByUserId(inputObject, outputObject);
    }

    /**
     * 修改日程日期信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/editScheduleDayMationById")
    public void editScheduleDayMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.editScheduleDayMationById(inputObject, outputObject);
    }

    /**
     * 获取日程详细信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/queryScheduleDayMationById")
    public void queryScheduleDayMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.queryScheduleDayMationById(inputObject, outputObject);
    }

    /**
     * 删除日程信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/deleteScheduleDayMationById")
    public void deleteScheduleDayMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.deleteScheduleDayMationById(inputObject, outputObject);
    }

    /**
     * 获取系统发布的请假日程
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/queryHolidayScheduleList")
    public void queryHolidayScheduleList(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.queryHolidayScheduleList(inputObject, outputObject);
    }

    /**
     * 下载节假日导入模板
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/downloadScheduleTemplate")
    public void downloadScheduleTemplate(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.downloadScheduleTemplate(inputObject, outputObject);
    }

    /**
     * 导入节假日日程
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/exploreScheduleTemplate")
    public void exploreScheduleTemplate(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.exploreScheduleTemplate(inputObject, outputObject);
    }

    /**
     * 删除节假日日程
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/deleteHolidayScheduleById")
    public void deleteHolidayScheduleById(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.deleteHolidayScheduleById(inputObject, outputObject);
    }

    /**
     * 删除本年度节假日日程
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/deleteHolidayScheduleByThisYear")
    public void deleteHolidayScheduleByThisYear(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.deleteHolidayScheduleByThisYear(inputObject, outputObject);
    }

    /**
     * 添加节假日日程提醒
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/addHolidayScheduleRemind")
    public void addHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.addHolidayScheduleRemind(inputObject, outputObject);
    }

    /**
     * 取消节假日日程提醒
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/deleteHolidayScheduleRemind")
    public void deleteHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.deleteHolidayScheduleRemind(inputObject, outputObject);
    }

    /**
     * 回显节假日信息以编辑
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/queryScheduleByIdToEdit")
    public void queryScheduleByIdToEdit(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.queryScheduleByIdToEdit(inputObject, outputObject);
    }

    /**
     * 编辑节假日
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/editScheduleById")
    public void editScheduleById(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.editScheduleById(inputObject, outputObject);
    }

    /**
     * 新增节假日
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/addSchedule")
    public void addSchedule(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.addSchedule(inputObject, outputObject);
    }

    /**
     * 获取所有节假日
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/queryHolidayScheduleListBySys")
    public void queryHolidayScheduleListBySys(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.queryHolidayScheduleListBySys(inputObject, outputObject);
    }

    /**
     * 获取我的日程
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ScheduleDayController/queryMyScheduleList")
    public void queryMyScheduleList(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.queryMyScheduleList(inputObject, outputObject);
    }

    /**
     * 其他模块同步到日程
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "insertScheduleMationByOtherModule", value = "其他模块同步到日程", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = OtherModuleScheduleMation.class)
    @RequestMapping("/post/ScheduleDayController/insertScheduleMationByOtherModule")
    public void insertScheduleMationByOtherModule(InputObject inputObject, OutputObject outputObject) throws Exception {
        scheduleDayService.insertScheduleMationByOtherModule(inputObject, outputObject);
    }

}
