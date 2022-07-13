/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.MailListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailListController {

    @Autowired
    private MailListService mailListService;

    /**
     * 获取通讯录列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MailListController/queryMailMationList")
    public void queryMailMationList(InputObject inputObject, OutputObject outputObject) {
        mailListService.queryMailMationList(inputObject, outputObject);
    }

    /**
     * 新增通讯录(个人或者公共通讯录)
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MailListController/insertMailMation")
    public void insertMailMation(InputObject inputObject, OutputObject outputObject) {
        mailListService.insertMailMation(inputObject, outputObject);
    }

    /**
     * 删除通讯录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MailListController/deleteMailMationById")
    public void deleteMailMationById(InputObject inputObject, OutputObject outputObject) {
        mailListService.deleteMailMationById(inputObject, outputObject);
    }

    /**
     * 编辑通讯录进行回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MailListController/queryMailMationToEditById")
    public void queryMailMationToEditById(InputObject inputObject, OutputObject outputObject) {
        mailListService.queryMailMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑通讯录
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MailListController/editMailMationById")
    public void editMailMationById(InputObject inputObject, OutputObject outputObject) {
        mailListService.editMailMationById(inputObject, outputObject);
    }

    /**
     * 个人/公共通讯录详情
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/MailListController/queryMailMationDetailsById")
    public void queryMailMationDetailsById(InputObject inputObject, OutputObject outputObject) {
        mailListService.queryMailMationDetailsById(inputObject, outputObject);
    }

}
