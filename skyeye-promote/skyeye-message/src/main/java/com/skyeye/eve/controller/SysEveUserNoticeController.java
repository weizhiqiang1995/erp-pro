/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveUserNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveUserNoticeController {

    @Autowired
    private SysEveUserNoticeService sysEveUserNoticeService;

    /**
     * 根据用户id获取用户的消息只查询8条
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserNoticeController/getNoticeListByUserId")
    public void getNoticeListByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserNoticeService.getNoticeListByUserId(inputObject, outputObject);
    }

    /**
     * 根据用户id获取用户的消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserNoticeController/getAllNoticeListByUserId")
    public void getAllNoticeListByUserId(InputObject inputObject, OutputObject outputObject) {
        sysEveUserNoticeService.getAllNoticeListByUserId(inputObject, outputObject);
    }

    /**
     * 用户阅读消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserNoticeController/editNoticeMationById")
    public void editNoticeMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserNoticeService.editNoticeMationById(inputObject, outputObject);
    }

    /**
     * 用户删除消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserNoticeController/deleteNoticeMationById")
    public void deleteNoticeMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserNoticeService.deleteNoticeMationById(inputObject, outputObject);
    }

    /**
     * 用户批量阅读消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserNoticeController/editNoticeMationByIds")
    public void editNoticeMationByIds(InputObject inputObject, OutputObject outputObject) {
        sysEveUserNoticeService.editNoticeMationByIds(inputObject, outputObject);
    }

    /**
     * 用户批量删除消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserNoticeController/deleteNoticeMationByIds")
    public void deleteNoticeMationByIds(InputObject inputObject, OutputObject outputObject) {
        sysEveUserNoticeService.deleteNoticeMationByIds(inputObject, outputObject);
    }

    /**
     * 获取消息详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserNoticeController/queryNoticeMationById")
    public void queryNoticeMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveUserNoticeService.queryNoticeMationById(inputObject, outputObject);
    }

}
