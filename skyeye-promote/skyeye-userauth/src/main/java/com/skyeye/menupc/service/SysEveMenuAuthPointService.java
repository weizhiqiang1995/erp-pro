/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menupc.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveMenuAuthPointService {

    void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject);

    void writeSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject);

    void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject);

    void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject);

}
