/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.RmGroupMemberService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RmGroupMemberController {

    @Autowired
    private RmGroupMemberService rmGroupMemberService;

    /**
     * 获取小程序组件列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/queryRmGroupMemberList")
    public void queryRmGroupMemberList(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.queryRmGroupMemberList(inputObject, outputObject);
    }

    /**
     * 添加小程序组件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/insertRmGroupMemberMation")
    public void insertRmGroupMemberMation(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.insertRmGroupMemberMation(inputObject, outputObject);
    }

    /**
     * 小程序组件展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/editRmGroupMemberSortTopById")
    public void editRmGroupMemberSortTopById(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.editRmGroupMemberSortTopById(inputObject, outputObject);
    }

    /**
     * 小程序组件展示顺序下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/editRmGroupMemberSortLowerById")
    public void editRmGroupMemberSortLowerById(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.editRmGroupMemberSortLowerById(inputObject, outputObject);
    }

    /**
     * 删除小程序组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/deleteRmGroupMemberById")
    public void deleteRmGroupMemberById(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.deleteRmGroupMemberById(inputObject, outputObject);
    }

    /**
     * 编辑小程序组件信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/queryRmGroupMemberMationToEditById")
    public void queryRmGroupMemberMationToEditById(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.queryRmGroupMemberMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑小程序组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/editRmGroupMemberMationById")
    public void editRmGroupMemberMationById(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.editRmGroupMemberMationById(inputObject, outputObject);
    }

    /**
     * 编辑小程序组件和标签属性的绑定信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/editRmGroupMemberAndPropertyMationById")
    public void editRmGroupMemberAndPropertyMationById(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.editRmGroupMemberAndPropertyMationById(inputObject, outputObject);
    }

    /**
     * 获取小程序组件和标签属性的绑定信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmGroupMemberController/queryRmGroupMemberAndPropertyMationById")
    public void queryRmGroupMemberAndPropertyMationById(InputObject inputObject, OutputObject outputObject) {
        rmGroupMemberService.queryRmGroupMemberAndPropertyMationById(inputObject, outputObject);
    }

}
