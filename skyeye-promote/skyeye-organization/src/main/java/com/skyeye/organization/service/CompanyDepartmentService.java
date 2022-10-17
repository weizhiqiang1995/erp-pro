/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyDepartmentService {

    void queryCompanyDepartmentList(InputObject inputObject, OutputObject outputObject);

    void insertCompanyDepartmentMation(InputObject inputObject, OutputObject outputObject);

    void deleteCompanyDepartmentMationById(InputObject inputObject, OutputObject outputObject);

    void queryCompanyDepartmentMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editCompanyDepartmentMationById(InputObject inputObject, OutputObject outputObject);

    void queryCompanyDepartmentListTreeByCompanyId(InputObject inputObject, OutputObject outputObject);

    void queryCompanyDepartmentListByCompanyIdToSelect(InputObject inputObject, OutputObject outputObject);

    void queryCompanyDepartmentListToChoose(InputObject inputObject, OutputObject outputObject);

    void queryCompanyDepartmentListByIds(InputObject inputObject, OutputObject outputObject);

    void queryDepartmentListByCurrentUserBelong(InputObject inputObject, OutputObject outputObject);
}
