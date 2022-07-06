/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.codedoc.develop.SysDeveLopDocQueryDo;
import com.skyeye.eve.service.SysDeveLopDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysDeveLopDocController
 * @Description: 开发文档管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/6 10:03
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "开发文档管理", tags = "开发文档管理", modelName = "代码生成器")
public class SysDeveLopDocController {

    @Autowired
    private SysDeveLopDocService sysDeveLopDocService;

    /**
     * 获取开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopTypeList")
    public void querySysDeveLopTypeList(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopTypeList(inputObject, outputObject);
    }

    /**
     * 新增开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/insertSysDeveLopType")
    public void insertSysDeveLopType(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.insertSysDeveLopType(inputObject, outputObject);
    }

    /**
     * 编辑开发文档目录信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopTypeByIdToEdit")
    public void querySysDeveLopTypeByIdToEdit(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopTypeByIdToEdit(inputObject, outputObject);
    }

    /**
     * 编辑开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeById")
    public void editSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopTypeById(inputObject, outputObject);
    }

    /**
     * 删除开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/deleteSysDeveLopTypeById")
    public void deleteSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.deleteSysDeveLopTypeById(inputObject, outputObject);
    }

    /**
     * 获取一级文档目录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopTypeByFirstType")
    public void querySysDeveLopTypeByFirstType(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopTypeByFirstType(inputObject, outputObject);
    }

    /**
     * 开发文档目录上线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeStateISupById")
    public void editSysDeveLopTypeStateISupById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopTypeStateISupById(inputObject, outputObject);
    }

    /**
     * 开发文档目录下线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeStateISdownById")
    public void editSysDeveLopTypeStateISdownById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopTypeStateISdownById(inputObject, outputObject);
    }

    /**
     * 开发文档目录上移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeOrderByISupById")
    public void editSysDeveLopTypeOrderByISupById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopTypeOrderByISupById(inputObject, outputObject);
    }

    /**
     * 开发文档目录下移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeOrderByISdownById")
    public void editSysDeveLopTypeOrderByISdownById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopTypeOrderByISdownById(inputObject, outputObject);
    }

    /**
     * 获取开发文档信息
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "sysdevelopdoc011", value = "获取开发文档信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysDeveLopDocQueryDo.class)
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocList")
    public void querySysDeveLopDocList(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopDocList(inputObject, outputObject);
    }

    /**
     * 新增开发文档信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/addSysDeveLopDoc")
    public void addSysDeveLopDoc(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.addSysDeveLopDoc(inputObject, outputObject);
    }

    /**
     * 编辑开发文档信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocByIdToEdit")
    public void querySysDeveLopDocByIdToEdit(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopDocByIdToEdit(inputObject, outputObject);
    }

    /**
     * 编辑开发文档信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocById")
    public void editSysDeveLopDocById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopDocById(inputObject, outputObject);
    }

    /**
     * 删除开发文档信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/deleteSysDeveLopDocById")
    public void deleteSysDeveLopDocById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.deleteSysDeveLopDocById(inputObject, outputObject);
    }

    /**
     * 开发文档上线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocStateISupById")
    public void editSysDeveLopDocStateISupById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopDocStateISupById(inputObject, outputObject);
    }

    /**
     * 开发文档下线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocStateISdownById")
    public void editSysDeveLopDocStateISdownById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopDocStateISdownById(inputObject, outputObject);
    }

    /**
     * 开发文档上移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocOrderByISupById")
    public void editSysDeveLopDocOrderByISupById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopDocOrderByISupById(inputObject, outputObject);
    }

    /**
     * 开发文档下移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocOrderByISdownById")
    public void editSysDeveLopDocOrderByISdownById(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.editSysDeveLopDocOrderByISdownById(inputObject, outputObject);
    }

    /**
     * 获取一级分类列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopFirstTypeToShow")
    public void querySysDeveLopFirstTypeToShow(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopFirstTypeToShow(inputObject, outputObject);
    }

    /**
     * 获取二级分类列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopSecondTypeToShow")
    public void querySysDeveLopSecondTypeToShow(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopSecondTypeToShow(inputObject, outputObject);
    }

    /**
     * 获取文档标题
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocToShow")
    public void querySysDeveLopDocToShow(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopDocToShow(inputObject, outputObject);
    }

    /**
     * 获取文档内容
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocContentToShow")
    public void querySysDeveLopDocContentToShow(InputObject inputObject, OutputObject outputObject) {
        sysDeveLopDocService.querySysDeveLopDocContentToShow(inputObject, outputObject);
    }

}
