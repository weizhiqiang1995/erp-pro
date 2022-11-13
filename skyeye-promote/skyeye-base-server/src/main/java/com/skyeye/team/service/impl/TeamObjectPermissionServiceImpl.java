/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service.impl;

import com.skyeye.team.dao.TeamObjectPermissionDao;
import com.skyeye.team.service.TeamObjectPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: TeamObjectPermissionServiceImpl
 * @Description: 业务对象权限管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:40
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TeamObjectPermissionServiceImpl implements TeamObjectPermissionService {

    @Autowired
    private TeamObjectPermissionDao teamObjectPermissionDao;

}
