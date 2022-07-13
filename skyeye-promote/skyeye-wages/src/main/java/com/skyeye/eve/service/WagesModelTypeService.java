/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface WagesModelTypeService {

    void queryWagesModelTypeList(InputObject inputObject, OutputObject outputObject);

    void insertWagesModelTypeMation(InputObject inputObject, OutputObject outputObject);

    void queryWagesModelTypeMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject);

    void deleteWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject);

    void enableWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject);

    void disableWagesModelTypeMationById(InputObject inputObject, OutputObject outputObject);

    void queryEnableWagesModelTypeList(InputObject inputObject, OutputObject outputObject);
}
