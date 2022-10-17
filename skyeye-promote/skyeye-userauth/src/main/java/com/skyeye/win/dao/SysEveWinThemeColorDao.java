/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.dao;

import java.util.List;
import java.util.Map;

public interface SysEveWinThemeColorDao {

    List<Map<String, Object>> querySysEveWinThemeColorList(Map<String, Object> map);

    int insertSysEveWinThemeColorMation(Map<String, Object> map);

    Map<String, Object> querySysEveWinThemeColorMationByName(Map<String, Object> map);

    int deleteSysEveWinThemeColorMationById(Map<String, Object> map);

    Map<String, Object> querySysEveWinThemeColorMationToEditById(Map<String, Object> map);

    Map<String, Object> querySysEveWinThemeColorMationByNameAndId(Map<String, Object> map);

    int editSysEveWinThemeColorMationById(Map<String, Object> map);

    List<Map<String, Object>> querySysEveWinThemeColorListToShow(Map<String, Object> map);

}
