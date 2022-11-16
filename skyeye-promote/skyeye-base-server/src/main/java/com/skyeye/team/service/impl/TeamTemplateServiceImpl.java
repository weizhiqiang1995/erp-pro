/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.entity.team.TeamObjectPermission;
import com.skyeye.eve.entity.team.TeamRole;
import com.skyeye.eve.entity.team.TeamRoleUser;
import com.skyeye.eve.entity.team.TeamTemplate;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.team.classenum.ObjectPermissionFromType;
import com.skyeye.team.dao.TeamTemplateDao;
import com.skyeye.team.service.TeamObjectPermissionService;
import com.skyeye.team.service.TeamRoleService;
import com.skyeye.team.service.TeamRoleUserService;
import com.skyeye.team.service.TeamTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Autowired
    private TeamObjectPermissionService teamObjectPermissionService;

    @Autowired
    private IAuthUserService iAuthUserService;

    @Override
    protected List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = teamTemplateDao.queryList(commonPageInfo);
        iAuthUserService.setNameByIdList(beans, "createId", "createName");
        iAuthUserService.setNameByIdList(beans, "lastUpdateId", "lastUpdateName");
        return beans;
    }

    @Override
    public void createPostpose(TeamTemplate entity, String userId) {
        String teamTemplateId = entity.getId();
        List<TeamRole> teamRoleList = entity.getTeamRoleList();
        String serviceClassName = getServiceClassName();
        saveNewTeamRole(teamTemplateId, teamRoleList, userId, serviceClassName);

        if (CollectionUtil.isNotEmpty(entity.getTeamObjectPermissionList())) {
            saveTeamOwnerPermission(teamTemplateId, serviceClassName, userId, entity.getTeamObjectPermissionList());
        }
    }

    @Override
    public void updatePostpose(TeamTemplate entity, String userId) {
        String teamTemplateId = entity.getId();
        List<TeamRole> newTeamRoleList = entity.getTeamRoleList();
        if (CollectionUtil.isNotEmpty(newTeamRoleList)) {
            String serviceClassName = getServiceClassName();
            TeamTemplate oldTeamTemplate = selectById(teamTemplateId);
            List<TeamRole> oldTeamRoleList = oldTeamTemplate.getTeamRoleList();
            List<String> oldRoleKeys = oldTeamRoleList.stream().map(bean -> bean.getRoleId()).collect(Collectors.toList());
            // 修改角色信息
            updateRole(userId, teamTemplateId, newTeamRoleList, serviceClassName, oldTeamRoleList, oldRoleKeys);

            // 修改团队用户信息
            updateRoleUser(userId, teamTemplateId, newTeamRoleList, serviceClassName, oldRoleKeys);

            // 修改权限信息
            updatePermission(entity.getTeamObjectPermissionList(), oldTeamTemplate.getTeamObjectPermissionList(), teamTemplateId, serviceClassName, userId);
        } else {
            // 如果团队角色集合为空,则删除旧的团队角色和用户数据
            // 1. 删除团队模板与角色的关系
            teamRoleService.deleteRoleByTeamIds(teamTemplateId);
            // 2. 删除角色下的用户
            teamRoleUserService.deleteRoleUserByTeamIds(teamTemplateId);
        }
    }

    private void updateRole(String userId, String teamTemplateId, List<TeamRole> newTeamRoleList, String serviceClassName,
                            List<TeamRole> oldTeamRoleList, List<String> oldRoleKeys) {
        List<String> newRoleKeys = newTeamRoleList.stream().map(bean -> bean.getRoleId()).collect(Collectors.toList());
        // (新数据 - 旧数据) 添加到数据库
        List<TeamRole> addTeamRoleMaps = newTeamRoleList.stream()
            .filter(item -> !oldRoleKeys.contains(item.getRoleId())).collect(Collectors.toList());
        // 新增
        saveNewTeamRole(teamTemplateId, addTeamRoleMaps, userId, serviceClassName);
        // (旧数据 - 新数据) 从数据库删除
        List<TeamRole> deleteTeamRole = oldTeamRoleList.stream()
            .filter(item -> !newRoleKeys.contains(item.getRoleId())).collect(Collectors.toList());
        List<String> removeRoleIds = deleteTeamRole.stream().map(teamRole -> teamRole.getRoleId()).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(removeRoleIds)) {
            teamRoleService.deleteRoleByRoleIds(removeRoleIds.toArray(new String[]{}));
            teamRoleUserService.deleteRoleUserByRoleIds(teamTemplateId, removeRoleIds.toArray(new String[]{}));
        }
    }

    private void updateRoleUser(String userId, String teamTemplateId, List<TeamRole> newTeamRoleList, String serviceClassName, List<String> oldRoleKeys) {
        // 新数据和旧数据交集(团队角色)需要编辑的数据
        List<TeamRole> oldTeamRole = newTeamRoleList.stream()
            .filter(item -> oldRoleKeys.contains(item.getRoleId())).collect(Collectors.toList());

        // 新数据 - 旧数据 更新到数据库
        List<TeamRoleUser> newRoleUser = getTeamRoleUserList(oldTeamRole);
        List<String> newRoleUserKeys = newRoleUser.stream().map(bean -> bean.getTeamId() + bean.getUserId()).collect(Collectors.toList());
        // 数据库里面的该团队模板下的用户信息
        List<TeamRoleUser> oldRoleUser = getTeamRoleUserList(oldTeamRole);
        List<String> oldRoleUserKeys = oldRoleUser.stream().map(bean -> bean.getTeamId() + bean.getUserId()).collect(Collectors.toList());
        // 需要新增的用户信息
        List<TeamRoleUser> addTeamRoleUser = newRoleUser.stream()
            .filter(item -> !oldRoleUserKeys.contains(item.getTeamId() + item.getUserId())).collect(Collectors.toList());
        addTeamRoleUser.forEach(p -> {
            p.setId(null);
            p.setTeamId(teamTemplateId);
            p.setTeamKey(serviceClassName);
        });
        if (CollectionUtil.isNotEmpty(addTeamRoleUser)) {
            teamRoleUserService.createEntity(addTeamRoleUser, userId);
        }
        // 删除用户关联信息
        List<TeamRoleUser> deleteRoleUser = oldRoleUser.stream()
            .filter(predicate -> !newRoleUserKeys.contains(predicate.getTeamId() + predicate.getUserId())).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(deleteRoleUser)) {
            List<String> deleteRoleUserLinkIds = deleteRoleUser.stream().map(TeamRoleUser::getId).collect(Collectors.toList());
            teamRoleUserService.deleteById(deleteRoleUserLinkIds);
        }
    }

    private void updatePermission(List<TeamObjectPermission> newPermissionList, List<TeamObjectPermission> oldPermissionList, String teamTemplateId,
                                  String serviceClassName, String userId) {
        List<String> newPermissionKeys = newPermissionList.stream().map(bean -> getPermissionKey(bean)).collect(Collectors.toList());
        List<String> oldPermissionKeys = oldPermissionList.stream().map(bean -> getPermissionKey(bean)).collect(Collectors.toList());
        // 需要新增的
        List<TeamObjectPermission> addPermission = newPermissionList.stream()
            .filter(item -> !oldPermissionKeys.contains(getPermissionKey(item))).collect(Collectors.toList());
        saveTeamOwnerPermission(teamTemplateId, serviceClassName, userId, addPermission);

        // (旧数据 - 新数据) 从数据库删除
        List<TeamObjectPermission> deletePermission = oldPermissionList.stream()
            .filter(item -> !newPermissionKeys.contains(getPermissionKey(item))).collect(Collectors.toList());
        List<String> removePermissionIds = deletePermission.stream().map(TeamObjectPermission::getId).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(removePermissionIds)) {
            teamObjectPermissionService.deleteById(removePermissionIds);
        }
    }

    private void saveTeamOwnerPermission(String teamTemplateId, String serviceClassName, String userId, List<TeamObjectPermission> addPermission) {
        addPermission.forEach(bean -> {
            bean.setTeamId(teamTemplateId);
            bean.setTeamKey(serviceClassName);
            bean.setFromType(ObjectPermissionFromType.TEAM_LINK.getKey());
        });
        teamObjectPermissionService.createEntity(addPermission, userId);
    }

    private String getPermissionKey(TeamObjectPermission permission) {
        return String.format(Locale.ROOT, "%s_%s_%s_%s", permission.getPermissionValue(), permission.getPermissionKey(),
            permission.getOwnerId(), permission.getOwnerKey());
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
            .flatMap(teamRole -> {
                teamRole.getTeamRoleUserList().forEach(teamRoleUser -> {
                    teamRoleUser.setRoleId(teamRole.getRoleId());
                });
                return teamRole.getTeamRoleUserList().stream();
            })
            .filter(Objects::nonNull).collect(Collectors.toList());
    }

    private void saveRole(String userId, String teamTemplateId, List<TeamRole> teamRoleList, String serviceClassName) {
        teamRoleList.forEach(teamRole -> {
            teamRole.setTeamId(teamTemplateId);
            teamRole.setTeamKey(serviceClassName);
        });
        teamRoleService.createEntity(teamRoleList, userId);
    }

    @Override
    protected TeamTemplate getDataFromDb(String id) {
        TeamTemplate teamTemplate = super.getDataFromDb(id);
        Map<String, List<TeamRole>> teamRoleMap = teamRoleService.queryTeamRoleByTeamIds(id);
        teamTemplate.setTeamRoleList(teamRoleMap.get(id));
        List<TeamObjectPermission> teamObjectPermissionList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(teamTemplate.getTeamRoleList())) {
            // 查询角色权限
            List<String> roleIds = teamTemplate.getTeamRoleList().stream().map(TeamRole::getRoleId).collect(Collectors.toList());
            List<TeamObjectPermission> rolePermissionList = teamObjectPermissionService.queryPermissionByTeamId(id, roleIds, teamRoleService.getServiceClassName());
            teamObjectPermissionList.addAll(rolePermissionList);
            // 查询用户权限
            List<String> userIds = teamTemplate.getTeamRoleList().stream()
                .filter(teamRole -> CollectionUtil.isNotEmpty(teamRole.getTeamRoleUserList()))
                .flatMap(teamRole -> teamRole.getTeamRoleUserList().stream())
                .filter(bean -> !ToolUtil.isBlank(bean.getUserId()))
                .map(bean -> bean.getUserId()).collect(Collectors.toList());
            List<TeamObjectPermission> userPermissionList = teamObjectPermissionService.queryPermissionByTeamId(id, userIds, teamRoleUserService.getServiceClassName());
            teamObjectPermissionList.addAll(userPermissionList);
        }
        teamTemplate.setTeamObjectPermissionList(teamObjectPermissionList);
        return teamTemplate;
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

    @Override
    public void deletePostpose(String id) {
        // 删除团队下的角色信息
        teamRoleService.deleteRoleByTeamIds(id);
        // 删除团队下的用户信息
        teamRoleUserService.deleteRoleUserByTeamIds(id);
    }

    /**
     * 设置为使用中
     *
     * @param id
     */
    @Override
    public void setUsed(String id) {
        TeamTemplate teamTemplate = super.selectById(id);
        if (teamTemplate.getIsUsed() == 0) {
            UpdateWrapper<TeamTemplate> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(CommonConstants.ID, id);
            TeamTemplate template = new TeamTemplate();
            template.setIsUsed(1);
            skyeyeBaseMapper.update(template, updateWrapper);
        }
    }

}
