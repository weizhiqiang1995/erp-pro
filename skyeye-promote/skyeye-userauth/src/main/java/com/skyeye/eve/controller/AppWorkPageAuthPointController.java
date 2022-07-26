/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.userauth.menu.AppWorkPageAuthPointMation;
import com.skyeye.eve.entity.userauth.menu.SysMenuAuthPointMation;
import com.skyeye.eve.service.AppWorkPageAuthPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "手机端菜单权限点管理", tags = "手机端菜单权限点管理", modelName = "基础模块")
public class AppWorkPageAuthPointController {

    @Autowired
    private AppWorkPageAuthPointService appWorkPageAuthPointService;

    /**
     * 获取菜单权限点列表根据菜单id
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageAuthPointController/queryAppWorkPageAuthPointListByMenuId")
    public void queryAppWorkPageAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) {
        appWorkPageAuthPointService.queryAppWorkPageAuthPointListByMenuId(inputObject, outputObject);
    }

    /**
     * 新增/编辑APP菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAppWorkPageAuthPointMation", value = "新增/编辑APP菜单权限点", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = AppWorkPageAuthPointMation.class)
    @RequestMapping("/post/AppWorkPageAuthPointController/writeAppWorkPageAuthPointMation")
    public void writeAppWorkPageAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        appWorkPageAuthPointService.writeAppWorkPageAuthPointMation(inputObject, outputObject);
    }

    /**
     * 编辑菜单权限点时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageAuthPointController/queryAppWorkPageAuthPointMationToEditById")
    public void queryAppWorkPageAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageAuthPointService.queryAppWorkPageAuthPointMationToEditById(inputObject, outputObject);
    }

    /**
     * 删除菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageAuthPointController/deleteAppWorkPageAuthPointMationById")
    public void deleteAppWorkPageAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageAuthPointService.deleteAppWorkPageAuthPointMationById(inputObject, outputObject);
    }

}
