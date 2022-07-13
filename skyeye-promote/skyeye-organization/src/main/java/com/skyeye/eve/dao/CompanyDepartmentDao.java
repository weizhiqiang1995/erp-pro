/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CompanyDepartmentDao
 * @Description: 企业部门信息管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/30 19:58
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CompanyDepartmentDao {

    List<Map<String, Object>> queryCompanyDepartmentList(Map<String, Object> map);

    int insertCompanyDepartmentMation(Map<String, Object> map);

    Map<String, Object> queryCompanyDepartmentMationByName(Map<String, Object> map);

    int deleteCompanyDepartmentMationById(Map<String, Object> map);

    Map<String, Object> queryCompanyDepartmentMationById(@Param("id") String id);

    Map<String, Object> queryCompanyDepartmentMationByNameAndId(Map<String, Object> map);

    int editCompanyDepartmentMationById(Map<String, Object> map);

    Map<String, Object> queryCompanyDepartmentUserMationById(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyDepartmentListTreeByCompanyId(Map<String, Object> map);

    Map<String, Object> queryCompanyJobNumMationById(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyDepartmentListByCompanyIdToSelect(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyDepartmentOrganization(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyDepartmentListToChoose(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyDepartmentListByIds(@Param("idsList") List<String> idsList);

    List<Map<String, Object>> queryDepartmentListByCompanyId(@Param("companyId") String companyId);
}
