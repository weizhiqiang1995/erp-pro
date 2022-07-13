/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SmProjectPageService {

    void queryProPageMationByProIdList(InputObject inputObject, OutputObject outputObject);

    void insertProPageMationByProId(InputObject inputObject, OutputObject outputObject);

    void editSmProjectPageSortTopById(InputObject inputObject, OutputObject outputObject);

    void editSmProjectPageSortLowerById(InputObject inputObject, OutputObject outputObject);

    void querySmProjectPageMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editSmProjectPageMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSmProjectPageMationById(InputObject inputObject, OutputObject outputObject);

}
