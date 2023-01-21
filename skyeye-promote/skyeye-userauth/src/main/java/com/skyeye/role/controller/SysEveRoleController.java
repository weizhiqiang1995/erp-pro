/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.role.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.role.entity.Role;
import com.skyeye.role.service.SysEveRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveRoleController
 * @Description: 角色管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/12 21:11
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "角色管理", tags = "角色管理", modelName = "基础模块")
public class SysEveRoleController {

    @Autowired
    private SysEveRoleService sysEveRoleService;

    /**
     * 获取角色列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys013", value = "获取角色列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SysEveRoleController/querySysRoleList")
    public void querySysRoleList(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑角色
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSysRole", value = "新增/编辑角色", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = Role.class)
    @RequestMapping("/post/SysEveRoleController/writeSysRole")
    public void writeSysRole(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询角色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySysRoleById", value = "根据id查询角色信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "角色ID", required = "required")})
    @RequestMapping("/post/SysEveRoleController/querySysRoleById")
    public void querySysRoleById(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.selectById(inputObject, outputObject);
    }

    /**
     * 删除角色
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteRoleById", value = "删除角色", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "角色ID", required = "required")})
    @RequestMapping("/post/SysEveRoleController/deleteRoleById")
    public void deleteRoleById(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.deleteById(inputObject, outputObject);
    }

    /**
     * 获取所有模块(桌面)/菜单/权限点/分组/数据权限列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys014", value = "获取所有模块(桌面)/菜单/权限点/分组/数据权限列表", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveRoleController/querySysRoleBandMenuList")
    public void querySysRoleBandMenuList(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.querySysRoleBandMenuList(inputObject, outputObject);
    }

    /**
     * 编辑角色PC端权限
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "editSysRolePCAuth", value = "编辑角色PC端权限", method = "PUT", allUse = "1")
    @ApiImplicitParams(classBean = Role.class, value = {
        @ApiImplicitParam(id = "rowId", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveRoleController/editSysRolePCAuth")
    public void editSysRolePCAuth(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.editSysRolePCAuth(inputObject, outputObject);
    }

    /**
     * 获取角色需要绑定的手机端菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys037", value = "获取角色需要绑定的手机端菜单列表", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveRoleController/querySysRoleBandAppMenuList")
    public void querySysRoleBandAppMenuList(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.querySysRoleBandAppMenuList(inputObject, outputObject);
    }

    /**
     * 手机端菜单授权
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys039", value = "手机端菜单授权", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "menuIds", name = "menuIds", value = "菜单权限", required = "required"),
        @ApiImplicitParam(id = "pointIds", name = "pointIds", value = "权限点"),
        @ApiImplicitParam(id = "id", name = "id", value = "角色ID", required = "required")})
    @RequestMapping("/post/SysEveRoleController/editSysRoleAppMenuById")
    public void editSysRoleAppMenuById(InputObject inputObject, OutputObject outputObject) {
        sysEveRoleService.editSysRoleAppMenuById(inputObject, outputObject);
    }

}
