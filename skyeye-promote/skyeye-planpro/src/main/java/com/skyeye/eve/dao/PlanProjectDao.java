/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface PlanProjectDao {

    List<Map<String, Object>> queryPlanProjectList(Map<String, Object> map);

    int insertPlanProjectMation(Map<String, Object> map);

    Map<String, Object> judgePlanProjectName(Map<String, Object> map);

    int deletePlanProjectMationById(Map<String, Object> map);

    Map<String, Object> queryPlanProjectMationToEditById(Map<String, Object> map);

    int editPlanProjectMationById(Map<String, Object> map);

}
