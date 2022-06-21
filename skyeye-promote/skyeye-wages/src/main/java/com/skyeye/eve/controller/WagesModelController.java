/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.WagesModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WagesModelController {

    @Autowired
    private WagesModelService wagesModelService;

    /**
     * 获取薪资模板列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/queryWagesModelList")
    public void queryWagesModelList(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.queryWagesModelList(inputObject, outputObject);
    }

    /**
     * 新增薪资模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/insertWagesModelMation")
    public void insertWagesModelMation(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.insertWagesModelMation(inputObject, outputObject);
    }

    /**
     * 编辑薪资模板信息时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/queryWagesModelMationToEditById")
    public void queryWagesModelMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.queryWagesModelMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑薪资模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/editWagesModelMationById")
    public void editWagesModelMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.editWagesModelMationById(inputObject, outputObject);
    }

    /**
     * 删除薪资模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/deleteWagesModelMationById")
    public void deleteWagesModelMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.deleteWagesModelMationById(inputObject, outputObject);
    }

    /**
     * 启用薪资模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/enableWagesModelMationById")
    public void enableWagesModelMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.enableWagesModelMationById(inputObject, outputObject);
    }

    /**
     * 禁用薪资模板信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/disableWagesModelMationById")
    public void disableWagesModelMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.disableWagesModelMationById(inputObject, outputObject);
    }

    /**
     * 薪资模板详细信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/WagesModelController/queryWagesModelDetailMationById")
    public void queryWagesModelDetailMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
        wagesModelService.queryWagesModelDetailMationById(inputObject, outputObject);
    }

}