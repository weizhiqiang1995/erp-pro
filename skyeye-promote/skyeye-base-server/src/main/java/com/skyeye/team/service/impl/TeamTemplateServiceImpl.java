/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import com.skyeye.team.dao.TeamTemplateDao;
import com.skyeye.team.service.TeamTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TeamTemplateServiceImpl
 * @Description: 团队模板管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:24
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamTemplateServiceImpl implements TeamTemplateService {

    @Autowired
    private TeamTemplateDao teamTemplateDao;

}
