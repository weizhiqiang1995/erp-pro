/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.userauth.user.SysUserMation;
import com.skyeye.eve.entity.userauth.user.SysUserQueryDo;
import com.skyeye.eve.entity.userauth.user.UserTreeQueryDo;
import com.skyeye.personnel.service.SysEveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveUserController
 * @Description: 系统用户管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/13 9:51
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "用户管理", tags = "用户管理", modelName = "基础模块")
public class SysEveUserController {

    @Autowired
    public SysEveUserService sysEveUserService;

    /**
     * 获取管理员用户列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys001", value = "获取用户列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysUserQueryDo.class)
    @RequestMapping("/post/SysEveUserController/querySysUserList")
    public void querySysUserList(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.querySysUserList(inputObject, outputObject);
    }

    /**
     * 锁定账号
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys002", value = "锁定账号", method = "PUT", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required")})
    @RequestMapping("/post/SysEveUserController/editSysUserLockStateToLockById")
    public void editSysUserLockStateToLockById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editSysUserLockStateToLockById(inputObject, outputObject);
    }

    /**
     * 解锁账号
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys003", value = "解锁账号", method = "PUT", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required")})
    @RequestMapping("/post/SysEveUserController/editSysUserLockStateToUnLockById")
    public void editSysUserLockStateToUnLockById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editSysUserLockStateToUnLockById(inputObject, outputObject);
    }

    /**
     * 创建账号
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysAdd005", value = "创建账号", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysUserMation.class)
    @RequestMapping("/post/SysEveUserController/insertSysUserMationById")
    public void insertSysUserMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.insertSysUserMationById(inputObject, outputObject);
    }

    /**
     * 重置密码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys005", value = "重置密码", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required"),
        @ApiImplicitParam(id = "password", name = "password", value = "密码", required = "required")})
    @RequestMapping("/post/SysEveUserController/editSysUserPasswordMationById")
    public void editSysUserPasswordMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editSysUserPasswordMationById(inputObject, outputObject);
    }

    /**
     * 登录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/queryUserToLogin")
    public void queryUserToLogin(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryUserToLogin(inputObject, outputObject);
    }

    /**
     * 从session中获取用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/queryUserMationBySession")
    public void queryUserMationBySession(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryUserMationBySession(inputObject, outputObject);
    }

    /**
     * 退出
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "login003", value = "退出", method = "POST", allUse = "2")
    @RequestMapping("/post/SysEveUserController/deleteUserMationBySession")
    public void deleteUserMationBySession(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.deleteUserMationBySession(inputObject, outputObject);
    }

    /**
     * 获取角色和当前已经绑定的角色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/queryRoleAndBindRoleByUserId")
    public void queryRoleAndBindRoleByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryRoleAndBindRoleByUserId(inputObject, outputObject);
    }

    /**
     * 编辑用户绑定的角色
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys020", value = "编辑用户绑定的角色", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "账号ID", required = "required"),
        @ApiImplicitParam(id = "roleIds", name = "roleIds", value = "角色ID串，逗号隔开", required = "required")})
    @RequestMapping("/post/SysEveUserController/editRoleIdsByUserId")
    public void editRoleIdsByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editRoleIdsByUserId(inputObject, outputObject);
    }

    /**
     * 获取当前登录用户的桌面菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "login004", value = "获取当前登录用户的桌面菜单列表", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveUserController/queryDeskTopMenuBySession")
    public void queryDeskTopMenuBySession(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryDeskTopMenuBySession(inputObject, outputObject);
    }

    /**
     * 获取全部菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "login005", value = "获取当前登录用户的桌面菜单列表", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveUserController/queryAllMenuBySession")
    public void queryAllMenuBySession(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryAllMenuBySession(inputObject, outputObject);
    }

    /**
     * 自定义设置主题颜色
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallThemeColor")
    public void editUserInstallThemeColor(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserInstallThemeColor(inputObject, outputObject);
    }

    /**
     * 自定义设置win背景图片
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinBgPic")
    public void editUserInstallWinBgPic(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserInstallWinBgPic(inputObject, outputObject);
    }

    /**
     * 自定义设置win锁屏背景图片
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinLockBgPic")
    public void editUserInstallWinLockBgPic(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserInstallWinLockBgPic(inputObject, outputObject);
    }

    /**
     * 自定义设置win开始菜单尺寸
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinStartMenuSize")
    public void editUserInstallWinStartMenuSize(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserInstallWinStartMenuSize(inputObject, outputObject);
    }

    /**
     * 自定义设置win任务栏在屏幕的位置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallWinTaskPosition")
    public void editUserInstallWinTaskPosition(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserInstallWinTaskPosition(inputObject, outputObject);
    }

    /**
     * 修改密码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserPassword")
    public void editUserPassword(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserPassword(inputObject, outputObject);
    }

    /**
     * 自定义设置win雾化
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallVagueBgSrc")
    public void editUserInstallVagueBgSrc(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserInstallVagueBgSrc(inputObject, outputObject);
    }

    /**
     * 自定义设置窗口下面展示的是图标还是图标+文字
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserInstallLoadMenuIconById")
    public void editUserInstallLoadMenuIconById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserInstallLoadMenuIconById(inputObject, outputObject);
    }

    /**
     * 锁屏密码解锁
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/queryUserLockByLockPwd")
    public void queryUserLockByLockPwd(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryUserLockByLockPwd(inputObject, outputObject);
    }

    /**
     * 修改个人信息时获取数据回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/queryUserDetailsMationByUserId")
    public void queryUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryUserDetailsMationByUserId(inputObject, outputObject);
    }

    /**
     * 修改个人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/editUserDetailsMationByUserId")
    public void editUserDetailsMationByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.editUserDetailsMationByUserId(inputObject, outputObject);
    }

    /**
     * 获取还没有分配账号的员工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/querySysUserListByUserName")
    public void querySysUserListByUserName(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.querySysUserListByUserName(inputObject, outputObject);
    }

    /**
     * 获取该用户拥有的桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserController/querySysDeskTopByUserId")
    public void querySysDeskTopByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.querySysDeskTopByUserId(inputObject, outputObject);
    }

    /**
     * 根据用户id获取桌面菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDeskTopsMenuByUserId", value = "根据用户id获取桌面菜单信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "userId", name = "userId", value = "用户id(账号ID)", required = "required")})
    @RequestMapping("/post/SysEveUserController/queryDeskTopsMenuByUserId")
    public void queryDeskTopsMenuByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryDeskTopsMenuByUserId(inputObject, outputObject);
    }

    /**
     * 人员选择获取所有公司和人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "commonselpeople001", value = "人员选择获取所有公司和人", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = UserTreeQueryDo.class)
    @RequestMapping("/post/SysEveUserController/queryAllPeopleToTree")
    public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryAllPeopleToTree(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "commonselpeople002", value = "人员选择根据当前用户所属公司获取这个公司的人", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = UserTreeQueryDo.class)
    @RequestMapping("/post/SysEveUserController/queryCompanyPeopleToTreeByUserBelongCompany")
    public void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryCompanyPeopleToTreeByUserBelongCompany(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司部门展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "commonselpeople003", value = "人员选择根据当前用户所属公司获取这个公司部门展示的人", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = UserTreeQueryDo.class)
    @RequestMapping("/post/SysEveUserController/queryDepartmentPeopleToTreeByUserBelongDepartment")
    public void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryDepartmentPeopleToTreeByUserBelongDepartment(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司岗位展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "commonselpeople004", value = "人员选择根据当前用户所属公司获取这个公司岗位展示的人", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = UserTreeQueryDo.class)
    @RequestMapping("/post/SysEveUserController/queryJobPeopleToTreeByUserBelongJob")
    public void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryJobPeopleToTreeByUserBelongJob(inputObject, outputObject);
    }

    /**
     * 人员选择根据当前用户所属公司获取这个公司同级部门展示的人
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "commonselpeople005", value = "人员选择根据当前用户所属公司获取这个公司同级部门展示的人", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = UserTreeQueryDo.class)
    @RequestMapping("/post/SysEveUserController/querySimpleDepPeopleToTreeByUserBelongSimpleDep")
    public void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.querySimpleDepPeopleToTreeByUserBelongSimpleDep(inputObject, outputObject);
    }

    /**
     * 根据聊天组展示用户
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "commonselpeople006", value = "根据聊天组展示用户", method = "GET", allUse = "2")
    @ApiImplicitParams(classBean = UserTreeQueryDo.class)
    @RequestMapping("/post/SysEveUserController/queryTalkGroupUserListByUserId")
    public void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryTalkGroupUserListByUserId(inputObject, outputObject);
    }

    /**
     * 获取所有在职的，拥有账号的员工
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "commonselpeople007", value = "获取所有在职的，拥有账号的员工", method = "POST", allUse = "2")
    @RequestMapping("/post/SysEveUserController/queryAllSysUserIsIncumbency")
    public void queryAllSysUserIsIncumbency(InputObject inputObject, OutputObject outputObject) {
        sysEveUserService.queryAllSysUserIsIncumbency(inputObject, outputObject);
    }

}
