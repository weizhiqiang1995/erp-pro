/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.discussion.entity.DiscussionReply;
import com.skyeye.discussion.entity.DiscussionReplyQueryDo;
import com.skyeye.discussion.service.DiscussionReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DiscussionReplyController
 * @Description: 讨论帖回帖管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 22:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "讨论帖回帖管理", tags = "讨论帖回帖管理", modelName = "基本服务")
public class DiscussionReplyController {

    @Autowired
    private DiscussionReplyService discussionReplyService;

    /**
     * 获取讨论帖回帖列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDiscussionReplyList", value = "获取讨论帖回帖列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DiscussionReplyQueryDo.class)
    @RequestMapping("/post/DiscussionReplyController/queryDiscussionReplyList")
    public void queryDiscussionReplyList(InputObject inputObject, OutputObject outputObject) {
        discussionReplyService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增讨论帖回帖信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "createDiscussionReply", value = "新增讨论帖回帖信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DiscussionReply.class)
    @RequestMapping("/post/DiscussionReplyController/createDiscussionReply")
    public void createDiscussionReply(InputObject inputObject, OutputObject outputObject) {
        discussionReplyService.createEntity(inputObject, outputObject);
    }

    /**
     * 根据id删除讨论帖回帖信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDiscussionReplyById", value = "根据id删除讨论帖回帖信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DiscussionReplyController/deleteDiscussionReplyById")
    public void deleteDiscussionReplyById(InputObject inputObject, OutputObject outputObject) {
        discussionReplyService.deleteById(inputObject, outputObject);
    }

}
