/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface RmTypeService {

    void queryRmTypeList(InputObject inputObject, OutputObject outputObject);

    void insertRmTypeMation(InputObject inputObject, OutputObject outputObject);

    void deleteRmTypeById(InputObject inputObject, OutputObject outputObject);

    void queryRmTypeMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editRmTypeMationById(InputObject inputObject, OutputObject outputObject);

    void editRmTypeSortTopById(InputObject inputObject, OutputObject outputObject);

    void editRmTypeSortLowerById(InputObject inputObject, OutputObject outputObject);

    void queryRmTypeAllList(InputObject inputObject, OutputObject outputObject);

}
