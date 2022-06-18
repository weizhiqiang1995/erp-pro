/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveWinDragDropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveWinDragDropController {

    @Autowired
    private SysEveWinDragDropService sysEveWinDragDropService;

    /**
     * 用户自定义创建菜单盒子
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/insertWinCustomMenuBox")
    public void insertWinCustomMenuBox(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.insertWinCustomMenuBox(inputObject, outputObject);
    }

    /**
     * 用户自定义创建菜单
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/insertWinCustomMenu")
    public void insertWinCustomMenu(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.insertWinCustomMenu(inputObject, outputObject);
    }

    /**
     * 用户删除自定义菜单或文件夹
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/deleteWinMenuOrBoxById")
    public void deleteWinMenuOrBoxById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.deleteWinMenuOrBoxById(inputObject, outputObject);
    }

    /**
     * 用户自定义父菜单
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/editMenuParentIdById")
    public void editMenuParentIdById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.editMenuParentIdById(inputObject, outputObject);
    }

    /**
     * 获取菜单类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/queryMenuMationTypeById")
    public void queryMenuMationTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.queryMenuMationTypeById(inputObject, outputObject);
    }

    /**
     * 编辑自定义盒子时回显信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/queryCustomMenuBoxMationEditById")
    public void queryCustomMenuBoxMationEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.queryCustomMenuBoxMationEditById(inputObject, outputObject);
    }

    /**
     * 编辑自定义盒子
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/editCustomMenuBoxMationById")
    public void editCustomMenuBoxMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.editCustomMenuBoxMationById(inputObject, outputObject);
    }

    /**
     * 编辑快捷方式时回显信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/queryCustomMenuMationEditById")
    public void queryCustomMenuMationEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.queryCustomMenuMationEditById(inputObject, outputObject);
    }

    /**
     * 编辑快捷方式
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/editCustomMenuMationById")
    public void editCustomMenuMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.editCustomMenuMationById(inputObject, outputObject);
    }

    /**
     * 系统菜单发送到桌面快捷方式
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinDragDropController/editCustomMenuToDeskTopById")
    public void editCustomMenuToDeskTopById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinDragDropService.editCustomMenuToDeskTopById(inputObject, outputObject);
    }

}
