/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import com.skyeye.eve.service.SysStaffContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "员工合同信息", tags = "员工合同信息", modelName = "EHR模块")
public class SysStaffContractController {

    @Autowired
    private SysStaffContractService sysStaffContractService;

    /**
     * 查询所有合同列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffContractController/queryAllSysStaffContractList")
    public void queryAllSysStaffContractList(InputObject inputObject, OutputObject outputObject) {
        sysStaffContractService.queryAllSysStaffContractList(inputObject, outputObject);
    }

    /**
     * 员工合同信息录入
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffContractController/insertSysStaffContractMation")
    public void insertSysStaffContractMation(InputObject inputObject, OutputObject outputObject) {
        sysStaffContractService.insertSysStaffContractMation(inputObject, outputObject);
    }

    /**
     * 编辑员工合同信息时回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffContractController/querySysStaffContractMationToEdit")
    public void querySysStaffContractMationToEdit(InputObject inputObject, OutputObject outputObject) {
        sysStaffContractService.querySysStaffContractMationToEdit(inputObject, outputObject);
    }

    /**
     * 编辑员工合同信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffContractController/editSysStaffContractMationById")
    public void editSysStaffContractMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffContractService.editSysStaffContractMationById(inputObject, outputObject);
    }

    /**
     * 删除员工合同信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysStaffContractController/deleteSysStaffContractMationById")
    public void deleteSysStaffContractMationById(InputObject inputObject, OutputObject outputObject) {
        sysStaffContractService.deleteSysStaffContractMationById(inputObject, outputObject);
    }

    /**
     * 查询指定员工的合同列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysstaffcontract006", value = "查询指定员工的合同列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = PointStaffQueryDo.class)
    @RequestMapping("/post/SysStaffContractController/queryPointStaffSysStaffContractList")
    public void queryPointStaffSysStaffContractList(InputObject inputObject, OutputObject outputObject) {
        sysStaffContractService.queryPointStaffSysStaffContractList(inputObject, outputObject);
    }

    /**
     * 员工合同签约
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysstaffcontract007", value = "员工合同签约", method = "GET", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "rowId", name = "id", value = "合同信息id", required = "required")})
    @RequestMapping("/post/SysStaffContractController/signSysStaffContractById")
    public void signSysStaffContractById(InputObject inputObject, OutputObject outputObject) {
        sysStaffContractService.signSysStaffContractById(inputObject, outputObject);
    }

}
