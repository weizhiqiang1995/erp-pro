/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.menupc.entity.SysMenuAuthPoint;
import com.skyeye.menupc.service.SysEveMenuAuthPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @ClassName: SysEveMenuAuthPointController
 * @Description: 菜单权限点管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/23 19:24
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "菜单权限点管理", tags = "菜单权限点管理", modelName = "菜单管理")
public class SysEveMenuAuthPointController {

    @Autowired
    private SysEveMenuAuthPointService sysEveMenuAuthPointService;

    /**
     * 根据菜单id获取菜单权限点列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysevemenuauthpoint001", value = "根据菜单id获取菜单权限点列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TableSelectInfo.class)
    @RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointListByMenuId")
    public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.queryList(inputObject, outputObject);
    }

    /**
     * 新增/编辑菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSysEveMenuAuthPointMation", value = "新增/编辑菜单权限点", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysMenuAuthPoint.class)
    @RequestMapping("/post/SysEveMenuAuthPointController/writeSysEveMenuAuthPointMation")
    public void writeSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id查询菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysevemenuauthpoint003", value = "根据id查询菜单权限点", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointById")
    public void querySysEveMenuAuthPointById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysevemenuauthpoint005", value = "根据id删除菜单权限点", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysEveMenuAuthPointController/deleteSysEveMenuAuthPointById")
    public void deleteSysEveMenuAuthPointById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.deleteById(inputObject, outputObject);
    }

}
