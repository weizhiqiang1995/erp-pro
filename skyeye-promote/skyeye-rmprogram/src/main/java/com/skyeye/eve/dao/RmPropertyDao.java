/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface RmPropertyDao {

    List<Map<String, Object>> queryRmPropertyList(Map<String, Object> map);

    int insertRmPropertyMation(Map<String, Object> map);

    Map<String, Object> queryRmPropertyMationByName(Map<String, Object> map);

    int deleteRmPropertyMationById(Map<String, Object> map);

    Map<String, Object> queryRmPropertyMationToEditById(Map<String, Object> map);

    Map<String, Object> queryRmPropertyMationByNameAndId(Map<String, Object> map);

    int editRmPropertyMationById(Map<String, Object> map);

    List<Map<String, Object>> queryRmPropertyListToShow(Map<String, Object> map);

    Map<String, Object> queryRmPropertyValueNumById(Map<String, Object> map);

    Map<String, Object> queryUseRmPropertyNumById(Map<String, Object> map);

}
