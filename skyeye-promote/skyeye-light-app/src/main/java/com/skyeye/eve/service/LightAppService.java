/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface LightAppService {

    void queryLightAppList(InputObject inputObject, OutputObject outputObject);

    void insertLightAppMation(InputObject inputObject, OutputObject outputObject);

    void queryLightAppMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editLightAppMationById(InputObject inputObject, OutputObject outputObject);

    void deleteLightAppById(InputObject inputObject, OutputObject outputObject);

    void editLightAppUpById(InputObject inputObject, OutputObject outputObject);

    void editLightAppDownById(InputObject inputObject, OutputObject outputObject);

    void queryLightAppUpList(InputObject inputObject, OutputObject outputObject);

    void insertLightAppToWin(InputObject inputObject, OutputObject outputObject);

}
