/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WagesSocialSecurityFundDao {

    List<Map<String, Object>> queryWagesSocialSecurityFundList(Map<String, Object> map);

    Map<String, Object> queryWagesSocialSecurityFundMationByTitleAndNotId(@Param("title") String title, @Param("notId") String notId);

    int insertWagesSocialSecurityFundMation(Map<String, Object> map);

    Map<String, Object> queryWagesSocialSecurityFundMationById(@Param("id") String id);

    List<Map<String, Object>> queryStaffNameListByStaffIdList(@Param("list") List<String> staffIds);

    List<Map<String, Object>> queryDepartMentNameListByDepartMentIdList(@Param("list") List<String> departMentIds);

    List<Map<String, Object>> queryCompanyNameListByCompanyIdList(@Param("list") List<String> companyIds);

    int editWagesSocialSecurityFundMationById(Map<String, Object> map);

    int editWagesSocialSecurityFundStateMationById(@Param("id") String id, @Param("state") int state);
}
