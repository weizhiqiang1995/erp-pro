/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.userauth.menu.SysMenuAuthPointMation;
import com.skyeye.eve.service.SysEveMenuAuthPointService;
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
@Api(value = "菜单权限点管理", tags = "菜单权限点管理", modelName = "基础模块")
public class SysEveMenuAuthPointController {

    @Autowired
    private SysEveMenuAuthPointService sysEveMenuAuthPointService;

    /**
     * 获取菜单权限点列表根据菜单id
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointListByMenuId")
    public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.querySysEveMenuAuthPointListByMenuId(inputObject, outputObject);
    }

    /**
     * 新增/编辑菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSysEveMenuAuthPointMation", value = "新增/编辑菜单权限点", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysMenuAuthPointMation.class)
    @RequestMapping("/post/SysEveMenuAuthPointController/insertSysEveMenuAuthPointMation")
    public void insertSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.insertSysEveMenuAuthPointMation(inputObject, outputObject);
    }

    /**
     * 编辑菜单权限点时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointMationToEditById")
    public void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.querySysEveMenuAuthPointMationToEditById(inputObject, outputObject);
    }

    /**
     * 删除菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/deleteSysEveMenuAuthPointMationById")
    public void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveMenuAuthPointService.deleteSysEveMenuAuthPointMationById(inputObject, outputObject);
    }

}
