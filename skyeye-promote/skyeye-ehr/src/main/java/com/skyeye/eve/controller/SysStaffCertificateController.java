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
import com.skyeye.eve.service.SysStaffCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "员工证书", tags = "员工证书", modelName = "EHR模块")
@RestController
public class SysStaffCertificateController {

    @Autowired
    private SysStaffCertificateService sysStaffCertificateService;

    /**
     * 查询所有证书列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffCertificateController/queryAllSysStaffCertificateList")
    public void queryAllSysStaffCertificateList(InputObject inputObject, OutputObject outputObject) {
        sysStaffCertificateService.queryAllSysStaffCertificateList(inputObject, outputObject);
    }

    /**
     * 员工证书信息录入
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffCertificateController/insertSysStaffCertificateMation")
    public void insertSysStaffCertificateMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffCertificateService.insertSysStaffCertificateMation(inputObject, outputObject);
    }

    /**
     * 编辑员工证书信息时回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffCertificateController/querySysStaffCertificateMationToEdit")
    public void querySysStaffCertificateMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffCertificateService.querySysStaffCertificateMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工证书信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffCertificateController/editSysStaffCertificateMationById")
    public void editSysStaffCertificateMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffCertificateService.editSysStaffCertificateMationById(inputObject, outputObject);
    }

    /**
     * 删除员工证书信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffCertificateController/deleteSysStaffCertificateMationById")
    public void deleteSysStaffCertificateMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffCertificateService.deleteSysStaffCertificateMationById(inputObject, outputObject);
    }

    /**
     * 查询指定员工的证书列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysstaffcertificate006", value = "查询指定员工的证书列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = PointStaffQueryDo.class)
    @RequestMapping("/post/SysStaffCertificateController/queryPointStaffSysStaffCertificateList")
    public void queryPointStaffSysStaffCertificateList(InputObject inputObject, OutputObject outputObject) {
        sysStaffCertificateService.queryPointStaffSysStaffCertificateList(inputObject, outputObject);
    }

}
