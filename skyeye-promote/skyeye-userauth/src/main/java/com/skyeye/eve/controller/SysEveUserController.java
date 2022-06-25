/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.eve.entity.userauth.user.SysUserMation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveUserService;

/**
 * @ClassName: SysEveUserController
 * @Description: 系统用户管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/13 9:51
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Controller
@Api(value = "用户管理", tags = "用户管理", modelName = "基础模块")
public class SysEveUserController {

    @Autowired
    public SysEveUserService sysEveUserService;

    /**
     * 获取管理员用户列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "sys001", value = "获取用户列表", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "limit", name = "limit", value = "分页参数,每页多少条数据", required = "required,num"),
        @ApiImplicitParam(id = "page", name = "page", value = "分页参数,第几页", required = "required,num"),
        @ApiImplicitParam(id = "userCode", name = "userCode", value = "用户账号"),
        @ApiImplicitParam(id = "userName", name = "userName", value = "员工姓名"),
        @ApiImplicitParam(id = "sexName", name = "sexName", value = "员工性别"),
        @ApiImplicitParam(id = "companyName", name = "companyName", value = "公司"),
        @ApiImplicitParam(id = "departmentName", name = "departmentName", value = "部门"),
        @ApiImplicitParam(id = "jobName", name = "jobName", value = "职位")})
    @RequestMapping("/post/SysEveUserController/querySysUserList")
    @ResponseBody
    public void querySysUserList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.querySysUserList(inputObject, outputObject);
    }

    /**
     * 锁定账号
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "sys002", value = "锁定账号", method = "PUT", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required")})
    @RequestMapping("/post/SysEveUserController/editSysUserLockStateToLockById")
    @ResponseBody
    public void editSysUserLockStateToLockById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editSysUserLockStateToLockById(inputObject, outputObject);
    }

    /**
     * 解锁账号
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "sys003", value = "解锁账号", method = "PUT", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required")})
    @RequestMapping("/post/SysEveUserController/editSysUserLockStateToUnLockById")
    @ResponseBody
    public void editSysUserLockStateToUnLockById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editSysUserLockStateToUnLockById(inputObject, outputObject);
    }

    /**
     * 创建账号
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "sysAdd005", value = "创建账号", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysUserMation.class)
    @RequestMapping("/post/SysEveUserController/insertSysUserMationById")
    @ResponseBody
    public void insertSysUserMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.insertSysUserMationById(inputObject, outputObject);
    }

    /**
     * 重置密码
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "sys005", value = "重置密码", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required"),
        @ApiImplicitParam(id = "password", name = "password", value = "密码", required = "required")})
    @RequestMapping("/post/SysEveUserController/editSysUserPasswordMationById")
    @ResponseBody
    public void editSysUserPasswordMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editSysUserPasswordMationById(inputObject, outputObject);
    }

    /**
     * 登录
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/queryUserToLogin")
    @ResponseBody
    public void queryUserToLogin(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryUserToLogin(inputObject, outputObject);
    }

    /**
     * 从session中获取用户信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/queryUserMationBySession")
    @ResponseBody
    public void queryUserMationBySession(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryUserMationBySession(inputObject, outputObject);
    }

    /**
     * 退出
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "login003", value = "退出", method = "POST", allUse = "2")
    @RequestMapping("/post/SysEveUserController/deleteUserMationBySession")
    @ResponseBody
    public void deleteUserMationBySession(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.deleteUserMationBySession(inputObject, outputObject);
    }

    /**
     * 获取角色和当前已经绑定的角色信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/queryRoleAndBindRoleByUserId")
    @ResponseBody
    public void queryRoleAndBindRoleByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryRoleAndBindRoleByUserId(inputObject, outputObject);
    }

    /**
     * 编辑用户绑定的角色
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "sys020", value = "编辑用户绑定的角色", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required"),
        @ApiImplicitParam(id = "roleIds", name = "roleIds", value = "角色ID串，逗号隔开", required = "required")})
    @RequestMapping("/post/SysEveUserController/editRoleIdsByUserId")
    @ResponseBody
    public void editRoleIdsByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editRoleIdsByUserId(inputObject, outputObject);
    }

    /**
     * 获取桌面菜单列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "login004", value = "获取桌面菜单列表", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveUserController/queryDeskTopMenuBySession")
    @ResponseBody
    public void queryDeskTopMenuBySession(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryDeskTopMenuBySession(inputObject, outputObject);
    }

    /**
     * 获取全部菜单列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/queryAllMenuBySession")
    @ResponseBody
    public void queryAllMenuBySession(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryAllMenuBySession(inputObject, outputObject);
    }

    /**
     * 自定义设置主题颜色
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallThemeColor")
    @ResponseBody
    public void editUserInstallThemeColor(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserInstallThemeColor(inputObject, outputObject);
    }

    /**
     * 自定义设置win背景图片
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinBgPic")
    @ResponseBody
    public void editUserInstallWinBgPic(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserInstallWinBgPic(inputObject, outputObject);
    }

    /**
     * 自定义设置win锁屏背景图片
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinLockBgPic")
    @ResponseBody
    public void editUserInstallWinLockBgPic(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserInstallWinLockBgPic(inputObject, outputObject);
    }

    /**
     * 自定义设置win开始菜单尺寸
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinStartMenuSize")
    @ResponseBody
    public void editUserInstallWinStartMenuSize(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserInstallWinStartMenuSize(inputObject, outputObject);
    }

    /**
     * 自定义设置win任务栏在屏幕的位置
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinTaskPosition")
    @ResponseBody
    public void editUserInstallWinTaskPosition(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserInstallWinTaskPosition(inputObject, outputObject);
    }

    /**
     * 修改密码
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserPassword")
    @ResponseBody
    public void editUserPassword(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserPassword(inputObject, outputObject);
    }

    /**
     * 自定义设置win雾化
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallVagueBgSrc")
    @ResponseBody
    public void editUserInstallVagueBgSrc(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserInstallVagueBgSrc(inputObject, outputObject);
    }

    /**
     * 自定义设置窗口下面展示的是图标还是图标+文字
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallLoadMenuIconById")
    @ResponseBody
    public void editUserInstallLoadMenuIconById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserInstallLoadMenuIconById(inputObject, outputObject);
    }

    /**
     * 锁屏密码解锁
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/queryUserLockByLockPwd")
    @ResponseBody
    public void queryUserLockByLockPwd(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryUserLockByLockPwd(inputObject, outputObject);
    }

    /**
     * 修改个人信息时获取数据回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/queryUserDetailsMationByUserId")
    @ResponseBody
    public void queryUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryUserDetailsMationByUserId(inputObject, outputObject);
    }

    /**
     * 修改个人信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/editUserDetailsMationByUserId")
    @ResponseBody
    public void editUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.editUserDetailsMationByUserId(inputObject, outputObject);
    }

    /**
     * 获取还没有分配账号的员工
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/querySysUserListByUserName")
    @ResponseBody
    public void querySysUserListByUserName(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.querySysUserListByUserName(inputObject, outputObject);
    }

    /**
     * 获取该用户拥有的桌面
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveUserController/querySysDeskTopByUserId")
    @ResponseBody
    public void querySysDeskTopByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.querySysDeskTopByUserId(inputObject, outputObject);
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "queryUserMationByUserId", value = "根据用户id获取用户信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "userId", name = "userId", value = "用户id(账号ID)", required = "required")})
    @RequestMapping("/post/SysEveUserController/queryUserMationByUserId")
    @ResponseBody
    public void queryUserMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveUserService.queryUserMationByUserId(inputObject, outputObject);
    }

}
