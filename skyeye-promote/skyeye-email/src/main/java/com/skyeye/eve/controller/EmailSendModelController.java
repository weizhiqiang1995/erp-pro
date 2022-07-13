/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.EmailSendModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSendModelController {

    @Autowired
    private EmailSendModelService emailSendModelService;

    /**
     * 可根据标题模糊+收件人分页获取邮箱发送模板列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/EmailSendModel/queryEmailSendModelList")
    public void queryEmailSendModelList(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.queryEmailSendModelList(inputObject, outputObject);
    }

    /**
     * 用户新增邮件发送模板
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/EmailSendModel/insertEmailSendModel")
    public void insertEmailSendModel(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.insertEmailSendModel(inputObject, outputObject);
    }

    /**
     * 根据邮箱模板id获取邮件模板详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/EmailSendModel/queryEmailSendModelInfoById")
    public void queryEmailSendModelInfoById(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.queryEmailSendModelInfoById(inputObject, outputObject);
    }

    /**
     * 根据邮件模板id删除该模板
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/EmailSendModel/delEmailSendModelById")
    public void delEmailSendModelById(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.delEmailSendModelById(inputObject, outputObject);
    }

    /**
     * 根据邮件模板id更新模板内容
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/EmailSendModel/updateEmailSendModelById")
    public void updateEmailSendModelById(InputObject inputObject, OutputObject outputObject) {
        emailSendModelService.updateEmailSendModelById(inputObject, outputObject);
    }

}
