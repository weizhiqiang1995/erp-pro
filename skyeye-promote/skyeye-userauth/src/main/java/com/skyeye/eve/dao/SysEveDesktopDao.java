/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEveDesktopDao {

    List<Map<String, Object>> querySysDesktopList(Map<String, Object> map) throws Exception;

    int insertSysDesktopMation(Map<String, Object> map) throws Exception;

    Map<String, Object> querySysDesktopBySimpleLevel(Map<String, Object> map) throws Exception;

    int editSysDesktopStateById(@Param("id") String id, @Param("state") Integer state) throws Exception;

    Map<String, Object> selectSysDesktopById(Map<String, Object> map) throws Exception;

    int editSysDesktopMationById(Map<String, Object> map) throws Exception;

    Map<String, Object> querySysDesktopUpMationById(Map<String, Object> map) throws Exception;

    int editSysDesktopMationOrderNumUpById(Map<String, Object> map) throws Exception;

    Map<String, Object> querySysDesktopDownMationById(Map<String, Object> map) throws Exception;

    Map<String, Object> querySysDesktopStateById(Map<String, Object> map) throws Exception;

    Map<String, Object> checkSysDesktopMation(Map<String, Object> map) throws Exception;

    List<Map<String, Object>> queryAllSysDesktopList(Map<String, Object> map) throws Exception;

    int removeAllSysEveMenuByDesktopId(Map<String, Object> map) throws Exception;

    Map<String, Object> querySysDesktopStateAndMenuNumById(Map<String, Object> map) throws Exception;

}
