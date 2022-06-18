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
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/queryForumTagList")
    public void queryForumTagList(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.queryForumTagList(inputObject, outputObject);
    }


    /**
     * 添加论坛标签
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/insertForumTagMation")
    public void insertForumTagMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.insertForumTagMation(inputObject, outputObject);
    }

    /**
     * 删除论坛标签
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/deleteForumTagById")
    public void deleteForumTagById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.deleteForumTagById(inputObject, outputObject);
    }

    /**
     * 上线论坛标签
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/updateUpForumTagById")
    public void updateUpForumTagById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.updateUpForumTagById(inputObject, outputObject);
    }

    /**
     * 下线论坛标签
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/updateDownForumTagById")
    public void updateDownForumTagById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.updateDownForumTagById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的论坛标签信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/selectForumTagById")
    public void selectForumTagById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.selectForumTagById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的论坛标签信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/editForumTagMationById")
    public void editForumTagMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.editForumTagMationById(inputObject, outputObject);
    }

    /**
     * 论坛标签上移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/editForumTagMationOrderNumUpById")
    public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.editForumTagMationOrderNumUpById(inputObject, outputObject);
    }

    /**
     * 论坛标签下移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/editForumTagMationOrderNumDownById")
    public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.editForumTagMationOrderNumDownById(inputObject, outputObject);
    }

    /**
     * 获取已经上线的论坛标签列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ForumTagController/queryForumTagUpStateList")
    public void queryForumTagUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception {
        forumTagService.queryForumTagUpStateList(inputObject, outputObject);
    }

}
