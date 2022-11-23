/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.organization.department.Department;

import java.util.List;
import java.util.Map;

public interface CompanyDepartmentService extends SkyeyeBusinessService<Department> {

    void queryCompanyDepartmentListTreeByCompanyId(InputObject inputObject, OutputObject outputObject);

    void queryCompanyDepartmentListByCompanyIdToSelect(InputObject inputObject, OutputObject outputObject);

    void queryCompanyDepartmentListToChoose(InputObject inputObject, OutputObject outputObject);

    void queryDepartmentListByCurrentUserBelong(InputObject inputObject, OutputObject outputObject);

    List<Map<String, Object>> queryDepartmentList(List<String> companyIds, List<String> departmentIds);
}
