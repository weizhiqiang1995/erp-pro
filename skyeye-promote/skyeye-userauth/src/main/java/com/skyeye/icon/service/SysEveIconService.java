/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.icon.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveIconService {

    void querySysIconList(InputObject inputObject, OutputObject outputObject);

    void insertSysIconMation(InputObject inputObject, OutputObject outputObject);

    void deleteSysIconMationById(InputObject inputObject, OutputObject outputObject);

    void querySysIconMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editSysIconMationById(InputObject inputObject, OutputObject outputObject);

    void querySysIconListToMenu(InputObject inputObject, OutputObject outputObject);


}
