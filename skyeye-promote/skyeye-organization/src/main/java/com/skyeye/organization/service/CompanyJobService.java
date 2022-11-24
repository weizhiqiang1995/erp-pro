/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.organization.job.CompanyJob;

import java.util.List;
import java.util.Map;

public interface CompanyJobService extends SkyeyeBusinessService<CompanyJob> {

    void queryCompanyJobListTreeByDepartmentId(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobListByToSelect(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobSimpleListByToSelect(InputObject inputObject, OutputObject outputObject);

    List<Map<String, Object>> queryJobList(List<String> companyIds, List<String> departmentIds);

}
