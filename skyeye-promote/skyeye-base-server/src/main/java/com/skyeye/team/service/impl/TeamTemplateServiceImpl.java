/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.team.TeamRole;
import com.skyeye.eve.entity.team.TeamRoleUser;
import com.skyeye.eve.entity.team.TeamTemplate;
import com.skyeye.team.dao.TeamTemplateDao;
import com.skyeye.team.service.TeamRoleService;
import com.skyeye.team.service.TeamRoleUserService;
import com.skyeye.team.service.TeamTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName: TeamTemplateServiceImpl
 * @Description: 团队模板管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:24
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamTemplateServiceImpl extends SkyeyeBusinessServiceImpl<TeamTemplateDao, TeamTemplate> implements TeamTemplateService {

    @Autowired
    private TeamTemplateDao teamTemplateDao;

    @Autowired
    private TeamRoleService teamRoleService;

    @Autowired
    private TeamRoleUserService teamRoleUserService;

    @Override
    public void createPostpose(TeamTemplate entity, String userId) {
        String teamTemplateId = entity.getId();
        List<TeamRole> teamRoleList = entity.getTeamRoleList();
        String serviceClassName = getServiceClassName();
        saveNewTeamRole(teamTemplateId, teamRoleList, userId, serviceClassName);
    }

    @Override
    public void updatePostpose(TeamTemplate entity, String userId) {
        String teamTemplateId = entity.getId();
        List<TeamRole> newTeamRoleList = entity.getTeamRoleList();
        if (CollectionUtil.isNotEmpty(newTeamRoleList)) {
            String serviceClassName = getServiceClassName();
            List<TeamRole> oldTeamRoleMapList = selectById(teamTemplateId).getTeamRoleList();
            List<String> oldRoleKeys = oldTeamRoleMapList.stream().map(bean -> bean.getRoleId()).collect(Collectors.toList());
            List<String> newRoleKeys = newTeamRoleList.stream().map(bean -> bean.getRoleId()).collect(Collectors.toList());
            // (新数据 - 旧数据) 差集(团队角色)添加到数据库
            List<TeamRole> addTeamRoleMaps = newTeamRoleList.stream()
                .filter(item -> !oldRoleKeys.contains(item.getRoleId())).collect(Collectors.toList());
            saveNewTeamRole(teamTemplateId, addTeamRoleMaps, userId, serviceClassName);

            // 删除入参中没有但数据库中有的角色以及角色下的用户信息
            deleteRoleAndPricipal(teamTemplateId, newRoleKeys, oldTeamRoleMapList);

            // 新数据和旧数据交集(团队角色)需要编辑的数据
            List<TeamRole> oldTeamRole = newTeamRoleList.stream()
                .filter(item -> oldRoleKeys.contains(item.getRoleId())).collect(Collectors.toList());

            // 新数据 - 旧数据 差集(角色用户)更新到数据库
            // 入参里面的该团队模板下的用户信息
            List<TeamRoleUser> newRoleUser = getTeamRoleUserList(oldTeamRole);
            List<String> newRoleUserKeys = newRoleUser.stream().map(bean -> bean.getTeamId() + bean.getRoleId() + bean.getUserId()).collect(Collectors.toList());
            // 数据库里面的该团队模板下的用户信息
            List<TeamRoleUser> oldRoleUser = getTeamRoleUserList(oldTeamRole);
            List<String> oldRoleUserKeys = oldRoleUser.stream().map(bean -> bean.getTeamId() + bean.getRoleId() + bean.getUserId()).collect(Collectors.toList());
            // 需要新增的用户信息
            List<TeamRoleUser> addParticipants = newRoleUser.stream()
                .filter(predicate -> !oldRoleUserKeys.contains(predicate.getTeamId() + predicate.getRoleId() + predicate.getUserId())).collect(Collectors.toList());
            addParticipants.forEach(p -> {
                p.setId(null);
            });
            if (CollectionUtil.isNotEmpty(addParticipants)) {
                teamRoleUserService.createEntity(addParticipants, userId);
            }
            // 删除用户关联信息
            List<TeamRoleUser> deleteRoleUser = oldRoleUser.stream()
                .filter(predicate -> !newRoleUserKeys.contains(predicate.getTeamId() + predicate.getRoleId() + predicate.getUserId())).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(deleteRoleUser)) {
                List<String> deleteRoleUserLinkIds = deleteRoleUser.stream().map(TeamRoleUser::getId).collect(Collectors.toList());
                teamRoleUserService.deleteById(deleteRoleUserLinkIds);
            }
        } else {
            // 如果团队角色集合为空,则删除旧的团队角色和角色用户数据
            // 1. 删除团队模板与角色的关系
            teamRoleService.deleteRoleByTeamIds(teamTemplateId);
            // 2. 删除角色下的用户
            teamRoleUserService.deleteRoleUserByTeamIds(teamTemplateId);
        }
    }

    public void saveNewTeamRole(String teamTemplateId, List<TeamRole> teamRoleList, String serviceClassName, String userId) {
        if (CollectionUtil.isNotEmpty(teamRoleList)) {
            saveRole(userId, teamTemplateId, teamRoleList, serviceClassName);
            saveRoleUser(userId, teamTemplateId, teamRoleList, serviceClassName);
        }
    }

    private void saveRoleUser(String userId, String teamTemplateId, List<TeamRole> teamRoleList, String serviceClassName) {
        List<TeamRoleUser> teamRoleUserList = teamRoleList.stream()
            .filter(teamRole -> CollectionUtil.isNotEmpty(teamRole.getTeamRoleUserList()))
            .flatMap(teamRole -> {
                teamRole.getTeamRoleUserList().forEach(teamRoleUser -> {
                    teamRoleUser.setRoleId(teamRole.getRoleId());
                    teamRoleUser.setTeamId(teamTemplateId);
                    teamRoleUser.setTeamKey(serviceClassName);
                });
                return teamRole.getTeamRoleUserList().stream();
            }).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(teamRoleUserList)) {
            teamRoleUserService.createEntity(teamRoleUserList, userId);
        }
    }

    private List<TeamRoleUser> getTeamRoleUserList(List<TeamRole> teamRoleList) {
        return teamRoleList.stream()
            .filter(teamRole -> CollectionUtil.isNotEmpty(teamRole.getTeamRoleUserList()))
            .flatMap(teamRole -> teamRole.getTeamRoleUserList().stream())
            .filter(Objects::nonNull).collect(Collectors.toList());
    }

    private void saveRole(String userId, String teamTemplateId, List<TeamRole> teamRoleList, String serviceClassName) {
        teamRoleList.forEach(teamRole -> {
            teamRole.setTeamId(teamTemplateId);
            teamRole.setTeamKey(serviceClassName);
        });
        teamRoleService.createEntity(teamRoleList, userId);
    }

    private void deleteRoleAndPricipal(String teamTemplateId, List<String> newRoleKeys, List<TeamRole> oldTeamRoleList) {
        // (旧数据 - 新数据) 差集(团队角色)从数据库删除
        List<TeamRole> deleteTeamRoleMaps = oldTeamRoleList.stream()
            .filter(item -> !newRoleKeys.contains(item.getRoleId())).collect(Collectors.toList());
        List<String> removeRoleIds = deleteTeamRoleMaps.stream().map(teamRole -> teamRole.getRoleId()).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(removeRoleIds)) {
            teamRoleService.deleteRoleByRoleIds(removeRoleIds.toArray(new String[]{}));
            teamRoleUserService.deleteRoleUserByRoleIds(teamTemplateId, removeRoleIds.toArray(new String[]{}));
        }
    }

    protected TeamTemplate getDataFromDb(String id) {
        TeamTemplate teamTemplate = super.getDataFromDb(id);
        Map<String, List<TeamRole>> teamRoleMap = teamRoleService.queryTeamRoleByTeamIds(id);
        teamTemplate.setTeamRoleList(teamRoleMap.get(id));
        return teamTemplate;
    }

    protected List<TeamTemplate> getDataFromDb(List<String> ids) {
        List<TeamTemplate> teamTemplateList = super.getDataFromDb(ids);
        Map<String, List<TeamRole>> teamRoleMap = teamRoleService.queryTeamRoleByTeamIds(ids.toArray(new String[]{}));
        teamTemplateList.forEach(teamTemplate -> {
            teamTemplate.setTeamRoleList(teamRoleMap.get(teamTemplate.getId()));
        });
        return teamTemplateList;
    }

    /**
     * 查询团队模板信息用于界面展示
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryTeamTemplateMation(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        TeamTemplate teamTemplate = super.selectById(id);
        teamRoleService.setUserRoleName(teamTemplate.getTeamRoleList());
        teamRoleUserService.setUserName(teamTemplate.getTeamRoleList());
        outputObject.setBean(teamTemplate);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

}
