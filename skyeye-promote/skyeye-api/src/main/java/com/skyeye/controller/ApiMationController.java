/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.ApiMationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiMationController {

    @Autowired
    private ApiMationService apiMationService;

    /**
     * 通过id查找对应的api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ApiMationController/selectApiMationById")
    public void selectApiMationById(InputObject inputObject, OutputObject outputObject) {
        apiMationService.selectApiMationById(inputObject, outputObject);
    }

    /**
     * 编辑api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ApiMationController/editApiMationById")
    public void editApiMationById(InputObject inputObject, OutputObject outputObject) {
        apiMationService.editApiMationById(inputObject, outputObject);
    }

}
