/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ApiService {

    void queryAllSysEveReqMapping(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryApiDetails(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryLimitRestrictions(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryApiMicroservices(InputObject inputObject, OutputObject outputObject) throws Exception;
}
