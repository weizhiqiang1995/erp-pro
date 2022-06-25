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
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/queryRmGroupList")
    public void queryRmGroupList(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.queryRmGroupList(inputObject, outputObject);
    }

    /**
     * 添加小程序分组
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/insertRmGroupMation")
    public void insertRmGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.insertRmGroupMation(inputObject, outputObject);
    }

    /**
     * 删除小程序分组信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/deleteRmGroupById")
    public void deleteRmGroupById(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.deleteRmGroupById(inputObject, outputObject);
    }

    /**
     * 编辑小程序分组信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/queryRmGroupMationToEditById")
    public void queryRmGroupMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.queryRmGroupMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑小程序分组信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/editRmGroupMationById")
    public void editRmGroupMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.editRmGroupMationById(inputObject, outputObject);
    }

    /**
     * 小程序分组展示顺序上移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/editRmGroupSortTopById")
    public void editRmGroupSortTopById(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.editRmGroupSortTopById(inputObject, outputObject);
    }

    /**
     * 小程序分组展示顺序下移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/editRmGroupSortLowerById")
    public void editRmGroupSortLowerById(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.editRmGroupSortLowerById(inputObject, outputObject);
    }

    /**
     * 获取所有小程序分组根据小程序分类ID
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RmGroupController/queryRmGroupAllList")
    public void queryRmGroupAllList(InputObject inputObject, OutputObject outputObject) throws Exception {
        rmGroupService.queryRmGroupAllList(inputObject, outputObject);
    }

}
