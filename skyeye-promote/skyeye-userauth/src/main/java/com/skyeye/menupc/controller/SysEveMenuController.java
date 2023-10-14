/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.menupc.entity.SysMenu;
import com.skyeye.menupc.entity.SysMenuQueryDo;
import com.skyeye.menupc.service.SysEveMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveMenuController
 * @Description: 菜单管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/15 20:58
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "菜单管理", tags = "菜单管理", modelName = "菜单管理")
public class SysEveMenuController {

    @Autowired
    private SysEveMenuService sysEveMenuService;

    /**
     * 获取菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys006", value = "获取菜单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysMenuQueryDo.class)
    @RequestMapping("/post/SysEveMenuController/querySysMenuList")
    public void querySysMenuList(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeMenu", value = "添加/编辑菜单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysMenu.class)
    @RequestMapping("/post/SysEveMenuController/writeMenu")
    public void writeMenu(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 系统菜单详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys040", value = "系统菜单详情", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "菜单ID", required = "required")})
    @RequestMapping("/post/SysEveMenuController/querySysEveMenuBySysId")
    public void querySysEveMenuBySysId(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.selectById(inputObject, outputObject);
    }

    /**
     * 根据父菜单ID查看子菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys009", value = "根据父菜单ID查看子菜单", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "parentId", name = "parentId", value = "父菜单ID", required = "required")})
    @RequestMapping("/post/SysEveMenuController/querySysMenuMationBySimpleLevel")
    public void querySysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.querySysMenuMationBySimpleLevel(inputObject, outputObject);
    }

    /**
     * 删除菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteMenuById", value = "删除菜单信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "菜单ID", required = "required")})
    @RequestMapping("/post/SysEveMenuController/deleteMenuById")
    public void deleteMenuById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.deleteById(inputObject, outputObject);
    }

    /**
     * 菜单展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys022", value = "菜单展示顺序上移", method = "POST", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "菜单ID", required = "required")})
    @RequestMapping("/post/SysEveMenuController/editSysEveMenuSortTopById")
    public void editSysEveMenuSortTopById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.editSysEveMenuSortTopById(inputObject, outputObject);
    }

    /**
     * 菜单展示顺序下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys023", value = "菜单展示顺序下移", method = "POST", allUse = "1")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "菜单ID", required = "required")})
    @RequestMapping("/post/SysEveMenuController/editSysEveMenuSortLowerById")
    public void editSysEveMenuSortLowerById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.editSysEveMenuSortLowerById(inputObject, outputObject);
    }

}
