/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.WagesModelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WagesModelTypeController {

    @Autowired
    private WagesModelTypeService wagesModelTypeService;

    /**
     * 获取薪资模板类型列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/queryWagesModelTypeList")
    public void queryWagesModelTypeList(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.queryWagesModelTypeList(inputObject, outputObject);
    }

    /**
     * 新增薪资模板类型信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/insertWagesModelTypeMation")
    public void insertWagesModelTypeMation(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.insertWagesModelTypeMation(inputObject, outputObject);
    }

    /**
     * 编辑薪资模板类型信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/queryWagesModelTypeMationToEditById")
    public void queryWagesModelTypeMationToEditById(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.queryWagesModelTypeMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑薪资模板类型信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/editWagesModelTypeMationById")
    public void editWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.editWagesModelTypeMationById(inputObject, outputObject);
    }

    /**
     * 删除薪资模板类型信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/deleteWagesModelTypeMationById")
    public void deleteWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.deleteWagesModelTypeMationById(inputObject, outputObject);
    }

    /**
     * 启用薪资模板类型信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/enableWagesModelTypeMationById")
    public void enableWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.enableWagesModelTypeMationById(inputObject, outputObject);
    }

    /**
     * 禁用薪资模板类型信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/disableWagesModelTypeMationById")
    public void disableWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.disableWagesModelTypeMationById(inputObject, outputObject);
    }

    /**
     * 获取已经启用的薪资模板类型列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/WagesModelTypeController/queryEnableWagesModelTypeList")
    public void queryEnableWagesModelTypeList(InputObject inputObject, OutputObject outputObject) {
        wagesModelTypeService.queryEnableWagesModelTypeList(inputObject, outputObject);
    }

}
