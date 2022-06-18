/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface PlanProjectDao {

	List<Map<String, Object>> queryPlanProjectList(Map<String, Object> map) throws Exception;

	int insertPlanProjectMation(Map<String, Object> map) throws Exception;

	Map<String, Object> judgePlanProjectName(Map<String, Object> map) throws Exception;

	int deletePlanProjectMationById(Map<String, Object> map) throws Exception;

	Map<String, Object> queryPlanProjectMationToEditById(Map<String, Object> map) throws Exception;

	int editPlanProjectMationById(Map<String, Object> map) throws Exception;

}
