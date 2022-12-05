/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.team.dao.TeamBusinessDao;
import com.skyeye.team.entity.TeamBusiness;
import com.skyeye.team.entity.TeamTemplate;
import com.skyeye.team.service.TeamBusinessService;
import com.skyeye.team.service.TeamTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: TeamBusinessServiceImpl
 * @Description: 团队管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:37
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamBusinessServiceImpl extends AbstractTeamServiceImpl<TeamBusinessDao, TeamBusiness> implements TeamBusinessService {

    @Autowired
    private TeamTemplateService teamTemplateService;

    /**
     * 根据团队模板生成团队信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void createTeamBusiness(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String teamTemplateId = params.get("teamTemplateId").toString();
        String businessId = params.get("businessId").toString();
        String businessKey = params.get("businessKey").toString();
        TeamTemplate teamTemplate = teamTemplateService.selectById(teamTemplateId);
        if (teamTemplate == null) {
            throw new CustomException("该团队模板不存在.");
        }
        TeamBusiness teamBusiness = Convert.convert(TeamBusiness.class, teamTemplate);
        teamBusiness.setTeamTemplateId(teamTemplateId);
        teamBusiness.setBusinessId(businessId);
        teamBusiness.setBusinessKey(businessKey);
        String userId = inputObject.getLogParams().get("id").toString();
        createEntity(teamBusiness, userId);
        // 设置该模板为启用中
        teamTemplateService.setUsed(teamTemplateId);
    }

    /**
     * 根据业务对象id获取团队信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryTeamBusiness(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String businessId = params.get("businessId").toString();
        String businessKey = params.get("businessKey").toString();
        TeamBusiness teamBusiness = selectById(businessId);
        if (teamBusiness == null) {
            QueryWrapper<TeamBusiness> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(TeamBusiness::getBusinessId), businessId);
            queryWrapper.eq(MybatisPlusUtil.toColumns(TeamBusiness::getBusinessKey), businessKey);
            TeamBusiness tmp = getOne(queryWrapper);
            if (tmp == null) {
                throw new CustomException("该团队不存在.");
            }
            teamBusiness = selectById(tmp.getId());
        }
        setOtherName(teamBusiness);
        // 设置名称
        TeamTemplate teamTemplate = teamTemplateService.selectById(teamBusiness.getTeamTemplateId());
        teamBusiness.setName(teamTemplate.getName());

        outputObject.setBean(teamBusiness);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据业务对象id删除团队信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteTeamBusiness(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String businessId = params.get("businessId").toString();
        String businessKey = params.get("businessKey").toString();
        TeamBusiness teamBusiness = selectById(businessId);
        if (teamBusiness == null) {
            QueryWrapper<TeamBusiness> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(TeamBusiness::getBusinessId), businessId);
            queryWrapper.eq(MybatisPlusUtil.toColumns(TeamBusiness::getBusinessKey), businessKey);
            TeamBusiness tmp = getOne(queryWrapper);
            if (tmp != null) {
                deleteById(tmp.getId());
            }
        }
    }
}

