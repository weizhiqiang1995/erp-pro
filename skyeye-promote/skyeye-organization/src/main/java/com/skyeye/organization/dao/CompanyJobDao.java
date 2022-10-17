/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.dao;

import java.util.List;
import java.util.Map;

public interface CompanyJobDao {

    List<Map<String, Object>> queryCompanyJobList(Map<String, Object> map);

    int insertCompanyJobMation(Map<String, Object> map);

    Map<String, Object> queryCompanyJobMationByName(Map<String, Object> map);

    int deleteCompanyJobMationById(Map<String, Object> map);

    Map<String, Object> queryCompanyJobMationToEditById(Map<String, Object> map);

    Map<String, Object> queryCompanyJobMationByNameAndId(Map<String, Object> map);

    int editCompanyJobMationById(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyJobListTreeByDepartmentId(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyJobListByToSelect(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyJobSimpleListByToSelect(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyJobOrganization(Map<String, Object> map);
}
