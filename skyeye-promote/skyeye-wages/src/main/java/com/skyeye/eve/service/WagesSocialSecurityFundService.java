/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface WagesSocialSecurityFundService {

    void queryWagesSocialSecurityFundList(InputObject inputObject, OutputObject outputObject);

    void insertWagesSocialSecurityFundMation(InputObject inputObject, OutputObject outputObject);

    void queryWagesSocialSecurityFundMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject);

    void deleteWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject);

    void enableWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject);

    void disableWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject);

    void queryWagesSocialSecurityFundMationById(InputObject inputObject, OutputObject outputObject);
}
