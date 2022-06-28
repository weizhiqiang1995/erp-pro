/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WagesModelApplicableObjectsDao {

    public int insertWagesModelApplicableObjects(@Param("list") List<Map<String, Object>> beans);

    public int deleteWagesModelApplicableObjectsByModelId(@Param("modelId") String modelId);

    public List<Map<String, Object>> queryWagesModelApplicableObjectsByModelId(@Param("modelId") String modelId);

    public List<Map<String, Object>> queryAllEanbleWagesModelApplicableObjects(@Param("lastMonthDate") String lastMonthDate);

}
