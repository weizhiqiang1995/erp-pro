/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.win.service.SysEveWinLockBgPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveWinLockBgPicController {

    @Autowired
    private SysEveWinLockBgPicService sysEveWinLockBgPicService;

    /**
     * 获取win系统锁屏桌面图片列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinLockBgPicController/querySysEveWinLockBgPicList")
    public void querySysEveWinLockBgPicList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.querySysEveWinLockBgPicList(inputObject, outputObject);
    }

    /**
     * 添加win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinLockBgPicController/insertSysEveWinLockBgPicMation")
    public void insertSysEveWinLockBgPicMation(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.insertSysEveWinLockBgPicMation(inputObject, outputObject);
    }

    /**
     * 删除win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinLockBgPicController/deleteSysEveWinLockBgPicMationById")
    public void deleteSysEveWinLockBgPicMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.deleteSysEveWinLockBgPicMationById(inputObject, outputObject);
    }

    /**
     * 用户自定义上传win系统锁屏桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinLockBgPicController/insertSysEveWinBgPicMationByCustom")
    public void insertSysEveWinBgPicMationByCustom(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.insertSysEveWinBgPicMationByCustom(inputObject, outputObject);
    }

    /**
     * 获取win系统锁屏桌面图片列表用户自定义
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinLockBgPicController/querySysEveWinBgPicCustomList")
    public void querySysEveWinBgPicCustomList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.querySysEveWinBgPicCustomList(inputObject, outputObject);
    }

    /**
     * 删除win系统锁屏桌面图片信息用户自定义
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinLockBgPicController/deleteSysEveWinBgPicMationCustomById")
    public void deleteSysEveWinBgPicMationCustomById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinLockBgPicService.deleteSysEveWinBgPicMationCustomById(inputObject, outputObject);
    }

}
