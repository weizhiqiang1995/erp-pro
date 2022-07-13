/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SmProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmProjectController {

    @Autowired
    private SmProjectService smProjectService;

    /**
     * 获取小程序列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SmProjectController/querySmProjectList")
    public void querySmProjectList(InputObject inputObject, OutputObject outputObject) {
        smProjectService.querySmProjectList(inputObject, outputObject);
    }

    /**
     * 新增小程序
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SmProjectController/insertSmProjectMation")
    public void insertSmProjectMation(InputObject inputObject, OutputObject outputObject) {
        smProjectService.insertSmProjectMation(inputObject, outputObject);
    }

    /**
     * 删除小程序
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SmProjectController/deleteSmProjectById")
    public void deleteSmProjectById(InputObject inputObject, OutputObject outputObject) {
        smProjectService.deleteSmProjectById(inputObject, outputObject);
    }

    /**
     * 编辑小程序信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SmProjectController/querySmProjectMationToEditById")
    public void querySmProjectMationToEditById(InputObject inputObject, OutputObject outputObject) {
        smProjectService.querySmProjectMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑小程序信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SmProjectController/editSmProjectMationById")
    public void editSmProjectMationById(InputObject inputObject, OutputObject outputObject) {
        smProjectService.editSmProjectMationById(inputObject, outputObject);
    }

    /**
     * 获取小程序组信息供展示
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SmProjectController/queryGroupMationList")
    public void queryGroupMationList(InputObject inputObject, OutputObject outputObject) {
        smProjectService.queryGroupMationList(inputObject, outputObject);
    }

    /**
     * 根据分组获取小程序组件信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SmProjectController/queryGroupMemberMationList")
    public void queryGroupMemberMationList(InputObject inputObject, OutputObject outputObject) {
        smProjectService.queryGroupMemberMationList(inputObject, outputObject);
    }

}
