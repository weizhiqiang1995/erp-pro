/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.organization.company.Company;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CompanyMationDao extends SkyeyeBaseMapper<Company> {

    List<Map<String, Object>> queryCompanyMationList(Map<String, Object> map);

    List<Map<String, Object>> queryOverAllCompanyMationList(Map<String, Object> map);

    Map<String, Object> queryCompanyMationById(@Param("id") String id);

    List<Map<String, Object>> queryCompanyMationListTree(Map<String, Object> map);

    Map<String, Object> queryCompanyUserNumMationById(@Param("id") String id);

    Map<String, Object> queryCompanyDepartMentNumMationById(@Param("id") String id);

    List<Map<String, Object>> queryCompanyListToSelect(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyList(@Param("id") String id);
}
