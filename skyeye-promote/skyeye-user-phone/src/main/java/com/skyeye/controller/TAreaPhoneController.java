/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.TAreaPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TAreaPhoneController {

    @Autowired
    private TAreaPhoneService tAreaPhoneService;

    /**
     * 手机端查询省市区数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/TAreaPhoneController/queryTAreaPhoneList")
    public void queryTAreaPhoneList(InputObject inputObject, OutputObject outputObject) {
        tAreaPhoneService.queryTAreaPhoneList(inputObject, outputObject);
    }

}
