/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.team.entity.TeamObjectPermission;

import java.util.List;

/**
 * @ClassName: TeamObjectPermissionService
 * @Description: 业务对象权限管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:39
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TeamObjectPermissionService extends SkyeyeBusinessService<TeamObjectPermission> {

    List<TeamObjectPermission> queryPermissionByTeamId(String teamId, List<String> ownerIds, String ownerKey);

}
