/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.win.service.SysEveWinBgPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveWinBgPicController {

    @Autowired
    private SysEveWinBgPicService sysEveWinBgPicService;

    /**
     * 获取win系统桌面图片列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinBgPicController/querySysEveWinBgPicList")
    public void querySysEveWinBgPicList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinBgPicService.querySysEveWinBgPicList(inputObject, outputObject);
    }

    /**
     * 添加win系统桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinBgPicController/insertSysEveWinBgPicMation")
    public void insertSysEveWinBgPicMation(InputObject inputObject, OutputObject outputObject) {
        sysEveWinBgPicService.insertSysEveWinBgPicMation(inputObject, outputObject);
    }

    /**
     * 删除win系统桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinBgPicController/deleteSysEveWinBgPicMationById")
    public void deleteSysEveWinBgPicMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinBgPicService.deleteSysEveWinBgPicMationById(inputObject, outputObject);
    }

    /**
     * 用户自定义上传添加win系统桌面图片信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinBgPicController/insertSysEveWinBgPicMationByCustom")
    public void insertSysEveWinBgPicMationByCustom(InputObject inputObject, OutputObject outputObject) {
        sysEveWinBgPicService.insertSysEveWinBgPicMationByCustom(inputObject, outputObject);
    }

    /**
     * 获取win系统桌面图片列表用户自定义
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinBgPicController/querySysEveWinBgPicCustomList")
    public void querySysEveWinBgPicCustomList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinBgPicService.querySysEveWinBgPicCustomList(inputObject, outputObject);
    }

    /**
     * 删除win系统桌面图片信息用户自定义
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveWinBgPicController/deleteSysEveWinBgPicMationCustomById")
    public void deleteSysEveWinBgPicMationCustomById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinBgPicService.deleteSysEveWinBgPicMationCustomById(inputObject, outputObject);
    }

}
