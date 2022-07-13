/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyJobScoreService {

    void queryCompanyJobScoreList(InputObject inputObject, OutputObject outputObject);

    void insertCompanyJobScoreMation(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobScoreMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject);

    void deleteCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject);

    void enableCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject);

    void disableCompanyJobScoreMationById(InputObject inputObject, OutputObject outputObject);

    void queryEnableCompanyJobScoreList(InputObject inputObject, OutputObject outputObject);

    void queryCompanyJobScoreDetailMationById(InputObject inputObject, OutputObject outputObject);
}
