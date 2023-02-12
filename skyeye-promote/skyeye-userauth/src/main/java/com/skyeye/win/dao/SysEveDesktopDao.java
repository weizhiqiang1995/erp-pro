/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.win.entity.SysDesktop;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEveDesktopDao extends SkyeyeBaseMapper<SysDesktop> {

    List<Map<String, Object>> querySysDesktopList(CommonPageInfo commonPageInfo);

    Map<String, Object> querySysDesktopUpMationById(Map<String, Object> map);

    int editSysDesktopMationOrderNumUpById(Map<String, Object> map);

    Map<String, Object> querySysDesktopDownMationById(Map<String, Object> map);

    int removeAllSysEveMenuByDesktopId(Map<String, Object> map);

    Map<String, Object> querySysDesktopStateAndMenuNumById(@Param("id") String id);

    Integer querySysEveDesktopMaxOrderBum();
}
