/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyMationService {

    void queryCompanyMationList(InputObject inputObject, OutputObject outputObject);

    void insertCompanyMation(InputObject inputObject, OutputObject outputObject);

    void deleteCompanyMationById(InputObject inputObject, OutputObject outputObject);

    void queryCompanyMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editCompanyMationById(InputObject inputObject, OutputObject outputObject);

    void queryOverAllCompanyMationList(InputObject inputObject, OutputObject outputObject);

    void queryCompanyMationListTree(InputObject inputObject, OutputObject outputObject);

    void queryCompanyListToSelect(InputObject inputObject, OutputObject outputObject);

    void queryCompanyOrganization(InputObject inputObject, OutputObject outputObject);

    void queryCompanyMationListToChoose(InputObject inputObject, OutputObject outputObject);

    void queryCompanyMationListByIds(InputObject inputObject, OutputObject outputObject);
}
