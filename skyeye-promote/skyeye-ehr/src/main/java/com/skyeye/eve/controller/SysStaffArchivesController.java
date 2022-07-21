/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysStaffArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysStaffArchivesController
 * @Description: 员工档案类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/10 16:34
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
public class SysStaffArchivesController {

    @Autowired
    private SysStaffArchivesService sysStaffArchivesService;

    /**
     * 查询所有档案列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffArchivesController/queryAllSysStaffArchivesList")
    public void queryAllSysStaffArchivesList(InputObject inputObject, OutputObject outputObject) {
        sysStaffArchivesService.queryAllSysStaffArchivesList(inputObject, outputObject);
    }

    /**
     * 离职员工在档列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffArchivesController/querySysLeaveStaffArchivesList")
    public void querySysLeaveStaffArchivesList(InputObject inputObject, OutputObject outputObject) {
        sysStaffArchivesService.querySysLeaveStaffArchivesList(inputObject, outputObject);
    }

    /**
     * 员工不在档列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffArchivesController/querySysStaffNotInArchivesList")
    public void querySysStaffNotInArchivesList(InputObject inputObject, OutputObject outputObject) {
        sysStaffArchivesService.querySysStaffNotInArchivesList(inputObject, outputObject);
    }

    /**
     * 员工无在档列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffArchivesController/querySysStaffNoArchivesList")
    public void querySysStaffNoArchivesList(InputObject inputObject, OutputObject outputObject) {
        sysStaffArchivesService.querySysStaffNoArchivesList(inputObject, outputObject);
    }

    /**
     * 员工档案信息录入
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffArchivesController/insertSysStaffArchivesMation")
    public void insertSysStaffArchivesMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffArchivesService.insertSysStaffArchivesMation(inputObject, outputObject);
    }

    /**
     * 编辑员工档案信息时回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffArchivesController/querySysStaffArchivesMationToEdit")
    public void querySysStaffArchivesMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffArchivesService.querySysStaffArchivesMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工档案信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffArchivesController/editSysStaffArchivesMationById")
    public void editSysStaffArchivesMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffArchivesService.editSysStaffArchivesMationById(inputObject, outputObject);
    }

}
