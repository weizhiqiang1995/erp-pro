/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface RmGroupMemberDao {

    List<Map<String, Object>> queryRmGroupMemberList(Map<String, Object> map);

    Map<String, Object> queryRmGroupMemberISTop(Map<String, Object> map);

    int insertRmGroupMemberMation(Map<String, Object> map);

    Map<String, Object> queryRmGroupMemberISLowerByThisId(Map<String, Object> map);

    Map<String, Object> queryRmGroupMemberISTopByThisId(Map<String, Object> map);

    int editRmGroupMemberSortTopById(Map<String, Object> map);

    Map<String, Object> queryUseRmGroupMemberNumById(Map<String, Object> map);

    int deleteRmGroupMemberById(Map<String, Object> map);

    Map<String, Object> queryRmGroupMemberMationToEditById(Map<String, Object> map);

    int editRmGroupMemberMationById(Map<String, Object> map);

    Map<String, Object> queryRmGroupMemberMationById(Map<String, Object> map);

    int deleteRmGroupMemberAndPropertyMationById(Map<String, Object> map);

    int insertRmGroupMemberAndPropertyMationById(List<Map<String, Object>> beans);

    List<Map<String, Object>> queryRmGroupMemberAndPropertyMationById(Map<String, Object> map);

}
