/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.SealServicePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SealServicePhoneController {

    @Autowired
    private SealServicePhoneService sealServicePhoneService;

    /**
     * 手机端查询不同状态下的工单数量
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SealServicePhoneController/queryNumberInEveryState")
    public void queryNumberInEveryState(InputObject inputObject, OutputObject outputObject) {
        sealServicePhoneService.queryNumberInEveryState(inputObject, outputObject);
    }

    /**
     * 手机端签到
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SealServicePhoneController/insertSealSeServiceWaitToSignonMation")
    public void insertSealSeServiceWaitToSignonMation(InputObject inputObject, OutputObject outputObject) {
        sealServicePhoneService.insertSealSeServiceWaitToSignonMation(inputObject, outputObject);
    }

    /**
     * 根据工单id获取情况反馈列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SealServicePhoneController/queryFeedBackList")
    public void queryFeedBackList(InputObject inputObject, OutputObject outputObject) {
        sealServicePhoneService.queryFeedBackList(inputObject, outputObject);
    }

    /**
     * 获取所有配件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SealServicePhoneController/queryAllPartsList")
    public void queryAllPartsList(InputObject inputObject, OutputObject outputObject) {
        sealServicePhoneService.queryAllPartsList(inputObject, outputObject);
    }

}
