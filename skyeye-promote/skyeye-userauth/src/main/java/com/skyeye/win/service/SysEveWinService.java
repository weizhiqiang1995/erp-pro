/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinService {

    void queryWinMationList(InputObject inputObject, OutputObject outputObject);

    void insertWinMation(InputObject inputObject, OutputObject outputObject);

    void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject);

    void deleteWinMationById(InputObject inputObject, OutputObject outputObject);

    void querySysEveWinList(InputObject inputObject, OutputObject outputObject);
}
