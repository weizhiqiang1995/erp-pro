/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CompanyTalkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyTalkGroupController {

    @Autowired
    private CompanyTalkGroupService companyTalkGroupService;

    /**
     * 添加群组信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/insertGroupMation")
    public void queryCodeModelList(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.insertGroupMation(inputObject, outputObject);
    }

    /**
     * 获取邀请信息/入群信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/queryGroupInvitationMation")
    public void queryGroupInvitationMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.queryGroupInvitationMation(inputObject, outputObject);
    }

    /**
     * 同意入群
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/editAgreeInGroupInvitationMation")
    public void editAgreeInGroupInvitationMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.editAgreeInGroupInvitationMation(inputObject, outputObject);
    }

    /**
     * 拒绝入群
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/editRefuseInGroupInvitationMation")
    public void editRefuseInGroupInvitationMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.editRefuseInGroupInvitationMation(inputObject, outputObject);
    }

    /**
     * 搜索群组列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/queryGroupMationList")
    public void queryGroupMationList(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.queryGroupMationList(inputObject, outputObject);
    }

    /**
     * 申请加入群聊
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/insertGroupMationToTalk")
    public void insertGroupMationToTalk(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.insertGroupMationToTalk(inputObject, outputObject);
    }

    /**
     * 获取群成员
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/queryGroupMemberByGroupId")
    public void queryGroupMemberByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.queryGroupMemberByGroupId(inputObject, outputObject);
    }

    /**
     * 获取聊天记录
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/queryChatLogByType")
    public void queryChatLogByType(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.queryChatLogByType(inputObject, outputObject);
    }

    /**
     * 退出群聊
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/editUserToExitGroup")
    public void editUserToExitGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.editUserToExitGroup(inputObject, outputObject);
    }

    /**
     * 解散群聊
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/CompanyTalkGroupController/editCreateToExitGroup")
    public void editCreateToExitGroup(InputObject inputObject, OutputObject outputObject) throws Exception {
        companyTalkGroupService.editCreateToExitGroup(inputObject, outputObject);
    }

}
