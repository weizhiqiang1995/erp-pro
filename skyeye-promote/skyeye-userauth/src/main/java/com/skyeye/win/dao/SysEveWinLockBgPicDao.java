/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.dao;

import java.util.List;
import java.util.Map;

public interface SysEveWinLockBgPicDao {

    List<Map<String, Object>> querySysEveWinLockBgPicList(Map<String, Object> map);

    int insertSysEveWinLockBgPicMation(Map<String, Object> map);

    int deleteSysEveWinLockBgPicMationById(Map<String, Object> map);

    Map<String, Object> querySysEveMationById(Map<String, Object> map);

    List<Map<String, Object>> querySysEveWinBgPicListToShow(Map<String, Object> map);

    int insertSysEveWinBgPicMationByCustom(Map<String, Object> map);

    List<Map<String, Object>> querySysEveWinBgPicCustomList(Map<String, Object> map);

    int deleteSysEveWinBgPicMationCustomById(Map<String, Object> map);

}
