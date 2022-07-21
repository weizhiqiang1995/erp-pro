/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.ForumContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumContentController {

    @Autowired
    private ForumContentService forumContentService;

    /**
     * 获取我的帖子列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryMyForumContentList")
    public void queryMyForumContentList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryMyForumContentList(inputObject, outputObject);
    }

    /**
     * 新增我的帖子
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/insertForumContentMation")
    public void insertForumContentMation(InputObject inputObject, OutputObject outputObject) {
        forumContentService.insertForumContentMation(inputObject, outputObject);
    }

    /**
     * 删除帖子
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/deleteForumContentById")
    public void deleteForumContentById(InputObject inputObject, OutputObject outputObject) {
        forumContentService.deleteForumContentById(inputObject, outputObject);
    }

    /**
     * 查询帖子信息用以编辑
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryForumContentMationById")
    public void queryForumContentMationById(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryForumContentMationById(inputObject, outputObject);
    }

    /**
     * 编辑帖子信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/editForumContentMationById")
    public void editForumContentMationById(InputObject inputObject, OutputObject outputObject) {
        forumContentService.editForumContentMationById(inputObject, outputObject);
    }

    /**
     * 帖子详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryForumContentMationToDetails")
    public void queryForumContentMationToDetails(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryForumContentMationToDetails(inputObject, outputObject);
    }

    /**
     * 获取最新帖子
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryNewForumContentList")
    public void queryNewForumContentList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryNewForumContentList(inputObject, outputObject);
    }

    /**
     * 新增帖子评论
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/insertForumCommentMation")
    public void insertForumCommentMation(InputObject inputObject, OutputObject outputObject) {
        forumContentService.insertForumCommentMation(inputObject, outputObject);
    }

    /**
     * 获取帖子评论信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryForumCommentList")
    public void queryForumCommentList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryForumCommentList(inputObject, outputObject);
    }

    /**
     * 新增帖子评论回复
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/insertForumReplyMation")
    public void insertForumReplyMation(InputObject inputObject, OutputObject outputObject) {
        forumContentService.insertForumReplyMation(inputObject, outputObject);
    }

    /**
     * 获取帖子评论回复信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryForumReplyList")
    public void queryForumReplyList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryForumReplyList(inputObject, outputObject);
    }

    /**
     * 获取我的浏览信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryForumMyBrowerList")
    public void queryForumMyBrowerList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryForumMyBrowerList(inputObject, outputObject);
    }

    /**
     * 获取最新评论
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryNewCommentList")
    public void queryNewCommentList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryNewCommentList(inputObject, outputObject);
    }

    /**
     * 根据标签id获取帖子列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryForumListByTagId")
    public void queryForumListByTagId(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryForumListByTagId(inputObject, outputObject);
    }

    /**
     * 获取热门标签
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryHotTagList")
    public void queryHotTagList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryHotTagList(inputObject, outputObject);
    }

    /**
     * 获取活跃用户
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryActiveUsersList")
    public void queryActiveUsersList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryActiveUsersList(inputObject, outputObject);
    }

    /**
     * 获取热门贴
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryHotForumList")
    public void queryHotForumList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryHotForumList(inputObject, outputObject);
    }

    /**
     * 获取用户搜索的帖子
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/querySearchForumList")
    public void querySearchForumList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.querySearchForumList(inputObject, outputObject);
    }

    /**
     * 获取solr上次同步数据的时间
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/querySolrSynchronousTime")
    public void querySolrSynchronousTime(InputObject inputObject, OutputObject outputObject) {
        forumContentService.querySolrSynchronousTime(inputObject, outputObject);
    }

    /**
     * solr同步数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/updateSolrSynchronousData")
    public void updateSolrSynchronousData(InputObject inputObject, OutputObject outputObject) {
        forumContentService.updateSolrSynchronousData(inputObject, outputObject);
    }

    /**
     * 获取我的帖子列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryMyCommentList")
    public void queryMyCommentList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryMyCommentList(inputObject, outputObject);
    }

    /**
     * 根据评论id删除评论
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/deleteCommentById")
    public void deleteCommentById(InputObject inputObject, OutputObject outputObject) {
        forumContentService.deleteCommentById(inputObject, outputObject);
    }

    /**
     * 获取我的通知列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/queryMyNoticeList")
    public void queryMyNoticeList(InputObject inputObject, OutputObject outputObject) {
        forumContentService.queryMyNoticeList(inputObject, outputObject);
    }

    /**
     * 根据通知id删除通知
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumContentController/deleteNoticeById")
    public void deleteNoticeById(InputObject inputObject, OutputObject outputObject) {
        forumContentService.deleteNoticeById(inputObject, outputObject);
    }

}
