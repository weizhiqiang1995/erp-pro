/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SmProjectPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmProjectPageController {

    @Autowired
    private SmProjectPageService smProjectPageService;

    /**
     * 根据项目获取项目内部的页面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SmProjectPageController/queryProPageMationByProIdList")
    public void queryProPageMationByProIdList(InputObject inputObject, OutputObject outputObject) {
        smProjectPageService.queryProPageMationByProIdList(inputObject, outputObject);
    }

    /**
     * 添加项目内部的页面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SmProjectPageController/insertProPageMationByProId")
    public void insertProPageMationByProId(InputObject inputObject, OutputObject outputObject) {
        smProjectPageService.insertProPageMationByProId(inputObject, outputObject);
    }

    /**
     * 小程序页面展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SmProjectPageController/editSmProjectPageSortTopById")
    public void editSmProjectPageSortTopById(InputObject inputObject, OutputObject outputObject) {
        smProjectPageService.editSmProjectPageSortTopById(inputObject, outputObject);
    }

    /**
     * 小程序页面展示顺序下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SmProjectPageController/editSmProjectPageSortLowerById")
    public void editSmProjectPageSortLowerById(InputObject inputObject, OutputObject outputObject) {
        smProjectPageService.editSmProjectPageSortLowerById(inputObject, outputObject);
    }

    /**
     * 编辑小程序页面信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SmProjectPageController/querySmProjectPageMationToEditById")
    public void querySmProjectPageMationToEditById(InputObject inputObject, OutputObject outputObject) {
        smProjectPageService.querySmProjectPageMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑小程序页面信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SmProjectPageController/editSmProjectPageMationById")
    public void editSmProjectPageMationById(InputObject inputObject, OutputObject outputObject) {
        smProjectPageService.editSmProjectPageMationById(inputObject, outputObject);
    }

    /**
     * 删除小程序页面信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SmProjectPageController/deleteSmProjectPageMationById")
    public void deleteSmProjectPageMationById(InputObject inputObject, OutputObject outputObject) {
        smProjectPageService.deleteSmProjectPageMationById(inputObject, outputObject);
    }

}
