/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveDesktopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveDesktopController {

    @Autowired
    private SysEveDesktopService sysEveDesktopService;

    /**
     * 获取桌面列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/querySysDesktopList")
    public void querySysDesktopList(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.querySysDesktopList(inputObject, outputObject);
    }


    /**
     * 添加桌面
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/insertSysDesktopMation")
    public void insertSysDesktopMation(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.insertSysDesktopMation(inputObject, outputObject);
    }

    /**
     * 删除桌面
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/deleteSysDesktopById")
    public void deleteSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.deleteSysDesktopById(inputObject, outputObject);
    }

    /**
     * 上线桌面
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/updateUpSysDesktopById")
    public void updateUpSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.updateUpSysDesktopById(inputObject, outputObject);
    }

    /**
     * 下线桌面
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/updateDownSysDesktopById")
    public void updateDownSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.updateDownSysDesktopById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的桌面
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/selectSysDesktopById")
    public void selectSysDesktopById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.selectSysDesktopById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的桌面
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/editSysDesktopMationById")
    public void editSysDesktopMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.editSysDesktopMationById(inputObject, outputObject);
    }

    /**
     * 桌面上移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/editSysDesktopMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.editSysDesktopMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 桌面下移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/editSysDesktopMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.editSysDesktopMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 获取全部的桌面用于系统菜单
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/queryAllSysDesktopList")
    public void queryAllSysDesktopList(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.queryAllSysDesktopList(inputObject, outputObject);
    }

    /**
     * 一键移除所有菜单
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveDesktopController/removeAllSysEveMenuByDesktopId")
    public void removeAllSysEveMenuByDesktopId(InputObject inputObject, OutputObject outputObject) {
        sysEveDesktopService.removeAllSysEveMenuByDesktopId(inputObject, outputObject);
    }

}
