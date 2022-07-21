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
import com.skyeye.eve.entity.userauth.menu.SysMenuMation;
import com.skyeye.eve.service.SysEveMenuService;
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
@Api(value = "菜单管理", tags = "菜单管理", modelName = "基础模块")
public class SysEveMenuController {

    @Autowired
    private SysEveMenuService sysEveMenuService;

    /**
     * 获取菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/querySysMenuList")
    public void querySysMenuList(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.querySysMenuList(inputObject, outputObject);
    }

    /**
     * 添加菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys007", value = "添加菜单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysMenuMation.class)
    @RequestMapping("/post/SysEveMenuController/insertSysMenuMation")
    public void insertSysMenuMation(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.insertSysMenuMation(inputObject, outputObject);
    }

    /**
     * 查看同级菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/querySysMenuMationBySimpleLevel")
    public void querySysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.querySysMenuMationBySimpleLevel(inputObject, outputObject);
    }

    /**
     * 编辑菜单时进行信息回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/querySysMenuMationToEditById")
    public void querySysMenuMationToEditById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.querySysMenuMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sys010", value = "编辑菜单信息", method = "PUT", allUse = "1")
    @ApiImplicitParams(classBean = SysMenuMation.class, value = {
        @ApiImplicitParam(id = "rowId", name = "id", value = "菜单ID", required = "required")})
    @RequestMapping("/post/SysEveMenuController/editSysMenuMationById")
    public void editSysMenuMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.editSysMenuMationById(inputObject, outputObject);
    }

    /**
     * 删除菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/deleteSysMenuMationById")
    public void deleteSysMenuMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.deleteSysMenuMationById(inputObject, outputObject);
    }

    /**
     * 异步加载树查看商户拥有的系统
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/queryTreeSysMenuMationBySimpleLevel")
    public void queryTreeSysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.queryTreeSysMenuMationBySimpleLevel(inputObject, outputObject);
    }

    /**
     * 获取菜单级别列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/querySysMenuLevelList")
    public void querySysMenuLevelList(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.querySysMenuLevelList(inputObject, outputObject);
    }

    /**
     * 菜单展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
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
    @RequestMapping("/post/SysEveMenuController/editSysEveMenuSortLowerById")
    public void editSysEveMenuSortLowerById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.editSysEveMenuSortLowerById(inputObject, outputObject);
    }

    /**
     * 获取该系统商户拥有的系统
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/querySysWinMationListBySysId")
    public void querySysWinMationListBySysId(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.querySysWinMationListBySysId(inputObject, outputObject);
    }

    /**
     * 系统菜单详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuController/querySysEveMenuBySysId")
    public void querySysEveMenuBySysId(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuService.querySysEveMenuBySysId(inputObject, outputObject);
    }

}
