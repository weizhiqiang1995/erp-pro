/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.entity.team.TeamRole;
import com.skyeye.eve.entity.team.TeamRoleUser;
import com.skyeye.team.dao.TeamRoleDao;
import com.skyeye.team.service.TeamRoleService;
import com.skyeye.team.service.TeamRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: TeamRoleServiceImpl
 * @Description: 团队角色管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:33
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamRoleServiceImpl extends SkyeyeBusinessServiceImpl<TeamRoleDao, TeamRole> implements TeamRoleService {

    @Autowired
    private TeamRoleUserService teamRoleUserService;

    /**
     * 根据团队id获取团队下的角色信息，并且包含角色下的用户信息
     *
     * @param teamIds
     * @return
     */
    @Override
    public Map<String, List<TeamRole>> queryTeamRoleByTeamIds(String... teamIds) {
        List<String> teamIdList = Arrays.asList(teamIds);
        if (CollectionUtil.isEmpty(teamIdList)) {
            return new HashMap<>();
        }
        QueryWrapper<TeamRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamRole::getTeamId), teamIdList);
        List<TeamRole> teamRoleList = super.list(queryWrapper);
        if (CollectionUtil.isEmpty(teamRoleList)) {
            return new HashMap<>();
        }
        // 查询用户信息
        Map<String, List<TeamRoleUser>> roleUserMap = teamRoleUserService.queryRoleUserByTeamIds(teamIds);
        for (TeamRole teamRole : teamRoleList) {
            String key = String.format(Locale.ROOT, "%s_%s", teamRole.getTeamId(), teamRole.getRoleId());
            List<TeamRoleUser> roleUser = roleUserMap.get(key);
            if (CollectionUtil.isEmpty(roleUser)) {
                roleUser = new ArrayList<>();
            }
            teamRole.setTeamRoleUserList(roleUser);
        }
        return teamRoleList.stream().collect(Collectors.groupingBy(TeamRole::getTeamId));
    }

    /**
     * 设置角色名称
     *
     * @param teamRoleList
     */
    @Override
    public void setUserRoleName(List<TeamRole> teamRoleList) {
        if (CollectionUtil.isEmpty(teamRoleList)) {
            return;
        }
        List<String> roleIds = teamRoleList.stream().map(TeamRole::getRoleId).collect(Collectors.toList());
        // 查询角色信息
        List<Map<String, Object>> sysDictDataList = iSysDictDataService.queryDictDataMationByIds(Joiner.on(CommonCharConstants.COMMA_MARK).join(roleIds));
        Map<String, Map<String, Object>> maps = sysDictDataList.stream()
            .collect(Collectors.toMap(item -> item.get("id").toString(), Function.identity()));
        // 循环设置角色名称
        teamRoleList.forEach(teamRole -> {
            Map<String, Object> map = maps.get(teamRole.getRoleId());
            if (CollectionUtil.isNotEmpty(map)) {
                teamRole.setName(map.get("dictName").toString());
            }
        });
    }

    /**
     * 根据团队id批量删除角色信息
     *
     * @param teamIds
     */
    @Override
    public void deleteRoleByTeamIds(String... teamIds) {
        List<String> teamIdList = Arrays.asList(teamIds);
        if (CollectionUtil.isEmpty(teamIdList)) {
            return;
        }
        QueryWrapper<TeamRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamRole::getTeamId), teamIdList);
        remove(queryWrapper);
    }

    /**
     * 根据角色id批量删除角色信息
     *
     * @param roleIds
     */
    @Override
    public void deleteRoleByRoleIds(String... roleIds) {
        List<String> roleIdList = Arrays.asList(roleIds);
        if (CollectionUtil.isEmpty(roleIdList)) {
            return;
        }
        QueryWrapper<TeamRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamRole::getRoleId), roleIdList);
        remove(queryWrapper);
    }
}
