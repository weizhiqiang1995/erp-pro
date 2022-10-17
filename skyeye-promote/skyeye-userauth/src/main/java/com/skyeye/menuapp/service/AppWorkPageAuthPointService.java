/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menuapp.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface AppWorkPageAuthPointService {

    void queryAppWorkPageAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject);

    void writeAppWorkPageAuthPointMation(InputObject inputObject, OutputObject outputObject);

    void queryAppWorkPageAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject);

    void deleteAppWorkPageAuthPointMationById(InputObject inputObject, OutputObject outputObject);

}
