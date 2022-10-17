/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveMenuService {

    void querySysMenuList(InputObject inputObject, OutputObject outputObject);

    void insertSysMenuMation(InputObject inputObject, OutputObject outputObject);

    void querySysMenuMationToEditById(InputObject inputObject, OutputObject outputObject);

    void querySysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject);

    void editSysMenuMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSysMenuMationById(InputObject inputObject, OutputObject outputObject);

    void querySysMenuLevelList(InputObject inputObject, OutputObject outputObject);

    void editSysEveMenuSortTopById(InputObject inputObject, OutputObject outputObject);

    void editSysEveMenuSortLowerById(InputObject inputObject, OutputObject outputObject);

    void querySysEveMenuBySysId(InputObject inputObject, OutputObject outputObject);

}
