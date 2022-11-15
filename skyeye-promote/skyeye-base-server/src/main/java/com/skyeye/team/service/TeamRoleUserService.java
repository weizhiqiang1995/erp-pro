/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.eve.entity.team.TeamRole;
import com.skyeye.eve.entity.team.TeamRoleUser;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeamRoleUserService
 * @Description: 团队用户管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:29
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TeamRoleUserService extends SkyeyeBusinessService<TeamRoleUser> {

    /**
     * 根据团队id查询该团队下的所有成员(结果的key为：团队id+角色id)
     *
     * @param teamIds
     * @return
     */
    Map<String, List<TeamRoleUser>> queryRoleUserByTeamIds(String... teamIds);

    /**
     * 设置用户信息
     *
     * @param teamRoleList
     */
    void setUserName(List<TeamRole> teamRoleList);

    /**
     * 根据团队id批量删除用户信息
     *
     * @param teamIds
     */
    void deleteRoleUserByTeamIds(String... teamIds);

    /**
     * 根据角色id批量删除用户信息
     *
     * @param teamId
     * @param roleIds
     */
    void deleteRoleUserByRoleIds(String teamId, String... roleIds);

}
