/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.team.entity.TeamTemplate;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TeamTemplateDao
 * @Description: 团队模板管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 19:23
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface TeamTemplateDao extends SkyeyeBaseMapper<TeamTemplate> {

    List<Map<String, Object>> queryList(CommonPageInfo commonPageInfo);

}
