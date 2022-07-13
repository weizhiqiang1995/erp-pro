/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyJobService {

    void queryCompanyJobList(InputObject inputObject, OutputObject outputObject);

    void insertCompanyJobMation(InputObject inputObject, OutputObject outputObject);

    void deleteCompanyJobMationById(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editCompanyJobMationById(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobListTreeByDepartmentId(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobListByToSelect(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobSimpleListByToSelect(InputObject inputObject, OutputObject outputObject);

}
