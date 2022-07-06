/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.dict.SysDictTypeQueryDO;
import com.skyeye.eve.entity.sysmodel.SysEveModelQueryDo;
import com.skyeye.eve.service.SysEveModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveModelController
 * @Description: 系统编辑器模板管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "系统编辑器模板", tags = "系统编辑器模板", modelName = "系统公共模块")
public class SysEveModelController {

    @Autowired
    private SysEveModelService sysEveModelService;

    /**
     * 获取系统编辑器模板表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysevemodel001", value = "获取系统编辑器模板列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysEveModelQueryDo.class)
    @RequestMapping("/post/SysEveModelController/querySysEveModelList")
    public void querySysEveModelList(InputObject inputObject, OutputObject outputObject) {
        sysEveModelService.querySysEveModelList(inputObject, outputObject);
    }

    /**
     * 新增系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveModelController/insertSysEveModelMation")
    public void insertSysEveModelMation(InputObject inputObject, OutputObject outputObject) {
        sysEveModelService.insertSysEveModelMation(inputObject, outputObject);
    }

    /**
     * 删除系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveModelController/deleteSysEveModelById")
    public void deleteSysEveModelById(InputObject inputObject, OutputObject outputObject) {
        sysEveModelService.deleteSysEveModelById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveModelController/selectSysEveModelById")
    public void selectSysEveModelById(InputObject inputObject, OutputObject outputObject) {
        sysEveModelService.selectSysEveModelById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的系统编辑器模板
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveModelController/editSysEveModelMationById")
    public void editSysEveModelMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveModelService.editSysEveModelMationById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的系统编辑器模板详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveModelController/selectSysEveModelMationById")
    public void selectSysEveModelMationById(InputObject inputObject, OutputObject outputObject) {
        sysEveModelService.selectSysEveModelMationById(inputObject, outputObject);
    }

}
