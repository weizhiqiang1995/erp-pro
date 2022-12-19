/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.annotation.coderule.CodeRuleService;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.coderule.service.ICodeRuleService;
import com.skyeye.team.dao.TeamTemplateDao;
import com.skyeye.team.entity.TeamTemplate;
import com.skyeye.team.service.TeamTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeamTemplateServiceImpl
 * @Description: 团队模板管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:24
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@CodeRuleService(value = "团队模板", groupName = "团队管理")
@SkyeyeService(name = "团队模板管理", groupName = "团队管理")
public class TeamTemplateServiceImpl extends AbstractTeamServiceImpl<TeamTemplateDao, TeamTemplate> implements TeamTemplateService {

    @Autowired
    private TeamTemplateDao teamTemplateDao;

    @Autowired
    private ICodeRuleService iCodeRuleService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = teamTemplateDao.queryList(commonPageInfo);
        iAuthUserService.setNameForMap(beans, "chargeUser", "chargeUserName");
        return beans;
    }

    @Override
    public void createPrepose(TeamTemplate entity) {
        String code = iCodeRuleService.getNextCodeByClassName(this.getServiceClassName(), BeanUtil.beanToMap(entity));
        entity.setCode(code);
    }

    /**
     * 设置为使用中
     *
     * @param id
     */
    @Override
    public void setUsed(String id) {
        TeamTemplate teamTemplate = super.selectById(id);
        if (teamTemplate.getIsUsed() == null || teamTemplate.getIsUsed() == 0) {
            UpdateWrapper<TeamTemplate> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(CommonConstants.ID, id);
            TeamTemplate template = new TeamTemplate();
            template.setIsUsed(1);
            String userId = InputObject.getLogParamsStatic().get(CommonConstants.ID).toString();
            DataCommonUtil.setCommonLastUpdateDataByGenericity(template, userId);
            skyeyeBaseMapper.update(template, updateWrapper);

            refreshCache(id);
        }
    }

    /**
     * 查询已启用的团队模板列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryEnableTeamTemplateList(InputObject inputObject, OutputObject outputObject) {
        String objectType = inputObject.getParams().get("objectType").toString();
        QueryWrapper<TeamTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TeamTemplate::getObjectType), objectType);
        queryWrapper.eq(MybatisPlusUtil.toColumns(TeamTemplate::getEnabled), EnableEnum.ENABLE_USING.getKey());
        List<TeamTemplate> teamTemplateList = list(queryWrapper);
        outputObject.setBeans(teamTemplateList);
        outputObject.settotal(teamTemplateList.size());
    }

}
