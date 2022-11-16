/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.entity.team.TeamObjectPermission;
import com.skyeye.team.dao.TeamObjectPermissionDao;
import com.skyeye.team.service.TeamObjectPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private TeamObjectPermissionDao teamObjectPermissionDao;

    @Override
    public List<TeamObjectPermission> queryPermissionByTeamId(String teamId, List<String> ownerIds, String ownerKey) {
        if (ToolUtil.isBlank(teamId) || CollectionUtil.isEmpty(ownerIds) || ToolUtil.isBlank(ownerKey)) {
            return new ArrayList<>();
        }
        QueryWrapper<TeamObjectPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TeamObjectPermission::getTeamId), teamId);
        queryWrapper.in(MybatisPlusUtil.toColumns(TeamObjectPermission::getOwnerId), ownerIds);
        queryWrapper.eq(MybatisPlusUtil.toColumns(TeamObjectPermission::getOwnerKey), ownerKey);
        queryWrapper.eq(MybatisPlusUtil.toColumns(TeamObjectPermission::getFromType), 1);
        List<TeamObjectPermission> teamObjectPermissionList = super.list(queryWrapper);
        return teamObjectPermissionList;
    }
}
