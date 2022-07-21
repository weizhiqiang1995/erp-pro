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
import com.skyeye.eve.entity.userauth.user.SysUserStaffMation;
import com.skyeye.eve.entity.userauth.user.SysUserStaffQueryDo;
import com.skyeye.eve.service.SysEveUserStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveUserStaffController
 * @Description: 员工管理开始
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/12 23:28
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "员工管理", tags = "员工管理", modelName = "基础模块")
public class SysEveUserStaffController {

    @Autowired
    private SysEveUserStaffService sysEveUserStaffService;

    /**
     * 获取员工列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff001", value = "查看所有员工列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysUserStaffQueryDo.class)
    @RequestMapping("/post/SysEveUserStaffController/querySysUserStaffList")
    public void querySysUserStaffList(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.querySysUserStaffList(inputObject, outputObject);
    }

    /**
     * 新增员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff002", value = "新增员工信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = SysUserStaffMation.class)
    @RequestMapping("/post/SysEveUserStaffController/insertSysUserStaffMation")
    public void insertSysUserStaffMation(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.insertSysUserStaffMation(inputObject, outputObject);
    }

    /**
     * 通过id查询一条员工信息回显编辑
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff003", value = "通过id查询一条员工信息回显编辑", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "员工id", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/querySysUserStaffById")
    public void querySysUserStaffById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.querySysUserStaffById(inputObject, outputObject);
    }

    /**
     * 编辑员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff004", value = "编辑员工信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysUserStaffMation.class, value = {
        @ApiImplicitParam(id = "rowId", name = "id", value = "员工id", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/editSysUserStaffById")
    public void editSysUserStaffById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.editSysUserStaffById(inputObject, outputObject);
    }

    /**
     * 通过id查询一条员工信息展示详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff005", value = "通过id查询一条员工信息展示详情", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "员工id", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/querySysUserStaffByIdToDetails")
    public void querySysUserStaffByIdToDetails(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.querySysUserStaffByIdToDetails(inputObject, outputObject);
    }

    /**
     * 员工离职
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff006", value = "员工离职", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "员工id", required = "required"),
        @ApiImplicitParam(id = "quitTime", name = "quitTime", value = "离职时间"),
        @ApiImplicitParam(id = "quitReason", name = "quitReason", value = "离职原因")})
    @RequestMapping("/post/SysEveUserStaffController/editSysUserStaffState")
    public void editSysUserStaffState(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.editSysUserStaffState(inputObject, outputObject);
    }

    /**
     * 普通员工转教职工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserStaffController/editTurnTeacher")
    public void editTurnTeacher(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.editTurnTeacher(inputObject, outputObject);
    }

    /**
     * 查看所有员工列表展示为表格供其他选择
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff008", value = "查看所有员工列表展示为表格供其他选择", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "userName", name = "userName", value = "员工姓名"),
        @ApiImplicitParam(id = "userIdCard", name = "userIdCard", value = "身份证"),
        @ApiImplicitParam(id = "userSex", name = "userSex", value = "员工性别  0保密   1男  2女"),
        @ApiImplicitParam(id = "companyName", name = "companyName", value = "公司"),
        @ApiImplicitParam(id = "departmentName", name = "departmentName", value = "部门"),
        @ApiImplicitParam(id = "jobName", name = "jobName", value = "职位")})
    @RequestMapping("/post/SysEveUserStaffController/querySysUserStaffListToTable")
    public void querySysUserStaffListToTable(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.querySysUserStaffListToTable(inputObject, outputObject);
    }

    /**
     * 根据员工ids获取员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserStaffController/querySysUserStaffListByIds")
    public void querySysUserStaffListByIds(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.querySysUserStaffListByIds(inputObject, outputObject);
    }

    /**
     * 获取当前登录员工的信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "staff010", value = "获取当前登录员工的信息", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveUserStaffController/querySysUserStaffLogin")
    public void querySysUserStaffLogin(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.querySysUserStaffLogin(inputObject, outputObject);
    }

    /**
     * 根据用户ids获取用户信息集合
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryUserNameList", value = "根据用户ids获取用户信息集合", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "userIds", name = "userIds", value = "用户id")})
    @RequestMapping("/post/SysEveUserStaffController/queryUserNameList")
    public void queryUserNameList(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.queryUserNameList(inputObject, outputObject);
    }

    /**
     * 通过用户id查询一条员工信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySysUserStaffDetailsByUserId", value = "通过用户id查询一条员工信息", method = "GET", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "userId", name = "userId", value = "用户id", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/querySysUserStaffDetailsByUserId")
    public void querySysUserStaffDetailsByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.querySysUserStaffDetailsByUserId(inputObject, outputObject);
    }

    /**
     * 修改员工剩余年假信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "editSysUserStaffAnnualLeaveById", value = "修改员工剩余年假信息", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "staffId", name = "staffId", value = "员工id", required = "required"),
        @ApiImplicitParam(id = "quarterYearHour", name = "quarterYearHour", value = "年假,精确到6位", required = "required"),
        @ApiImplicitParam(id = "annualLeaveStatisTime", name = "annualLeaveStatisTime", value = "员工剩余年假数据刷新日期", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/editSysUserStaffAnnualLeaveById")
    public void editSysUserStaffAnnualLeaveById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.editSysUserStaffAnnualLeaveById(inputObject, outputObject);
    }

    /**
     * 修改员工的补休池剩余补休信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "updateSysUserStaffHolidayNumberById", value = "修改员工的补休池剩余补休信息", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "staffId", name = "staffId", value = "员工id", required = "required"),
        @ApiImplicitParam(id = "holidayNumber", name = "holidayNumber", value = "当前员工剩余补休天数", required = "required"),
        @ApiImplicitParam(id = "holidayStatisTime", name = "holidayStatisTime", value = "员工剩余补休数据刷新日期", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/updateSysUserStaffHolidayNumberById")
    public void updateSysUserStaffHolidayNumberById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.updateSysUserStaffHolidayNumberById(inputObject, outputObject);
    }

    /**
     * 修改员工的补休池已休补休信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "updateSysUserStaffRetiredHolidayNumberById", value = "修改员工的补休池已休补休信息", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "staffId", name = "staffId", value = "员工id", required = "required"),
        @ApiImplicitParam(id = "retiredHolidayNumber", name = "retiredHolidayNumber", value = "当前员工已休补休天数", required = "required"),
        @ApiImplicitParam(id = "retiredHolidayStatisTime", name = "retiredHolidayStatisTime", value = "员工已休补休数据刷新日期", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/updateSysUserStaffRetiredHolidayNumberById")
    public void updateSysUserStaffRetiredHolidayNumberById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.updateSysUserStaffRetiredHolidayNumberById(inputObject, outputObject);
    }

    /**
     * 根据员工id获取该员工的考勤时间段
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryStaffCheckWorkTimeRelationNameByStaffId", value = "根据员工id获取该员工的考勤时间段", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "staffId", name = "staffId", value = "员工id", required = "required")})
    @RequestMapping("/post/SysEveUserStaffController/queryStaffCheckWorkTimeRelationNameByStaffId")
    public void queryStaffCheckWorkTimeRelationNameByStaffId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffService.queryStaffCheckWorkTimeRelationNameByStaffId(inputObject, outputObject);
    }

}
