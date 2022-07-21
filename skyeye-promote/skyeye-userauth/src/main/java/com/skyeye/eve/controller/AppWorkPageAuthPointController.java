/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.AppWorkPageAuthPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
     * 添加菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageAuthPointController/insertAppWorkPageAuthPointMation")
    public void insertAppWorkPageAuthPointMation(InputObject inputObject, OutputObject outputObject) {
        appWorkPageAuthPointService.insertAppWorkPageAuthPointMation(inputObject, outputObject);
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
     * 编辑菜单权限点
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageAuthPointController/editAppWorkPageAuthPointMationById")
    public void editAppWorkPageAuthPointMationById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageAuthPointService.editAppWorkPageAuthPointMationById(inputObject, outputObject);
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
