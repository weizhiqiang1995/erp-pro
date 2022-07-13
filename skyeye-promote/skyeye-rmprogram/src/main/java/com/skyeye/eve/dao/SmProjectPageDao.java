/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface SmProjectPageDao {

    List<Map<String, Object>> queryProPageMationByProIdList(Map<String, Object> map);

    Map<String, Object> queryFilePathNumMaxMation(Map<String, Object> map);

    Map<String, Object> queryFileNameNumMaxMation(Map<String, Object> map);

    Map<String, Object> querySortMaxMationByProjectId(Map<String, Object> map);

    int insertProPageMationByProId(Map<String, Object> map);

    Map<String, Object> querySmProjectPageISTopByThisId(Map<String, Object> map);

    int editSmProjectPageSortTopById(Map<String, Object> map);

    Map<String, Object> querySmProjectPageISLowerByThisId(Map<String, Object> map);

    int editSmProjectPageSortLowerById(Map<String, Object> topBean);

    Map<String, Object> querySmProjectPageMationToEditById(Map<String, Object> map);

    int editSmProjectPageMationById(Map<String, Object> map);

    int deleteSmProjectPageMationById(Map<String, Object> map);

    int deleteSmProjectPageModeMationById(Map<String, Object> map);

}
