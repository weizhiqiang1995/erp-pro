/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.eve.entity.team.TeamBusiness;
import com.skyeye.team.dao.TeamBusinessDao;
import com.skyeye.team.service.TeamBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TeamBusinessServiceImpl
 * @Description: 团队管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:37
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamBusinessServiceImpl extends SkyeyeBusinessServiceImpl<TeamBusinessDao, TeamBusiness> implements TeamBusinessService {

    @Autowired
    private TeamBusinessDao teamBusinessDao;

}

