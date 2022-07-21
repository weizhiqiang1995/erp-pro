/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.RmGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RmGroupController {

    @Autowired
    private RmGroupService rmGroupService;

    /**
     * 获取小程序分组列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/queryRmGroupList")
    public void queryRmGroupList(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.queryRmGroupList(inputObject, outputObject);
    }

    /**
     * 添加小程序分组
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/insertRmGroupMation")
    public void insertRmGroupMation(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.insertRmGroupMation(inputObject, outputObject);
    }

    /**
     * 删除小程序分组信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/deleteRmGroupById")
    public void deleteRmGroupById(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.deleteRmGroupById(inputObject, outputObject);
    }

    /**
     * 编辑小程序分组信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/queryRmGroupMationToEditById")
    public void queryRmGroupMationToEditById(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.queryRmGroupMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑小程序分组信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/editRmGroupMationById")
    public void editRmGroupMationById(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.editRmGroupMationById(inputObject, outputObject);
    }

    /**
     * 小程序分组展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/editRmGroupSortTopById")
    public void editRmGroupSortTopById(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.editRmGroupSortTopById(inputObject, outputObject);
    }

    /**
     * 小程序分组展示顺序下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/editRmGroupSortLowerById")
    public void editRmGroupSortLowerById(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.editRmGroupSortLowerById(inputObject, outputObject);
    }

    /**
     * 获取所有小程序分组根据小程序分类ID
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupController/queryRmGroupAllList")
    public void queryRmGroupAllList(InputObject inputObject, OutputObject outputObject) {
        rmGroupService.queryRmGroupAllList(inputObject, outputObject);
    }

}
