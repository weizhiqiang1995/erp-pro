/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CodeModelService {

    void queryCodeModelList(InputObject inputObject, OutputObject outputObject);

    void insertCodeModelMation(InputObject inputObject, OutputObject outputObject);

    void deleteCodeModelById(InputObject inputObject, OutputObject outputObject);

    void queryCodeModelMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editCodeModelMationById(InputObject inputObject, OutputObject outputObject);

}
