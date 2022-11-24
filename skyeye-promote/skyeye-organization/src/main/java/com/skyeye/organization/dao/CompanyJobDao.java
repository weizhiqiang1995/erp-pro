/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.dao;

import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.organization.job.CompanyJob;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CompanyJobDao extends SkyeyeBaseMapper<CompanyJob> {

    List<Map<String, Object>> queryCompanyJobList(TableSelectInfo tableSelectInfo);

    List<Map<String, Object>> queryCompanyJobListTreeByDepartmentId(Map<String, Object> map);

    List<Map<String, Object>> queryCompanyJobSimpleList(Map<String, Object> map);

    List<Map<String, Object>> queryJobList(@Param("companyIds") List<String> companyIds, @Param("departmentIds") List<String> departmentIds);
}
