/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface SysEveWinDragDropDao {

    List<Map<String, Object>> queryMenuBoxNameInByName(Map<String, Object> map);

    Map<String, Object> queryWinCustomMenuBoxNumByUserId(Map<String, Object> map);

    int insertWinCustomMenuBox(Map<String, Object> map);

    List<Map<String, Object>> queryMenuNameInByName(Map<String, Object> map);

    int insertWinCustomMenu(Map<String, Object> map);

    Map<String, Object> queryMenuMationFromSysById(Map<String, Object> map);

    int deleteCustomMenuById(Map<String, Object> map);

    int deleteCustomBoxMenuById(Map<String, Object> map);

    List<Map<String, Object>> queryChildsMenuById(Map<String, Object> map);

    int deleteCustomMenuByIds(Map<String, Object> map);

    int deleteUserSysMenuByIds(List<Map<String, Object>> removeChild);

    int deleteSysBoxMenuById(Map<String, Object> map);

    int delMenuParentIdById(Map<String, Object> map);

    int insertMenuParentId(Map<String, Object> map);

    int deleteCustomMenuParentByIds(Map<String, Object> map);

    Map<String, Object> queryMenuMationTypeById(Map<String, Object> map);

    Map<String, Object> queryCustomMenuBoxMationEditById(Map<String, Object> map);

    int editCustomMenuBoxMationById(Map<String, Object> map);

    Map<String, Object> queryCustomMenuMationEditById(Map<String, Object> map);

    int editCustomMenuMationById(Map<String, Object> map);

    Map<String, Object> queryCustomMenuToDeskTopById(Map<String, Object> map);

    int editCustomMenuToDeskTopById(Map<String, Object> map);

    Map<String, Object> queryMenuToDeskTopById(Map<String, Object> map);

}
