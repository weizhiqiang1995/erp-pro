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
import com.skyeye.team.entity.TeamRole;
import com.skyeye.team.service.TeamRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TeamRoleController
 * @Description: 团队角色管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:32
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "团队角色管理", tags = "团队角色管理", modelName = "团队模块")
public class TeamRoleController {

    @Autowired
    private TeamRoleService teamRoleService;

    /**
     * 新增团队角色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "insertTeamRole", value = "新增团队角色信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = TeamRole.class)
    @RequestMapping("/post/TeamRoleController/insertTeamRole")
    public void insertTeamRole(InputObject inputObject, OutputObject outputObject) {
        teamRoleService.createEntity(inputObject, outputObject);
    }

    /**
     * 删除团队角色信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteTeamRoleById", value = "删除团队角色信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "团队角色id", required = "required")})
    @RequestMapping("/post/TeamRoleController/deleteTeamRoleById")
    public void deleteTeamRoleById(InputObject inputObject, OutputObject outputObject) {
        teamRoleService.deleteById(inputObject, outputObject);
    }

}
