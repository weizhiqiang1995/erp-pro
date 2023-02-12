/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.win.entity.SysDesktop;
import com.skyeye.win.service.SysEveDesktopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveDesktopController
 * @Description: 桌面管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:19
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "桌面信息管理", tags = "桌面信息管理", modelName = "基础模块")
public class SysEveDesktopController {

    @Autowired
    private SysEveDesktopService sysEveDesktopService;

    /**
     * 获取桌面列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "desktop001", value = "获取桌面列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SysEveDesktopController/querySysDesktopList")
    public void querySysDesktopList(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.queryPageList(inputObject, outputObject);
    }


    /**
     * 新增/编辑桌面信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSysEveDesktopMation", value = "新增/编辑桌面信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysDesktop.class)
    @RequestMapping("/post/SysEveDesktopController/writeSysEveDesktopMation")
    public void writeSysEveDesktopMation(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "desktop003", value = "删除桌面信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveDesktopController/deleteSysDesktopById")
    public void deleteSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.deleteById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "desktop006", value = "通过id查找对应的桌面", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveDesktopController/selectSysDesktopById")
    public void selectSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.selectById(inputObject, outputObject);
    }

    /**
     * 桌面上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "desktop008", value = "桌面上移", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveDesktopController/editSysDesktopMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.editSysDesktopMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 桌面下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "desktop009", value = "桌面下移", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveDesktopController/editSysDesktopMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.editSysDesktopMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 获取全部的桌面用于系统菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "desktop011", value = "获取全部的桌面用于系统菜单", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveDesktopController/queryAllSysDesktopList")
    public void queryAllSysDesktopList(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.queryAllSysDesktopList(inputObject, outputObject);
    }

    /**
     * 一键移除所有菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "desktop012", value = "一键移除所有菜单", method = "POST", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveDesktopController/removeAllSysEveMenuByDesktopId")
    public void removeAllSysEveMenuByDesktopId(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.removeAllSysEveMenuByDesktopId(inputObject, outputObject);
    }

}
