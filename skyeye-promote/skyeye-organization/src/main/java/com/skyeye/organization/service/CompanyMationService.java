/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.organization.company.Company;

public interface CompanyMationService extends SkyeyeBusinessService<Company> {

    void queryOverAllCompanyMationList(InputObject inputObject, OutputObject outputObject);

    void queryCompanyMationListTree(InputObject inputObject, OutputObject outputObject);

    void queryCompanyListToSelect(InputObject inputObject, OutputObject outputObject);

    void queryCompanyOrganization(InputObject inputObject, OutputObject outputObject);
}
