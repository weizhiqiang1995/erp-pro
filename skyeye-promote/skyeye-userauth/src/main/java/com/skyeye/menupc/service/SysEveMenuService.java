/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.menupc.entity.SysMenu;

public interface SysEveMenuService extends SkyeyeBusinessService<SysMenu> {

    void querySysMenuMationBySimpleLevel(InputObject inputObject, OutputObject outputObject);

    void editSysEveMenuSortTopById(InputObject inputObject, OutputObject outputObject);

    void editSysEveMenuSortLowerById(InputObject inputObject, OutputObject outputObject);

}
