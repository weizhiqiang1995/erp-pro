/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.dao;

import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.userauth.desktop.SysEveDesktopMation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEveDesktopDao extends SkyeyeBaseMapper<SysEveDesktopMation> {

    List<Map<String, Object>> querySysDesktopList(CommonPageInfo commonPageInfo);

    int editSysDesktopStateById(@Param("id") String id, @Param("state") Integer state);

    Map<String, Object> querySysDesktopUpMationById(Map<String, Object> map);

    int editSysDesktopMationOrderNumUpById(Map<String, Object> map);

    Map<String, Object> querySysDesktopDownMationById(Map<String, Object> map);

    List<Map<String, Object>> queryAllSysDesktopList(Map<String, Object> map);

    int removeAllSysEveMenuByDesktopId(Map<String, Object> map);

    Map<String, Object> querySysDesktopStateAndMenuNumById(@Param("id") String id);

    Integer querySysEveDesktopMaxOrderBum();
}
