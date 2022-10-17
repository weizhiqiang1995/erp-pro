/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.icon.dao;

import java.util.List;
import java.util.Map;

public interface SysEveIconDao {

    List<Map<String, Object>> querySysIconList(Map<String, Object> map);

    int insertSysIconMation(Map<String, Object> map);

    Map<String, Object> checkSysIconMation(Map<String, Object> map);

    int deleteSysIconMationById(Map<String, Object> map);

    Map<String, Object> querySysIconMationToEditById(Map<String, Object> map);

    int editSysIconMationById(Map<String, Object> map);

    List<Map<String, Object>> querySysIconListToMenu(Map<String, Object> map);

}
