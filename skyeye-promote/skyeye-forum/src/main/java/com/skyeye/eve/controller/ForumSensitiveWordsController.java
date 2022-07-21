/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.ForumSensitiveWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumSensitiveWordsController {

    @Autowired
    private ForumSensitiveWordsService forumSensitiveWordsService;

    /**
     * 获取论坛敏感词列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumSensitiveWordsController/queryForumSensitiveWordsList")
    public void queryForumSensitiveWordsList(InputObject inputObject, OutputObject outputObject) {
        forumSensitiveWordsService.queryForumSensitiveWordsList(inputObject, outputObject);
    }


    /**
     * 添加论坛敏感词
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumSensitiveWordsController/insertForumSensitiveWordsMation")
    public void insertForumSensitiveWordsMation(InputObject inputObject, OutputObject outputObject) {
        forumSensitiveWordsService.insertForumSensitiveWordsMation(inputObject, outputObject);
    }

    /**
     * 删除论坛敏感词
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumSensitiveWordsController/deleteForumSensitiveWordsById")
    public void deleteForumSensitiveWordsById(InputObject inputObject, OutputObject outputObject) {
        forumSensitiveWordsService.deleteForumSensitiveWordsById(inputObject, outputObject);
    }

    /**
     * 通过id查找对应的论坛敏感词信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumSensitiveWordsController/selectForumSensitiveWordsById")
    public void selectForumSensitiveWordsById(InputObject inputObject, OutputObject outputObject) {
        forumSensitiveWordsService.selectForumSensitiveWordsById(inputObject, outputObject);
    }

    /**
     * 通过id编辑对应的论坛敏感词信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/ForumSensitiveWordsController/editForumSensitiveWordsMationById")
    public void editForumSensitiveWordsMationById(InputObject inputObject, OutputObject outputObject) {
        forumSensitiveWordsService.editForumSensitiveWordsMationById(inputObject, outputObject);
    }

}
