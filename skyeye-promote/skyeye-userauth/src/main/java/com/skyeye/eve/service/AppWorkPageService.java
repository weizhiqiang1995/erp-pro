/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface AppWorkPageService {

    void queryAppWorkPageList(InputObject inputObject, OutputObject outputObject);

    void writeAppWorkPageMation(InputObject inputObject, OutputObject outputObject);

    void queryAppWorkPageMationById(InputObject inputObject, OutputObject outputObject);

    void deleteAppWorkPageMationById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageSortTopById(InputObject inputObject, OutputObject outputObject);

    void editAppWorkPageSortLowerById(InputObject inputObject, OutputObject outputObject);

    void queryAppWorkPageListByParentId(InputObject inputObject, OutputObject outputObject);
}
