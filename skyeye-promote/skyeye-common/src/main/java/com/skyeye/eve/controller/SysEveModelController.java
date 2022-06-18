/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveModelController {

    @Autowired
    private SysEveModelService sysEveModelService;

    /**
     * 获取系统编辑器模板表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelController/querySysEveModelList")
    public void querySysEveModelList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelService.querySysEveModelList(inputObject, outputObject);
    }

    /**
     * 新增系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelController/insertSysEveModelMation")
    public void insertSysEveModelMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelService.insertSysEveModelMation(inputObject, outputObject);
    }

    /**
     * 删除系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelController/deleteSysEveModelById")
    public void deleteSysEveModelById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelService.deleteSysEveModelById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelController/selectSysEveModelById")
    public void selectSysEveModelById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelService.selectSysEveModelById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelController/editSysEveModelMationById")
    public void editSysEveModelMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelService.editSysEveModelMationById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的系统编辑器模板详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveModelController/selectSysEveModelMationById")
    public void selectSysEveModelMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveModelService.selectSysEveModelMationById(inputObject, outputObject);
    }

}
