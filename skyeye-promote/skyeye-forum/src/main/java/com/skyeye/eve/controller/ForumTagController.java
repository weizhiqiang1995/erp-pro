/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.ForumTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumTagController {

    @Autowired
    private ForumTagService forumTagService;

    /**
     * 获取论坛标签列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/queryForumTagList")
    public void queryForumTagList(InputObject inputObject, OutputObject outputObject) {
        forumTagService.queryForumTagList(inputObject, outputObject);
    }


    /**
     * 添加论坛标签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/insertForumTagMation")
    public void insertForumTagMation(InputObject inputObject, OutputObject outputObject) {
        forumTagService.insertForumTagMation(inputObject, outputObject);
    }

    /**
     * 删除论坛标签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/deleteForumTagById")
    public void deleteForumTagById(InputObject inputObject, OutputObject outputObject) {
        forumTagService.deleteForumTagById(inputObject, outputObject);
    }

    /**
     * 上线论坛标签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/updateUpForumTagById")
    public void updateUpForumTagById(InputObject inputObject, OutputObject outputObject) {
        forumTagService.updateUpForumTagById(inputObject, outputObject);
    }

    /**
     * 下线论坛标签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/updateDownForumTagById")
    public void updateDownForumTagById(InputObject inputObject, OutputObject outputObject) {
        forumTagService.updateDownForumTagById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的论坛标签信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/selectForumTagById")
    public void selectForumTagById(InputObject inputObject, OutputObject outputObject) {
        forumTagService.selectForumTagById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的论坛标签信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/editForumTagMationById")
    public void editForumTagMationById(InputObject inputObject, OutputObject outputObject) {
        forumTagService.editForumTagMationById(inputObject, outputObject);
    }

    /**
     * 论坛标签上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/editForumTagMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) {
        forumTagService.editForumTagMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 论坛标签下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/editForumTagMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) {
        forumTagService.editForumTagMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 获取已经上线的论坛标签列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumTagController/queryForumTagUpStateList")
    public void queryForumTagUpStateList(InputObject inputObject, OutputObject outputObject) {
        forumTagService.queryForumTagUpStateList(inputObject, outputObject);
    }

}
