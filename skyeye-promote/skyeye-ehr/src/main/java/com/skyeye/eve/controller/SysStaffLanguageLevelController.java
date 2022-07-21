/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 */

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysStaffLanguageLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysStaffLanguageLevelController {

    @Autowired
    private SysStaffLanguageLevelService sysStaffLanguageLevelService;

    /**
     * 查询语种等级列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/querySysStaffLanguageLevelList")
    public void querySysStaffLanguageLevelList(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.querySysStaffLanguageLevelList(inputObject, outputObject);
    }

    /**
     * 新增语种等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/insertSysStaffLanguageLevelMation")
    public void insertSysStaffLanguageLevelMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.insertSysStaffLanguageLevelMation(inputObject, outputObject);
    }

    /**
     * 修改语种等级时数据回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/querySysStaffLanguageLevelMationToEditById")
    public void querySysStaffLanguageLevelMationToEditById(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.querySysStaffLanguageLevelMationToEditById(inputObject, outputObject);
    }

    /**
     * 修改语种等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/editSysStaffLanguageLevelMationById")
    public void editSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.editSysStaffLanguageLevelMationById(inputObject, outputObject);
    }

    /**
     * 禁用语种等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/downSysStaffLanguageLevelMationById")
    public void downSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.downSysStaffLanguageLevelMationById(inputObject, outputObject);
    }

    /**
     * 启用语种等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/upSysStaffLanguageLevelMationById")
    public void upSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.upSysStaffLanguageLevelMationById(inputObject, outputObject);
    }

    /**
     * 删除语种等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/deleteSysStaffLanguageLevelMationById")
    public void deleteSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.deleteSysStaffLanguageLevelMationById(inputObject, outputObject);
    }

    /**
     * 获取所有启用语种等级
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffLanguageLevelController/querySysStaffLanguageLevelUpMation")
    public void querySysStaffLanguageLevelUpMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageLevelService.querySysStaffLanguageLevelUpMation(inputObject, outputObject);
    }

}
