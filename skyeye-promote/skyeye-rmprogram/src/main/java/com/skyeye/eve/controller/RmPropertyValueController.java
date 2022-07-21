/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.RmPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RmPropertyValueController {

    @Autowired
    private RmPropertyValueService rmPropertyValueService;

    /**
     * 获取小程序样式属性值列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmPropertyValueController/queryRmPropertyValueList")
    public void queryRmPropertyValueList(InputObject inputObject, OutputObject outputObject) {
        rmPropertyValueService.queryRmPropertyValueList(inputObject, outputObject);
    }

    /**
     * 添加小程序样式属性值信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmPropertyValueController/insertRmPropertyValueMation")
    public void insertRmPropertyValueMation(InputObject inputObject, OutputObject outputObject) {
        rmPropertyValueService.insertRmPropertyValueMation(inputObject, outputObject);
    }

    /**
     * 删除小程序样式属性值信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmPropertyValueController/deleteRmPropertyValueMationById")
    public void deleteRmPropertyValueMationById(InputObject inputObject, OutputObject outputObject) {
        rmPropertyValueService.deleteRmPropertyValueMationById(inputObject, outputObject);
    }

    /**
     * 编辑小程序样式属性值信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmPropertyValueController/queryRmPropertyValueMationToEditById")
    public void queryRmPropertyValueMationToEditById(InputObject inputObject, OutputObject outputObject) {
        rmPropertyValueService.queryRmPropertyValueMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑小程序样式属性值信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmPropertyValueController/editRmPropertyValueMationById")
    public void editRmPropertyValueMationById(InputObject inputObject, OutputObject outputObject) {
        rmPropertyValueService.editRmPropertyValueMationById(inputObject, outputObject);
    }

}
