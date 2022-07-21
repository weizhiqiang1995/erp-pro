/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveWinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveWinController {

    @Autowired
    private SysEveWinService sysEveWinService;

    /**
     * 获取系统信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/queryWinMationList")
    public void queryWinMationList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.queryWinMationList(inputObject, outputObject);
    }

    /**
     * 新增系统信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/insertWinMation")
    public void insertWinMation(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.insertWinMation(inputObject, outputObject);
    }

    /**
     * 编辑系统信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/queryWinMationToEditById")
    public void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.queryWinMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑系统信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/editWinMationById")
    public void editWinMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.editWinMationById(inputObject, outputObject);
    }

    /**
     * 删除系统信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/deleteWinMationById")
    public void deleteWinMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.deleteWinMationById(inputObject, outputObject);
    }

    /**
     * 进行商户系统授权
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/editAuthorizationById")
    public void editAuthorizationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.editAuthorizationById(inputObject, outputObject);
    }

    /**
     * 进行商户系统取消授权
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/editCancleAuthorizationById")
    public void editCancleAuthorizationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.editCancleAuthorizationById(inputObject, outputObject);
    }

    /**
     * 获取应用商店
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/queryWinMationListToShow")
    public void queryWinMationListToShow(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.queryWinMationListToShow(inputObject, outputObject);
    }

    /**
     * 系统重要的同步操作
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/insertWinMationImportantSynchronization")
    public void insertWinMationImportantSynchronization(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.insertWinMationImportantSynchronization(inputObject, outputObject);
    }

    /**
     * 系统重要的同步操作获取数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinController/queryWinMationImportantSynchronizationData")
    public void queryWinMationImportantSynchronizationData(InputObject inputObject, OutputObject outputObject) {
        sysEveWinService.queryWinMationImportantSynchronizationData(inputObject, outputObject);
    }

}
