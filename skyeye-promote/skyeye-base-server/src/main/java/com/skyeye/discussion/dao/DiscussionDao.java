/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.discussion.entity.Discussion;
import com.skyeye.eve.dao.SkyeyeBaseMapper;

import java.util.List;
import java.util.Map;

public interface DiscussionDao extends SkyeyeBaseMapper<Discussion> {

    List<Map<String, Object>> queryDiscussionList(CommonPageInfo pageInfo);

}
