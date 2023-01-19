/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.dsform.entity.DsFormComponent;
import com.skyeye.eve.dao.SkyeyeBaseMapper;

import java.util.List;
import java.util.Map;

public interface DsFormComponentDao extends SkyeyeBaseMapper<DsFormComponent> {

    List<Map<String, Object>> queryDsFormComponentList(CommonPageInfo commonPageInfo);

}
