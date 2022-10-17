/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.userauth.win.SysEveWinMation;
import com.skyeye.win.service.SysEveWinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveWinController
 * @Description: 服务管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:10
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "前台服务管理", tags = "前台服务管理", modelName = "基础模块")
public class SysEveWinController {

    @Autowired
    private SysEveWinService sysEveWinService;

    /**
     * 获取服务信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysevewin001", value = "获取服务信息列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SysEveWinController/queryWinMationList")
    public void queryWinMationList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.queryWinMationList(inputObject, outputObject);
    }

    /**
     * 新增/编辑服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSysEveWinMation", value = "新增/编辑服务信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysEveWinMation.class)
    @RequestMapping("/post/SysEveWinController/insertWinMation")
    public void insertWinMation(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.insertWinMation(inputObject, outputObject);
    }

    /**
     * 编辑服务信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysevewin003", value = "编辑服务信息时进行回显", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveWinController/queryWinMationToEditById")
    public void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.queryWinMationToEditById(inputObject, outputObject);
    }

    /**
     * 删除服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysevewin005", value = "删除桌面信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveWinController/deleteWinMationById")
    public void deleteWinMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.deleteWinMationById(inputObject, outputObject);
    }

    /**
     * 获取所有的服务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySysEveWinList", value = "获取所有的服务信息", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEveWinController/querySysEveWinList")
    public void querySysEveWinList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.querySysEveWinList(inputObject, outputObject);
    }

}
