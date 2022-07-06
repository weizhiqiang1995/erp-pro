/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CodeModelGroupService {

    void queryCodeModelGroupList(InputObject inputObject, OutputObject outputObject);

    void insertCodeModelGroupMation(InputObject inputObject, OutputObject outputObject);

    void deleteCodeModelGroupById(InputObject inputObject, OutputObject outputObject);

    void queryCodeModelGroupMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editCodeModelGroupMationById(InputObject inputObject, OutputObject outputObject);

    void queryTableParameterByTableName(InputObject inputObject, OutputObject outputObject);

    void queryTableMationByTableName(InputObject inputObject, OutputObject outputObject);

    void queryCodeModelListByGroupId(InputObject inputObject, OutputObject outputObject);

}
