/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.MyAgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAgencyController {

    @Autowired
    private MyAgencyService myAgencyService;

    /**
     * 获取我的代办列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MyAgencyController/queryMyAgencyList")
    public void queryMyAgencyList(InputObject inputObject, OutputObject outputObject) {
        myAgencyService.queryMyAgencyList(inputObject, outputObject);
    }

    /**
     * 取消代办提醒
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MyAgencyController/deleteMyAgencyList")
    public void deleteMyAgencyList(InputObject inputObject, OutputObject outputObject) {
        myAgencyService.deleteMyAgencyList(inputObject, outputObject);
    }

}
