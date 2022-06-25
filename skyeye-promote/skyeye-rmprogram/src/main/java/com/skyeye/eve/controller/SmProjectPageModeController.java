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
import com.skyeye.eve.service.SmProjectPageModeService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmProjectPageModeController {

    @Autowired
    private SmProjectPageModeService smProjectPageModeService;

    /**
     * 根据项目页面获取该页面拥有的组件列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SmProjectPageModeController/queryProPageModeMationByPageIdList")
    public void queryProPageModeMationByPageIdList(InputObject inputObject, OutputObject outputObject) throws Exception {
        smProjectPageModeService.queryProPageModeMationByPageIdList(inputObject, outputObject);
    }

    /**
     * 插入项目页面对应的模块内容
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SmProjectPageModeController/editProPageModeMationByPageIdList")
    public void editProPageModeMationByPageIdList(InputObject inputObject, OutputObject outputObject) throws Exception {
        smProjectPageModeService.editProPageModeMationByPageIdList(inputObject, outputObject);
    }

    /**
     * 根据组件id获取标签属性
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SmProjectPageModeController/queryPropertyListByMemberId")
    public void queryPropertyListByMemberId(InputObject inputObject, OutputObject outputObject) throws Exception {
        smProjectPageModeService.queryPropertyListByMemberId(inputObject, outputObject);
    }

    /**
     * 导出当前页面为h5
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SmProjectPageModeController/queryPageToExportH5ByPageId")
    public void queryPageToExportH5ByPageId(InputObject inputObject, OutputObject outputObject) throws Exception {
        smProjectPageModeService.queryPageToExportH5ByPageId(inputObject, outputObject);
    }

}
