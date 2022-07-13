/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface RmGroupService {

    void queryRmGroupList(InputObject inputObject, OutputObject outputObject);

    void insertRmGroupMation(InputObject inputObject, OutputObject outputObject);

    void deleteRmGroupById(InputObject inputObject, OutputObject outputObject);

    void queryRmGroupMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editRmGroupMationById(InputObject inputObject, OutputObject outputObject);

    void editRmGroupSortTopById(InputObject inputObject, OutputObject outputObject);

    void editRmGroupSortLowerById(InputObject inputObject, OutputObject outputObject);

    void queryRmGroupAllList(InputObject inputObject, OutputObject outputObject);

}
