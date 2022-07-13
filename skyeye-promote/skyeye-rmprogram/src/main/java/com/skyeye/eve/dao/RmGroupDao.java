/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface RmGroupDao {

    List<Map<String, Object>> queryRmGroupList(Map<String, Object> map);

    Map<String, Object> queryRmGroupByName(Map<String, Object> map);

    Map<String, Object> queryRmGroupISTop(Map<String, Object> map);

    int insertRmGroupMation(Map<String, Object> map);

    Map<String, Object> queryRmGroupMemberNumById(Map<String, Object> map);

    int deleteRmGroupById(Map<String, Object> map);

    Map<String, Object> queryRmGroupMationToEditById(Map<String, Object> map);

    Map<String, Object> queryRmGroupMationByIdAndName(Map<String, Object> map);

    int editRmGroupMationById(Map<String, Object> map);

    Map<String, Object> queryRmGroupISTopByThisId(Map<String, Object> map);

    int editRmGroupSortTopById(Map<String, Object> topBean);

    Map<String, Object> queryRmGroupISLowerByThisId(Map<String, Object> map);

    List<Map<String, Object>> queryRmGroupAllList(Map<String, Object> map);

}
