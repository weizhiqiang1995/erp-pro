/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.service.SysStaffEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "员工教育背景", tags = "员工教育背景", modelName = "EHR模块")
public class SysStaffEducationController {

    @Autowired
    private SysStaffEducationService sysStaffEducationService;

    /**
     * 查询所有教育背景列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffEducationController/queryAllSysStaffEducationList")
    public void queryAllSysStaffEducationList(InputObject inputObject, OutputObject outputObject) {
        sysStaffEducationService.queryAllSysStaffEducationList(inputObject, outputObject);
    }

    /**
     * 员工教育背景信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffEducationController/insertSysStaffEducationMation")
    public void insertSysStaffEducationMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffEducationService.insertSysStaffEducationMation(inputObject, outputObject);
    }

    /**
     * 编辑员工教育背景信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffEducationController/querySysStaffEducationMationToEdit")
    public void querySysStaffEducationMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffEducationService.querySysStaffEducationMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工教育背景信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffEducationController/editSysStaffEducationMationById")
    public void editSysStaffEducationMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffEducationService.editSysStaffEducationMationById(inputObject, outputObject);
    }

    /**
     * 删除员工教育背景信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffEducationController/deleteSysStaffEducationMationById")
    public void deleteSysStaffEducationMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffEducationService.deleteSysStaffEducationMationById(inputObject, outputObject);
    }

    /**
     * 查询指定员工的教育背景列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysstaffeducation006", value = "查询指定员工的教育背景列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = PointStaffQueryDo.class)
    @RequestMapping("/post/SysStaffEducationController/queryPointStaffSysStaffEducationList")
    public void queryPointStaffSysStaffEducationList(InputObject inputObject, OutputObject outputObject) {
        sysStaffEducationService.queryPointStaffSysStaffEducationList(inputObject, outputObject);
    }

}
