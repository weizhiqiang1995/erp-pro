/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveModelService {

    void querySysEveModelList(InputObject inputObject, OutputObject outputObject);

    void insertSysEveModelMation(InputObject inputObject, OutputObject outputObject);

    void deleteSysEveModelById(InputObject inputObject, OutputObject outputObject);

    void selectSysEveModelById(InputObject inputObject, OutputObject outputObject);

    void selectSysEveModelMationById(InputObject inputObject, OutputObject outputObject);

    void editSysEveModelMationById(InputObject inputObject, OutputObject outputObject);

}
