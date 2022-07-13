/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CompanyChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyChatController {

    @Autowired
    private CompanyChatService companyChatService;

    /**
     * 获取好友列表，群聊信息，个人信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyChatController/getList")
    public void getList(InputObject inputObject, OutputObject outputObject) {
        companyChatService.getList(inputObject, outputObject);
    }

    /**
     * 编辑签名
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/CompanyChatController/editUserSignByUserId")
    public void editUserSignByUserId(InputObject inputObject, OutputObject outputObject) {
        companyChatService.editUserSignByUserId(inputObject, outputObject);
    }

}
