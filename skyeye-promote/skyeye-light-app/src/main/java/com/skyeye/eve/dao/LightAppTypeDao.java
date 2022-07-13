/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface LightAppTypeDao {

    List<Map<String, Object>> queryLightAppTypeList(Map<String, Object> map);

    Map<String, Object> queryLightAppTypeMationByTypeName(Map<String, Object> map);

    Map<String, Object> queryLightAppTypeMationByTypeNameAndId(Map<String, Object> map);

    int insertLightAppTypeMation(Map<String, Object> map);

    Map<String, Object> queryLightAppTypeAfterOrderBum(Map<String, Object> map);

    Map<String, Object> queryLightAppTypeMationToEditById(Map<String, Object> map);

    Map<String, Object> queryLightAppTypeMationStateById(Map<String, Object> map);

    int editLightAppTypeMationById(Map<String, Object> map);

    Map<String, Object> queryLightAppTypeISTopByThisId(Map<String, Object> map);

    int editLightAppTypeSortTopById(Map<String, Object> map);

    Map<String, Object> queryLightAppTypeISLowerByThisId(Map<String, Object> map);

    int editLightAppTypeSortLowerById(Map<String, Object> map);

    int deleteLightAppTypeById(Map<String, Object> map);

    int editLightAppTypeUpTypeById(Map<String, Object> map);

    int editLightAppTypeDownTypeById(Map<String, Object> map);

    List<Map<String, Object>> queryLightAppTypeUpList(Map<String, Object> map);

}
