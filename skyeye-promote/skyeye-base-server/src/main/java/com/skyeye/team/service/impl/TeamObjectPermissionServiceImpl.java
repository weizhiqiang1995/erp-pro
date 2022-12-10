/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.team.dao.TeamObjectPermissionDao;
import com.skyeye.team.entity.TeamObjectPermission;
import com.skyeye.team.entity.TeamRoleUser;
import com.skyeye.team.service.TeamObjectPermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: TeamObjectPermissionServiceImpl
 * @Description: 业务对象权限管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:40
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamObjectPermissionServiceImpl extends SkyeyeBusinessServiceImpl<TeamObjectPermissionDao, TeamObjectPermission> implements TeamObjectPermissionService {

    @Override
    public List<TeamObjectPermission> queryPermissionByTeamId(String teamId, List<String> ownerIds) {
        if (ToolUtil.isBlank(teamId) || CollectionUtil.isEmpty(ownerIds)) {
            return new ArrayList<>();
        }
        QueryWrapper<TeamObjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TeamObjectPermission::getTeamId), teamId);
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamObjectPermission::getOwnerId), ownerIds);
        List<TeamObjectPermission> teamObjectPermissionList = super.list(queryWrapper);
        return teamObjectPermissionList;
    }

    /**
     * 删除团队下的权限信息
     *
     * @param teamIds
     */
    @Override
    public void deletePermissionByTeamIds(String... teamIds) {
        List<String> teamIdList = Arrays.asList(teamIds);
        if (CollectionUtil.isEmpty(teamIdList)) {
            return;
        }
        QueryWrapper<TeamObjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamObjectPermission::getTeamId), teamIdList);
        remove(queryWrapper);
    }

}
