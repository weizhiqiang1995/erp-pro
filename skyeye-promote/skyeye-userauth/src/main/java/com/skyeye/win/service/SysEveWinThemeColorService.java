/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinThemeColorService {

    void querySysEveWinThemeColorList(InputObject inputObject, OutputObject outputObject);

    void insertSysEveWinThemeColorMation(InputObject inputObject, OutputObject outputObject);

    void deleteSysEveWinThemeColorMationById(InputObject inputObject, OutputObject outputObject);

    void querySysEveWinThemeColorMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editSysEveWinThemeColorMationById(InputObject inputObject, OutputObject outputObject);

}
