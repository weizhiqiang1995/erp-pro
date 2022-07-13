/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEveDesktopDao {

    List<Map<String, Object>> querySysDesktopList(Map<String, Object> map);

    int insertSysDesktopMation(Map<String, Object> map);

    Map<String, Object> querySysDesktopBySimpleLevel(Map<String, Object> map);

    int editSysDesktopStateById(@Param("id") String id, @Param("state") Integer state);

    Map<String, Object> selectSysDesktopById(Map<String, Object> map);

    int editSysDesktopMationById(Map<String, Object> map);

    Map<String, Object> querySysDesktopUpMationById(Map<String, Object> map);

    int editSysDesktopMationOrderNumUpById(Map<String, Object> map);

    Map<String, Object> querySysDesktopDownMationById(Map<String, Object> map);

    Map<String, Object> querySysDesktopStateById(Map<String, Object> map);

    Map<String, Object> checkSysDesktopMation(Map<String, Object> map);

    List<Map<String, Object>> queryAllSysDesktopList(Map<String, Object> map);

    int removeAllSysEveMenuByDesktopId(Map<String, Object> map);

    Map<String, Object> querySysDesktopStateAndMenuNumById(Map<String, Object> map);

}
