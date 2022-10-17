/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.icon.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.icon.service.SysEveIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveIconController {

    @Autowired
    private SysEveIconService sysEveIconService;

    /**
     * 获取ICON列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveIconController/querySysIconList")
    public void querySysIconList(InputObject inputObject, OutputObject outputObject) {
        sysEveIconService.querySysIconList(inputObject, outputObject);
    }

    /**
     * 添加ICON信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveIconController/insertSysIconMation")
    public void insertSysIconMation(InputObject inputObject, OutputObject outputObject) {
        sysEveIconService.insertSysIconMation(inputObject, outputObject);
    }

    /**
     * 删除ICON信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveIconController/deleteSysIconMationById")
    public void deleteSysIconMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveIconService.deleteSysIconMationById(inputObject, outputObject);
    }

    /**
     * 编辑ICON信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveIconController/querySysIconMationToEditById")
    public void querySysIconMationToEditById(InputObject inputObject, OutputObject outputObject) {
        sysEveIconService.querySysIconMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑ICON信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveIconController/editSysIconMationById")
    public void editSysIconMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveIconService.editSysIconMationById(inputObject, outputObject);
    }

    /**
     * 获取ICON列表供menu菜单使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveIconController/querySysIconListToMenu")
    public void querySysIconListToMenu(InputObject inputObject, OutputObject outputObject) {
        sysEveIconService.querySysIconListToMenu(inputObject, outputObject);
    }

}
