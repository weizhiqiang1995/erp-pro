/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveWinTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveWinTypeController {

    @Autowired
    private SysEveWinTypeService sysEveWinTypeService;

    /**
     * 获取分类列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/querySysWinTypeList")
    public void querySysWinTypeList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.querySysWinTypeList(inputObject, outputObject);
    }

    /**
     * 获取所有一级分类展示为下拉选项
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/querySysWinFirstTypeList")
    public void querySysWinFirstTypeList(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.querySysWinFirstTypeList(inputObject, outputObject);
    }

    /**
     * 新增系统分类
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/insertSysWinTypeMation")
    public void insertSysWinTypeMation(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.insertSysWinTypeMation(inputObject, outputObject);
    }

    /**
     * 编辑系统分类时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/querySysWinTypeMationToEditById")
    public void querySysWinTypeMationToEditById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.querySysWinTypeMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑系统分类时
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/editSysWinTypeMationById")
    public void editSysWinTypeMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.editSysWinTypeMationById(inputObject, outputObject);
    }

    /**
     * 获取所有不是当前分类的一级分类展示为下拉选项
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/querySysWinFirstTypeListNotIsThisId")
    public void querySysWinFirstTypeListNotIsThisId(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.querySysWinFirstTypeListNotIsThisId(inputObject, outputObject);
    }

    /**
     * 删除系统分类
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/deleteSysWinTypeMationById")
    public void deleteSysWinTypeMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.deleteSysWinTypeMationById(inputObject, outputObject);
    }

    /**
     * 系统分类上移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/editSysWinTypeMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.editSysWinTypeMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 系统分类下移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/editSysWinTypeMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.editSysWinTypeMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 系统分类上线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/editSysWinTypeMationStateUpById")
    public void editSysWinTypeMationStateUpById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.editSysWinTypeMationStateUpById(inputObject, outputObject);
    }

    /**
     * 系统分类下线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/editSysWinTypeMationStateDownById")
    public void editSysWinTypeMationStateDownById(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.editSysWinTypeMationStateDownById(inputObject, outputObject);
    }

    /**
     * 获取已经上线的一级分类
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/querySysWinTypeFirstMationStateIsUp")
    public void querySysWinTypeFirstMationStateIsUp(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.querySysWinTypeFirstMationStateIsUp(inputObject, outputObject);
    }

    /**
     * 获取已经上线的二级分类
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveWinTypeController/querySysWinTypeSecondMationStateIsUp")
    public void querySysWinTypeSecondMationStateIsUp(InputObject inputObject, OutputObject outputObject) {
        sysEveWinTypeService.querySysWinTypeSecondMationStateIsUp(inputObject, outputObject);
    }

}
