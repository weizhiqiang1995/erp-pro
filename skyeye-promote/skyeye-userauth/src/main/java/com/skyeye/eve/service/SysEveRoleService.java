/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveRoleService {

    void querySysRoleList(InputObject inputObject, OutputObject outputObject);

    void querySysRoleBandMenuList(InputObject inputObject, OutputObject outputObject);

    void insertSysRoleMation(InputObject inputObject, OutputObject outputObject);

    void querySysRoleMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editSysRoleMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSysRoleMationById(InputObject inputObject, OutputObject outputObject);

    void querySysRoleBandAppMenuList(InputObject inputObject, OutputObject outputObject);

    void querySysRoleToAppMenuEditById(InputObject inputObject, OutputObject outputObject);

    void editSysRoleAppMenuById(InputObject inputObject, OutputObject outputObject);

    void editSysRolePCAuth(InputObject inputObject, OutputObject outputObject);
}
