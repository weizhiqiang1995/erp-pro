/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.team.entity.TeamTemplate;
import com.skyeye.team.service.TeamTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TeamTemplateController
 * @Description: 团队模板管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:23
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "团队模板管理", tags = "团队模板管理", modelName = "团队模块")
public class TeamTemplateController {

    @Autowired
    private TeamTemplateService teamTemplateService;

    /**
     * 查询团队模板列表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTeamTemplate", value = "查询团队模板列表信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/TeamTemplateController/queryTeamTemplate")
    public void queryTeamTemplate(InputObject inputObject, OutputObject outputObject) {
        teamTemplateService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑团队模板信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeTeamTemplate", value = "新增/编辑团队模板信息", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TeamTemplate.class)
    @RequestMapping("/post/TeamTemplateController/writeTeamTemplate")
    public void writeTeamTemplate(InputObject inputObject, OutputObject outputObject) {
        teamTemplateService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除团队模板信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTeamTemplateById", value = "删除团队模板信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "团队模板id", required = "required")})
    @RequestMapping("/post/TeamTemplateController/deleteTeamTemplateById")
    public void deleteTeamTemplateById(InputObject inputObject, OutputObject outputObject) {
        teamTemplateService.deleteById(inputObject, outputObject);
    }

    /**
     * 查询团队模板详情信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryTeamTemplateById", value = "查询团队模板详情信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "团队模板id", required = "required")})
    @RequestMapping("/post/TeamTemplateController/queryTeamTemplateById")
    public void queryTeamTemplateById(InputObject inputObject, OutputObject outputObject) {
        teamTemplateService.queryTeamMation(inputObject, outputObject);
    }

    /**
     * 根据受用对象查询已启用的团队模板列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnableTeamTemplateList", value = "根据受用对象查询已启用的团队模板列表", method = "GET", allUse = "2")
    @RequestMapping("/post/TeamTemplateController/queryEnableTeamTemplateList")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectType", name = "objectType", value = "受用对象", required = "required,num")})
    public void queryEnableTeamTemplateList(InputObject inputObject, OutputObject outputObject) {
        teamTemplateService.queryEnableTeamTemplateList(inputObject, outputObject);
    }

}
