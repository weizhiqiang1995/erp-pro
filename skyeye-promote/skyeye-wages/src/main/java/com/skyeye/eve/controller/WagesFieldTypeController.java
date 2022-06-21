/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.WagesFieldTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WagesFieldTypeController {

    @Autowired
    private WagesFieldTypeService wagesFieldTypeService;

    /**
     * 获取薪资字段列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/queryWagesFieldTypeList")
    public void queryWagesFieldTypeList(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.queryWagesFieldTypeList(inputObject, outputObject);
    }

    /**
     * 新增薪资字段信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/insertWagesFieldTypeMation")
    public void insertWagesFieldTypeMation(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.insertWagesFieldTypeMation(inputObject, outputObject);
    }

    /**
     * 编辑薪资字段信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/queryWagesFieldTypeMationToEditById")
    public void queryWagesFieldTypeMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.queryWagesFieldTypeMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑薪资字段信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/editWagesFieldTypeMationById")
    public void editWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.editWagesFieldTypeMationById(inputObject, outputObject);
    }

    /**
     * 删除薪资字段信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/deleteWagesFieldTypeMationById")
    public void deleteWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.deleteWagesFieldTypeMationById(inputObject, outputObject);
    }

    /**
     * 启用薪资字段信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/enableWagesFieldTypeMationById")
    public void enableWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.enableWagesFieldTypeMationById(inputObject, outputObject);
    }

    /**
     * 禁用薪资字段信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/disableWagesFieldTypeMationById")
    public void disableWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.disableWagesFieldTypeMationById(inputObject, outputObject);
    }

    /**
     * 获取已经启用的薪资字段列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/queryEnableWagesFieldTypeList")
    public void queryEnableWagesFieldTypeList(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.queryEnableWagesFieldTypeList(inputObject, outputObject);
    }

    /**
     * 获取系统薪资字段列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesFieldTypeController/querySysWagesFieldTypeList")
    public void querySysWagesFieldTypeList(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesFieldTypeService.querySysWagesFieldTypeList(inputObject, outputObject);
    }

}