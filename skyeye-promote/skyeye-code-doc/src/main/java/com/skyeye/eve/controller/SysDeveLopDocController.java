/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysDeveLopDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysDeveLopDocController {

    @Autowired
    private SysDeveLopDocService sysDeveLopDocService;

    /**
     * 获取开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopTypeList")
    public void querySysDeveLopTypeList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopTypeList(inputObject, outputObject);
    }

    /**
     * 新增开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/insertSysDeveLopType")
    public void insertSysDeveLopType(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.insertSysDeveLopType(inputObject, outputObject);
    }

    /**
     * 编辑开发文档目录信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopTypeByIdToEdit")
    public void querySysDeveLopTypeByIdToEdit(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopTypeByIdToEdit(inputObject, outputObject);
    }

    /**
     * 编辑开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeById")
    public void editSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopTypeById(inputObject, outputObject);
    }

    /**
     * 删除开发文档目录信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/deleteSysDeveLopTypeById")
    public void deleteSysDeveLopTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.deleteSysDeveLopTypeById(inputObject, outputObject);
    }

    /**
     * 获取一级文档目录
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopTypeByFirstType")
    public void querySysDeveLopTypeByFirstType(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopTypeByFirstType(inputObject, outputObject);
    }

    /**
     * 开发文档目录上线
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeStateISupById")
    public void editSysDeveLopTypeStateISupById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopTypeStateISupById(inputObject, outputObject);
    }

    /**
     * 开发文档目录下线
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeStateISdownById")
    public void editSysDeveLopTypeStateISdownById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopTypeStateISdownById(inputObject, outputObject);
    }

    /**
     * 开发文档目录上移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeOrderByISupById")
    public void editSysDeveLopTypeOrderByISupById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopTypeOrderByISupById(inputObject, outputObject);
    }

    /**
     * 开发文档目录下移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopTypeOrderByISdownById")
    public void editSysDeveLopTypeOrderByISdownById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopTypeOrderByISdownById(inputObject, outputObject);
    }

    /**
     * 获取开发文档信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocList")
    public void querySysDeveLopDocList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopDocList(inputObject, outputObject);
    }

    /**
     * 新增开发文档信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/addSysDeveLopDoc")
    public void addSysDeveLopDoc(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.addSysDeveLopDoc(inputObject, outputObject);
    }

    /**
     * 编辑开发文档信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocByIdToEdit")
    public void querySysDeveLopDocByIdToEdit(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopDocByIdToEdit(inputObject, outputObject);
    }

    /**
     * 编辑开发文档信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocById")
    public void editSysDeveLopDocById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopDocById(inputObject, outputObject);
    }

    /**
     * 删除开发文档信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/deleteSysDeveLopDocById")
    public void deleteSysDeveLopDocById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.deleteSysDeveLopDocById(inputObject, outputObject);
    }

    /**
     * 开发文档上线
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocStateISupById")
    public void editSysDeveLopDocStateISupById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopDocStateISupById(inputObject, outputObject);
    }

    /**
     * 开发文档下线
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocStateISdownById")
    public void editSysDeveLopDocStateISdownById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopDocStateISdownById(inputObject, outputObject);
    }

    /**
     * 开发文档上移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocOrderByISupById")
    public void editSysDeveLopDocOrderByISupById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopDocOrderByISupById(inputObject, outputObject);
    }

    /**
     * 开发文档下移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/editSysDeveLopDocOrderByISdownById")
    public void editSysDeveLopDocOrderByISdownById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.editSysDeveLopDocOrderByISdownById(inputObject, outputObject);
    }

    /**
     * 获取一级分类列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopFirstTypeToShow")
    public void querySysDeveLopFirstTypeToShow(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopFirstTypeToShow(inputObject, outputObject);
    }

    /**
     * 获取二级分类列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopSecondTypeToShow")
    public void querySysDeveLopSecondTypeToShow(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopSecondTypeToShow(inputObject, outputObject);
    }

    /**
     * 获取文档标题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocToShow")
    public void querySysDeveLopDocToShow(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopDocToShow(inputObject, outputObject);
    }

    /**
     * 获取文档内容
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysDeveLopDocController/querySysDeveLopDocContentToShow")
    public void querySysDeveLopDocContentToShow(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysDeveLopDocService.querySysDeveLopDocContentToShow(inputObject, outputObject);
    }

}
