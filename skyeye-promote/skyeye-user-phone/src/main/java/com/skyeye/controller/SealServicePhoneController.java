/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.SealServicePhoneService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SealServicePhoneController {

    @Autowired
    private SealServicePhoneService sealServicePhoneService;

    /**
     * 手机端查询不同状态下的工单数量
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SealServicePhoneController/queryNumberInEveryState")
    public void queryNumberInEveryState(InputObject inputObject, OutputObject outputObject) throws Exception {
        sealServicePhoneService.queryNumberInEveryState(inputObject, outputObject);
    }

    /**
     * 手机端签到
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SealServicePhoneController/insertSealSeServiceWaitToSignonMation")
    public void insertSealSeServiceWaitToSignonMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        sealServicePhoneService.insertSealSeServiceWaitToSignonMation(inputObject, outputObject);
    }

    /**
     * 根据工单id获取情况反馈列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SealServicePhoneController/queryFeedBackList")
    public void queryFeedBackList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sealServicePhoneService.queryFeedBackList(inputObject, outputObject);
    }

    /**
     * 获取所有配件信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SealServicePhoneController/queryAllPartsList")
    public void queryAllPartsList(InputObject inputObject, OutputObject outputObject) throws Exception {
        sealServicePhoneService.queryAllPartsList(inputObject, outputObject);
    }

}
