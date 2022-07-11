/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysStaffFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysStaffFamilyController {

    @Autowired
    private SysStaffFamilyService sysStaffFamilyService;

    /**
     * 查询所有家庭情况列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffFamilyController/queryAllSysStaffFamilyList")
    public void queryAllSysStaffFamilyList(InputObject inputObject, OutputObject outputObject) {
        sysStaffFamilyService.queryAllSysStaffFamilyList(inputObject, outputObject);
    }

    /**
     * 员工家庭情况信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffFamilyController/insertSysStaffFamilyMation")
    public void insertSysStaffFamilyMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffFamilyService.insertSysStaffFamilyMation(inputObject, outputObject);
    }

    /**
     * 编辑员工家庭情况信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffFamilyController/querySysStaffFamilyMationToEdit")
    public void querySysStaffFamilyMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffFamilyService.querySysStaffFamilyMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工家庭情况信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffFamilyController/editSysStaffFamilyMationById")
    public void editSysStaffFamilyMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffFamilyService.editSysStaffFamilyMationById(inputObject, outputObject);
    }

    /**
     * 删除员工家庭情况信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffFamilyController/deleteSysStaffFamilyMationById")
    public void deleteSysStaffFamilyMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffFamilyService.deleteSysStaffFamilyMationById(inputObject, outputObject);
    }

    /**
     * 查询指定员工的家庭情况列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffFamilyController/queryPointStaffSysStaffFamilyList")
    public void queryPointStaffSysStaffFamilyList(InputObject inputObject, OutputObject outputObject) {
        sysStaffFamilyService.queryPointStaffSysStaffFamilyList(inputObject, outputObject);
    }

}
