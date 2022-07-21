/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.RmTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RmTypeController {

    @Autowired
    private RmTypeService rmTypeService;

    /**
     * 获取小程序分类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/queryRmTypeList")
    public void queryRmTypeList(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.queryRmTypeList(inputObject, outputObject);
    }

    /**
     * 新增小程序分类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/insertRmTypeMation")
    public void insertRmTypeMation(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.insertRmTypeMation(inputObject, outputObject);
    }

    /**
     * 删除小程序分类信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/deleteRmTypeById")
    public void deleteRmTypeById(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.deleteRmTypeById(inputObject, outputObject);
    }

    /**
     * 编辑小程序分类信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/queryRmTypeMationToEditById")
    public void queryRmTypeMationToEditById(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.queryRmTypeMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑小程序分类信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/editRmTypeMationById")
    public void editRmTypeMationById(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.editRmTypeMationById(inputObject, outputObject);
    }

    /**
     * 小程序分类展示顺序上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/editRmTypeSortTopById")
    public void editRmTypeSortTopById(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.editRmTypeSortTopById(inputObject, outputObject);
    }

    /**
     * 小程序分类展示顺序下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/editRmTypeSortLowerById")
    public void editRmTypeSortLowerById(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.editRmTypeSortLowerById(inputObject, outputObject);
    }

    /**
     * 获取所有小程序分类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/RmTypeController/queryRmTypeAllList")
    public void queryRmTypeAllList(InputObject inputObject, OutputObject outputObject) {
        rmTypeService.queryRmTypeAllList(inputObject, outputObject);
    }

}
