/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.dao;

import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.organization.department.Department;
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
public interface CompanyDepartmentDao extends SkyeyeBaseMapper<Department> {

    List<Map<String, Object>> queryCompanyDepartmentList(CommonPageInfo commonPageInfo);

    Map<String, Object> queryCompanyDepartmentUserMationById(@Param("id") String id);

    List<Map<String, Object>> queryCompanyDepartmentListTreeByCompanyId(Map<String, Object> map);

    Map<String, Object> queryCompanyJobNumMationById(@Param("id") String id);

    List<Map<String, Object>> queryCompanyDepartmentOrganization(Map<String, Object> map);

    List<Map<String, Object>> queryDepartmentListByCompanyId(@Param("companyId") String companyId);
}
