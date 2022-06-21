/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveWinThemeColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveWinThemeColorController {

    @Autowired
    private SysEveWinThemeColorService sysEveWinThemeColorService;

    /**
     * 获取win系统主题颜色列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinThemeColorController/querySysEveWinThemeColorList")
    public void querySysEveWinThemeColorList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinThemeColorService.querySysEveWinThemeColorList(inputObject, outputObject);
    }

    /**
     * 添加win系统主题颜色信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinThemeColorController/insertSysEveWinThemeColorMation")
    public void insertSysEveWinThemeColorMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinThemeColorService.insertSysEveWinThemeColorMation(inputObject, outputObject);
    }

    /**
     * 删除win系统主题颜色信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinThemeColorController/deleteSysEveWinThemeColorMationById")
    public void deleteSysEveWinThemeColorMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinThemeColorService.deleteSysEveWinThemeColorMationById(inputObject, outputObject);
    }

    /**
     * 编辑win系统主题颜色信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinThemeColorController/querySysEveWinThemeColorMationToEditById")
    public void querySysEveWinThemeColorMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinThemeColorService.querySysEveWinThemeColorMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑win系统主题颜色信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveWinThemeColorController/editSysEveWinThemeColorMationById")
    public void editSysEveWinThemeColorMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveWinThemeColorService.editSysEveWinThemeColorMationById(inputObject, outputObject);
    }

}