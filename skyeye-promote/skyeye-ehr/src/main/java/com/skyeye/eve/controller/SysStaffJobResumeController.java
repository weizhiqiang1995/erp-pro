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
import com.skyeye.eve.service.SysStaffJobResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "员工工作履历信息", tags = "员工工作履历信息", modelName = "EHR模块")
public class SysStaffJobResumeController {

    @Autowired
    private SysStaffJobResumeService sysStaffJobResumeService;

    /**
     * 查询所有工作履历列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffJobResumeController/queryAllSysStaffJobResumeList")
    public void queryAllSysStaffJobResumeList(InputObject inputObject, OutputObject outputObject) {
        sysStaffJobResumeService.queryAllSysStaffJobResumeList(inputObject, outputObject);
    }

    /**
     * 员工工作履历信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffJobResumeController/insertSysStaffJobResumeMation")
    public void insertSysStaffJobResumeMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffJobResumeService.insertSysStaffJobResumeMation(inputObject, outputObject);
    }

    /**
     * 编辑员工工作履历信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffJobResumeController/querySysStaffJobResumeMationToEdit")
    public void querySysStaffJobResumeMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffJobResumeService.querySysStaffJobResumeMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工工作履历信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffJobResumeController/editSysStaffJobResumeMationById")
    public void editSysStaffJobResumeMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffJobResumeService.editSysStaffJobResumeMationById(inputObject, outputObject);
    }

    /**
     * 删除员工工作履历信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffJobResumeController/deleteSysStaffJobResumeMationById")
    public void deleteSysStaffJobResumeMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffJobResumeService.deleteSysStaffJobResumeMationById(inputObject, outputObject);
    }

    /**
     * 查询指定员工的工作履历列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysstaffjobresume006", value = "查询指定员工的工作履历列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = PointStaffQueryDo.class)
    @RequestMapping("/post/SysStaffJobResumeController/queryPointStaffSysStaffJobResumeList")
    public void queryPointStaffSysStaffJobResumeList(InputObject inputObject, OutputObject outputObject) {
        sysStaffJobResumeService.queryPointStaffSysStaffJobResumeList(inputObject, outputObject);
    }

}
