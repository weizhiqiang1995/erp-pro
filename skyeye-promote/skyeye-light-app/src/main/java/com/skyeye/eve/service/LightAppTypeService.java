/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface LightAppTypeService {

    void queryLightAppTypeList(InputObject inputObject, OutputObject outputObject);

    void insertLightAppTypeMation(InputObject inputObject, OutputObject outputObject);

    void queryLightAppTypeMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editLightAppTypeMationById(InputObject inputObject, OutputObject outputObject);

    void editLightAppTypeSortTopById(InputObject inputObject, OutputObject outputObject);

    void editLightAppTypeSortLowerById(InputObject inputObject, OutputObject outputObject);

    void deleteLightAppTypeById(InputObject inputObject, OutputObject outputObject);

    void editLightAppTypeUpTypeById(InputObject inputObject, OutputObject outputObject);

    void editLightAppTypeDownTypeById(InputObject inputObject, OutputObject outputObject);

    void queryLightAppTypeUpList(InputObject inputObject, OutputObject outputObject);

}
