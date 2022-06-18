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
import com.skyeye.eve.service.UserEmailService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserEmailController {

    @Autowired
    private UserEmailService userEmailService;

    /**
     * 根据用户获取该用户绑定的邮箱信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/queryEmailListByUserId")
    public void queryEmailListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.queryEmailListByUserId(inputObject, outputObject);
    }

    /**
     * 用户新增邮箱
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertEmailListByUserId")
    public void insertEmailListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertEmailListByUserId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取收件箱里的邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertEmailListFromServiceByUserId")
    public void insertEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取收件箱内容
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/queryInboxEmailListByEmailId")
    public void queryInboxEmailListByEmailId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.queryInboxEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 获取邮件内容
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/queryEmailMationByEmailId")
    public void queryEmailMationByEmailId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.queryEmailMationByEmailId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取已发送邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertSendedEmailListFromServiceByUserId")
    public void insertSendedEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertSendedEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取已发送邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/querySendedEmailListByEmailId")
    public void querySendedEmailListByEmailId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.querySendedEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取已删除邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertDelsteEmailListFromServiceByUserId")
    public void insertDelsteEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertDelsteEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取已删除邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/queryDeleteEmailListByEmailId")
    public void queryDeleteEmailListByEmailId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.queryDeleteEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取草稿箱邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertDraftsEmailListFromServiceByUserId")
    public void insertDraftsEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertDraftsEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取草稿箱邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/queryDraftsEmailListByEmailId")
    public void queryDraftsEmailListByEmailId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.queryDraftsEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 发送邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertToSendEmailMationByUserId")
    public void insertToSendEmailMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertToSendEmailMationByUserId(inputObject, outputObject);
    }

    /**
     * 保存邮件为草稿
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertToDraftsEmailMationByUserId")
    public void insertToDraftsEmailMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertToDraftsEmailMationByUserId(inputObject, outputObject);
    }

    /**
     * 编辑草稿箱内容展示时回显使用
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/queryDraftsEmailMationToEditByUserId")
    public void queryDraftsEmailMationToEditByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.queryDraftsEmailMationToEditByUserId(inputObject, outputObject);
    }

    /**
     * 草稿邮件修改
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/editToDraftsEmailMationByUserId")
    public void editToDraftsEmailMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.editToDraftsEmailMationByUserId(inputObject, outputObject);
    }

    /**
     * 草稿箱邮件发送
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertToSendEmailMationByEmailId")
    public void insertToSendEmailMationByEmailId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertToSendEmailMationByEmailId(inputObject, outputObject);
    }

    /**
     * 转发时进行信息回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/queryForwardEmailMationToEditByUserId")
    public void queryForwardEmailMationToEditByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.queryForwardEmailMationToEditByUserId(inputObject, outputObject);
    }

    /**
     * 转发邮件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/UserEmailController/insertForwardToSendEmailMationByUserId")
    public void insertForwardToSendEmailMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception {
        userEmailService.insertForwardToSendEmailMationByUserId(inputObject, outputObject);
    }

}
