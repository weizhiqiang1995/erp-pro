/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.discussion.entity.Discussion;
import com.skyeye.discussion.service.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DiscussionController
 * @Description: 讨论帖管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 22:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "讨论帖管理", tags = "讨论帖管理", modelName = "基本服务")
public class DiscussionController {

    @Autowired
    private DiscussionService discussionService;

    /**
     * 获取讨论帖列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDiscussionList", value = "获取讨论帖列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DiscussionController/queryDiscussionList")
    public void queryDiscussionList(InputObject inputObject, OutputObject outputObject) {
        discussionService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑讨论帖信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDiscussion", value = "新增/编辑讨论帖信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Discussion.class)
    @RequestMapping("/post/DiscussionController/writeDiscussion")
    public void writeDiscussion(InputObject inputObject, OutputObject outputObject) {
        discussionService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取讨论帖信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDiscussionById", value = "根据id获取讨论帖信息", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DiscussionController/queryDiscussionById")
    public void queryDiscussionById(InputObject inputObject, OutputObject outputObject) {
        discussionService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除讨论帖信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDiscussionById", value = "根据id删除讨论帖信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DiscussionController/deleteDiscussionById")
    public void deleteDiscussionById(InputObject inputObject, OutputObject outputObject) {
        discussionService.deleteById(inputObject, outputObject);
    }

}
