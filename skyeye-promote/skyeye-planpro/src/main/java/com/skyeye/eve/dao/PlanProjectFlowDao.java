/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface PlanProjectFlowDao {

    List<Map<String, Object>> queryPlanProjectFlowList(Map<String, Object> map);

    int insertPlanProjectFlowMation(Map<String, Object> map);

    Map<String, Object> queryPlanProjectFlowMationByName(Map<String, Object> map);

    int deletePlanProjectFlowMationById(Map<String, Object> map);

    Map<String, Object> queryPlanProjectFlowMationToEditById(Map<String, Object> map);

    Map<String, Object> queryPlanProjectFlowMationByNameAndId(Map<String, Object> map);

    int editPlanProjectFlowMationById(Map<String, Object> map);

    Map<String, Object> queryChildNumMationById(Map<String, Object> map);

    Map<String, Object> queryPlanProjectFlowJsonContentMationById(Map<String, Object> map);

    int editPlanProjectFlowJsonContentMationById(Map<String, Object> map);

}
