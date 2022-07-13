/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WagesModelDao {

    List<Map<String, Object>> queryWagesModelList(Map<String, Object> map);

    int editWagesModelMationStateMationById(@Param("id") String id, @Param("state") int state);

    Map<String, Object> queryWagesModelMationByTitleAndNotId(@Param("title") String title, @Param("notId") String notId);

    int insertWagesModelMation(Map<String, Object> map);

    Map<String, Object> queryWagesModelMationById(@Param("id") String id);

    List<Map<String, Object>> queryStaffNameListByStaffIdList(@Param("list") List<String> staffIds);

    List<Map<String, Object>> queryDepartMentNameListByDepartMentIdList(@Param("list") List<String> departMentIds);

    List<Map<String, Object>> queryCompanyNameListByCompanyIdList(@Param("list") List<String> companyIds);

    int editWagesModelMationById(Map<String, Object> map);

    List<Map<String, Object>> queryWagesModelListByApplicableObjectIds(@Param("list") List<String> wagesApplicableObjectIds);
}
