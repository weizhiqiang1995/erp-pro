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
import com.skyeye.eve.entity.ehr.language.SysStaffLanguageQueryDo;
import com.skyeye.eve.service.SysStaffLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "员工语言能力信息", tags = "员工语言能力信息", modelName = "EHR模块")
public class SysStaffLanguageController {

    @Autowired
    private SysStaffLanguageService sysStaffLanguageService;

    /**
     * 查询所有语言能力列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysstafflanguage001", value = "查询所有语言能力列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysStaffLanguageQueryDo.class)
    @RequestMapping("/post/SysStaffLanguageController/queryAllSysStaffLanguageList")
    public void queryAllSysStaffLanguageList(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageService.queryAllSysStaffLanguageList(inputObject, outputObject);
    }

    /**
     * 员工语言能力信息录入
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffLanguageController/insertSysStaffLanguageMation")
    public void insertSysStaffLanguageMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageService.insertSysStaffLanguageMation(inputObject, outputObject);
    }

    /**
     * 编辑员工语言能力信息时回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffLanguageController/querySysStaffLanguageMationToEdit")
    public void querySysStaffLanguageMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageService.querySysStaffLanguageMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工语言能力信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffLanguageController/editSysStaffLanguageMationById")
    public void editSysStaffLanguageMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageService.editSysStaffLanguageMationById(inputObject, outputObject);
    }

    /**
     * 删除员工语言能力信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysStaffLanguageController/deleteSysStaffLanguageMationById")
    public void deleteSysStaffLanguageMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageService.deleteSysStaffLanguageMationById(inputObject, outputObject);
    }

    /**
     * 查询指定员工的语言能力列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysstafflanguage006", value = "查询指定员工的语言能力列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = PointStaffQueryDo.class)
    @RequestMapping("/post/SysStaffLanguageController/queryPointStaffSysStaffLanguageList")
    public void queryPointStaffSysStaffLanguageList(InputObject inputObject, OutputObject outputObject) {
        sysStaffLanguageService.queryPointStaffSysStaffLanguageList(inputObject, outputObject);
    }

}
