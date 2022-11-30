/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.team.entity.TeamRole;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeamRoleService
 * @Description: 团队角色管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:33
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TeamRoleService extends SkyeyeBusinessService<TeamRole> {

    /**
     * 根据团队id获取团队下的角色信息，并且包含角色下的用户信息
     *
     * @param teamIds
     * @return
     */
    Map<String, List<TeamRole>> queryTeamRoleByTeamIds(String... teamIds);

    /**
     * 设置角色名称
     *
     * @param teamRoleList
     */
    void setUserRoleName(List<TeamRole> teamRoleList);

    /**
     * 根据团队id批量删除角色信息
     *
     * @param teamIds
     */
    void deleteRoleByTeamIds(String... teamIds);

    /**
     * 根据角色id批量删除角色信息
     *
     * @param roleIds
     */
    void deleteRoleByRoleIds(String... roleIds);

}
