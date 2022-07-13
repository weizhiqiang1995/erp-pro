/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface RmGroupMemberService {

    void queryRmGroupMemberList(InputObject inputObject, OutputObject outputObject);

    void insertRmGroupMemberMation(InputObject inputObject, OutputObject outputObject);

    void editRmGroupMemberSortTopById(InputObject inputObject, OutputObject outputObject);

    void editRmGroupMemberSortLowerById(InputObject inputObject, OutputObject outputObject);

    void deleteRmGroupMemberById(InputObject inputObject, OutputObject outputObject);

    void queryRmGroupMemberMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editRmGroupMemberMationById(InputObject inputObject, OutputObject outputObject);

    void editRmGroupMemberAndPropertyMationById(InputObject inputObject, OutputObject outputObject);

    void queryRmGroupMemberAndPropertyMationById(InputObject inputObject, OutputObject outputObject);

}
