/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.eve.entity.email.common.EmailQueryDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.UserEmailService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "邮件管理", tags = "邮件管理", modelName = "邮件模块")
public class UserEmailController {

    @Autowired
    private UserEmailService userEmailService;

    /**
     * 根据用户获取该用户绑定的邮箱信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/queryEmailListByUserId")
    public void queryEmailListByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.queryEmailListByUserId(inputObject, outputObject);
    }

    /**
     * 用户新增邮箱
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertEmailListByUserId")
    public void insertEmailListByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertEmailListByUserId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取收件箱里的邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertEmailListFromServiceByUserId")
    public void insertEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取收件箱内容
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "useremail004", value = "根据绑定邮箱id获取收件箱内容", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = EmailQueryDo.class)
    @RequestMapping("/post/UserEmailController/queryInboxEmailListByEmailId")
    public void queryInboxEmailListByEmailId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.queryInboxEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 获取邮件内容
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/queryEmailMationByEmailId")
    public void queryEmailMationByEmailId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.queryEmailMationByEmailId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取已发送邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertSendedEmailListFromServiceByUserId")
    public void insertSendedEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertSendedEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取已发送邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "useremail007", value = "根据绑定邮箱id获取已发送邮件", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = EmailQueryDo.class)
    @RequestMapping("/post/UserEmailController/querySendedEmailListByEmailId")
    public void querySendedEmailListByEmailId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.querySendedEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取已删除邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertDelsteEmailListFromServiceByUserId")
    public void insertDelsteEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertDelsteEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取已删除邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "useremail009", value = "根据绑定邮箱id获取已删除邮件", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = EmailQueryDo.class)
    @RequestMapping("/post/UserEmailController/queryDeleteEmailListByEmailId")
    public void queryDeleteEmailListByEmailId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.queryDeleteEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 从服务器上获取草稿箱邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertDraftsEmailListFromServiceByUserId")
    public void insertDraftsEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertDraftsEmailListFromServiceByUserId(inputObject, outputObject);
    }

    /**
     * 根据绑定邮箱id获取草稿箱邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "useremail011", value = "根据绑定邮箱id获取草稿箱邮件", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = EmailQueryDo.class)
    @RequestMapping("/post/UserEmailController/queryDraftsEmailListByEmailId")
    public void queryDraftsEmailListByEmailId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.queryDraftsEmailListByEmailId(inputObject, outputObject);
    }

    /**
     * 发送邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertToSendEmailMationByUserId")
    public void insertToSendEmailMationByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertToSendEmailMationByUserId(inputObject, outputObject);
    }

    /**
     * 保存邮件为草稿
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertToDraftsEmailMationByUserId")
    public void insertToDraftsEmailMationByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertToDraftsEmailMationByUserId(inputObject, outputObject);
    }

    /**
     * 编辑草稿箱内容展示时回显使用
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/queryDraftsEmailMationToEditByUserId")
    public void queryDraftsEmailMationToEditByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.queryDraftsEmailMationToEditByUserId(inputObject, outputObject);
    }

    /**
     * 草稿邮件修改
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/editToDraftsEmailMationByUserId")
    public void editToDraftsEmailMationByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.editToDraftsEmailMationByUserId(inputObject, outputObject);
    }

    /**
     * 草稿箱邮件发送
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertToSendEmailMationByEmailId")
    public void insertToSendEmailMationByEmailId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertToSendEmailMationByEmailId(inputObject, outputObject);
    }

    /**
     * 转发时进行信息回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/queryForwardEmailMationToEditByUserId")
    public void queryForwardEmailMationToEditByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.queryForwardEmailMationToEditByUserId(inputObject, outputObject);
    }

    /**
     * 转发邮件
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/UserEmailController/insertForwardToSendEmailMationByUserId")
    public void insertForwardToSendEmailMationByUserId(InputObject inputObject, OutputObject outputObject) {
        userEmailService.insertForwardToSendEmailMationByUserId(inputObject, outputObject);
    }

}
