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
import com.skyeye.eve.service.ForumReportService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumReportController {

    @Autowired
    private ForumReportService forumReportService;

    /**
     * 添加举报信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumReportController/insertForumReportMation")
    public void insertForumReportMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumReportService.insertForumReportMation(inputObject, outputObject);
    }

    /**
     * 获取论坛举报未审核列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumReportController/queryReportNoCheckList")
    public void queryReportNoCheckList(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumReportService.queryReportNoCheckList(inputObject, outputObject);
    }

    /**
     * 举报信息审核
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumReportController/editReportCheckMationById")
    public void editForumContentMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumReportService.editReportCheckMationById(inputObject, outputObject);
    }

    /**
     * 获取论坛举报已审核列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumReportController/queryReportCheckedList")
    public void queryReportCheckedList(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumReportService.queryReportCheckedList(inputObject, outputObject);
    }

    /**
     * 举报详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumReportController/queryForumReportMationToDetails")
    public void queryForumReportMationToDetails(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumReportService.queryForumReportMationToDetails(inputObject, outputObject);
    }

}
