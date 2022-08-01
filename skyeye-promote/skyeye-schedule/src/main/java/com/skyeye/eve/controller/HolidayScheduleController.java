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
import com.skyeye.eve.service.HolidayScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HolidayScheduleController
 * @Description: 企业节假日安排控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/1 21:57
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "节假日管理", tags = "节假日管理", modelName = "日程模块")
public class HolidayScheduleController {

    @Autowired
    private HolidayScheduleService holidayScheduleService;

    /**
     * 获取系统发布的请假日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule008", value = "获取当前用户的日程信息", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "scheduleTitle", name = "scheduleTitle", value = "日程标题")})
    @RequestMapping("/post/HolidayScheduleController/queryHolidayScheduleList")
    public void queryHolidayScheduleList(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.queryHolidayScheduleList(inputObject, outputObject);
    }

    /**
     * 下载节假日导入模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule009", value = "下载节假日导入模板", method = "POST", allUse = "1")
    @RequestMapping("/post/HolidayScheduleController/downloadScheduleTemplate")
    public void downloadScheduleTemplate(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.downloadScheduleTemplate(inputObject, outputObject);
    }

    /**
     * 导入节假日日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule010", value = "导入节假日日程", method = "POST", allUse = "1")
    @RequestMapping("/post/HolidayScheduleController/exploreScheduleTemplate")
    public void exploreScheduleTemplate(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.exploreScheduleTemplate(inputObject, outputObject);
    }

    /**
     * 删除节假日日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule011", value = "删除节假日日程", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "节假日ID", required = "required")})
    @RequestMapping("/post/HolidayScheduleController/deleteHolidayScheduleById")
    public void deleteHolidayScheduleById(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.deleteHolidayScheduleById(inputObject, outputObject);
    }

    /**
     * 删除本年度节假日日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule012", value = "删除本年度节假日日程", method = "POST", allUse = "1")
    @RequestMapping("/post/HolidayScheduleController/deleteHolidayScheduleByThisYear")
    public void deleteHolidayScheduleByThisYear(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.deleteHolidayScheduleByThisYear(inputObject, outputObject);
    }

    /**
     * 添加节假日日程提醒
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule013", value = "添加节假日日程提醒", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "节假日ID", required = "required"),
        @ApiImplicitParam(id = "remindType", name = "remindType", value = "提醒时间所属类型  1.日程开始时 2.5分钟前 3.15分钟前 4.30分钟前 5.1小时前 6.2小时前 7.1天前 8.2天前 9.一周前", required = "required,num")})
    @RequestMapping("/post/HolidayScheduleController/addHolidayScheduleRemind")
    public void addHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.addHolidayScheduleRemind(inputObject, outputObject);
    }

    /**
     * 取消节假日日程提醒
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule014", value = "取消节假日日程提醒", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "节假日ID", required = "required")})
    @RequestMapping("/post/HolidayScheduleController/deleteHolidayScheduleRemind")
    public void deleteHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.deleteHolidayScheduleRemind(inputObject, outputObject);
    }

    /**
     * 回显节假日信息以编辑
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule015", value = "回显节假日信息以编辑", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "节假日ID", required = "required")})
    @RequestMapping("/post/HolidayScheduleController/queryScheduleByIdToEdit")
    public void queryScheduleByIdToEdit(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.queryScheduleByIdToEdit(inputObject, outputObject);
    }

    /**
     * 编辑节假日
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule016", value = "编辑节假日", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "节假日ID", required = "required"),
        @ApiImplicitParam(id = "title", name = "title", value = "节假日标题", required = "required"),
        @ApiImplicitParam(id = "startTime", name = "startTime", value = "开始时间", required = "required"),
        @ApiImplicitParam(id = "endTime", name = "endTime", value = "结束时间", required = "required")})
    @RequestMapping("/post/HolidayScheduleController/editScheduleById")
    public void editScheduleById(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.editScheduleById(inputObject, outputObject);
    }

    /**
     * 新增节假日
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule017", value = "新增节假日", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "title", name = "title", value = "节假日标题", required = "required"),
        @ApiImplicitParam(id = "startTime", name = "startTime", value = "开始时间", required = "required"),
        @ApiImplicitParam(id = "endTime", name = "endTime", value = "结束时间", required = "required")})
    @RequestMapping("/post/HolidayScheduleController/addSchedule")
    public void addSchedule(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.addSchedule(inputObject, outputObject);
    }

    /**
     * 获取所有节假日
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "syseveschedule018", value = "获取所有节假日", method = "POST", allUse = "2")
    @RequestMapping("/post/HolidayScheduleController/queryHolidayScheduleListBySys")
    public void queryHolidayScheduleListBySys(InputObject inputObject, OutputObject outputObject) {
        holidayScheduleService.queryHolidayScheduleListBySys(inputObject, outputObject);
    }

}
