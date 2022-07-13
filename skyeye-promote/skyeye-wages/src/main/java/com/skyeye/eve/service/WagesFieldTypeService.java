/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface WagesFieldTypeService {

    void queryWagesFieldTypeList(InputObject inputObject, OutputObject outputObject);

    void insertWagesFieldTypeMation(InputObject inputObject, OutputObject outputObject);

    void queryWagesFieldTypeMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject);

    void deleteWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject);

    void enableWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject);

    void disableWagesFieldTypeMationById(InputObject inputObject, OutputObject outputObject);

    void queryEnableWagesFieldTypeList(InputObject inputObject, OutputObject outputObject);

    void querySysWagesFieldTypeList(InputObject inputObject, OutputObject outputObject);
}
