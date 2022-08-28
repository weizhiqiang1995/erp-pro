/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.api.ApiMation;

import java.util.List;

public interface ApiMationService {

    void writeApiMation(InputObject inputObject, OutputObject outputObject);

    List<ApiMation> queryApiMationByAppIdAndUrlId(String appId, String urlId);

    void deleteApiMationById(InputObject inputObject, OutputObject outputObject);
}
