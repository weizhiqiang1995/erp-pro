/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.dao;

import com.skyeye.catalog.entity.CatalogBusinessQueryDo;
import com.skyeye.enclosure.entity.Enclosure;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEnclosureDao extends SkyeyeBaseMapper<Enclosure> {

    List<Map<String, Object>> queryEnclosureList(CatalogBusinessQueryDo commonPageInfo);

    List<Map<String, Object>> queryEnclosureInfo(@Param("enclosure") String enclosure);

    List<Map<String, Object>> queryEnclosureTree(@Param("userId") String userId);
}
