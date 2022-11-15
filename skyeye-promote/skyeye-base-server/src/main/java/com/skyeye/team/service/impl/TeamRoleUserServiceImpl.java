/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.entity.team.TeamRole;
import com.skyeye.eve.entity.team.TeamRoleUser;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.team.dao.TeamRoleUserDao;
import com.skyeye.team.service.TeamRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: TeamRoleUserServiceImpl
 * @Description: 团队用户管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:30
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamRoleUserServiceImpl extends SkyeyeBusinessServiceImpl<TeamRoleUserDao, TeamRoleUser> implements TeamRoleUserService {

    @Autowired
    private TeamRoleUserDao teamRoleUserDao;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 根据团队id查询该团队下的所有成员(结果的key为：团队id+角色id)
     *
     * @param teamIds
     * @return
     */
    @Override
    public Map<String, List<TeamRoleUser>> queryRoleUserByTeamIds(String... teamIds) {
        List<String> teamIdList = Arrays.asList(teamIds);
        if (CollectionUtil.isEmpty(teamIdList)) {
            return new HashMap<>();
        }
        QueryWrapper<TeamRoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamRoleUser::getTeamId), teamIdList);
        List<TeamRoleUser> teamRoleUserList = super.list(queryWrapper);
        if (CollectionUtil.isEmpty(teamRoleUserList)) {
            return new HashMap<>();
        }
        return teamRoleUserList.stream()
            .collect(Collectors.groupingBy(bean -> String.format(Locale.ROOT, "%s_%s", bean.getTeamId(), bean.getRoleId())));
    }

    /**
     * 设置用户信息
     *
     * @param teamRoleList
     */
    @Override
    public void setUserName(List<TeamRole> teamRoleList) {
        if (CollectionUtil.isEmpty(teamRoleList)) {
            return;
        }
        List<String> userIds = teamRoleList.stream()
            .filter(teamRole -> CollectionUtil.isNotEmpty(teamRole.getTeamRoleUserList()))
            .flatMap(teamRole -> teamRole.getTeamRoleUserList().stream())
            .filter(bean -> !ToolUtil.isBlank(bean.getUserId()))
            .map(bean -> bean.getUserId()).collect(Collectors.toList());
        Map<String, Map<String, Object>> userNameMap = iAuthUserService.queryUserNameList(userIds);
        teamRoleList.forEach(teamRole -> {
            teamRole.getTeamRoleUserList().forEach(teamRoleUser -> {
                Map<String, Object> userMation = userNameMap.get(teamRoleUser.getUserId());
                teamRoleUser.setUserMation(CollectionUtil.isEmpty(userMation) ? new HashMap<>() : userMation);
            });
        });
    }

    /**
     * 根据团队id批量删除用户信息
     *
     * @param teamIds
     */
    @Override
    public void deleteRoleUserByTeamIds(String... teamIds) {
        List<String> teamIdList = Arrays.asList(teamIds);
        if (CollectionUtil.isEmpty(teamIdList)) {
            return;
        }
        QueryWrapper<TeamRoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamRoleUser::getTeamId), teamIdList);
        remove(queryWrapper);
    }

    /**
     * 根据角色id批量删除用户信息
     *
     * @param teamId
     * @param roleIds
     */
    @Override
    public void deleteRoleUserByRoleIds(String teamId, String... roleIds) {
        List<String> roleIdList = Arrays.asList(roleIds);
        if (CollectionUtil.isEmpty(roleIdList) || ToolUtil.isBlank(teamId)) {
            return;
        }
        QueryWrapper<TeamRoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamRoleUser::getRoleId), roleIdList);
        queryWrapper.eq(MybatisPlusUtil.toColumns(TeamRoleUser::getTeamId), teamId);
        remove(queryWrapper);
    }
}
