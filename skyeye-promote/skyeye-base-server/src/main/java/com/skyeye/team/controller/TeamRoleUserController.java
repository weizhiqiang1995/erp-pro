/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.team.TeamRoleUser;
import com.skyeye.team.service.TeamRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TeamRoleUserController
 * @Description: 团队用户管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:28
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "团队用户管理", tags = "团队用户管理", modelName = "团队模块")
public class TeamRoleUserController {

    @Autowired
    private TeamRoleUserService teamRoleUserService;

    /**
     * 新增团队用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertTeamRoleUser", value = "新增团队用户信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = TeamRoleUser.class)
    @RequestMapping("/post/TeamRoleController/insertTeamRoleUser")
    public void insertTeamRoleUser(InputObject inputObject, OutputObject outputObject) {
        teamRoleUserService.createEntity(inputObject, outputObject);
    }

    /**
     * 删除团队用户信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTeamRoleUserById", value = "删除团队用户信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "团队用户id", required = "required")})
    @RequestMapping("/post/TeamRoleController/deleteTeamRoleUserById")
    public void deleteTeamRoleUserById(InputObject inputObject, OutputObject outputObject) {
        teamRoleUserService.deleteById(inputObject, outputObject);
    }

}
