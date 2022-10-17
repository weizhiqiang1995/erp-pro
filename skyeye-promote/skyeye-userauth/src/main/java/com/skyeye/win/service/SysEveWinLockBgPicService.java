/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinLockBgPicService {

    void querySysEveWinLockBgPicList(InputObject inputObject, OutputObject outputObject);

    void insertSysEveWinLockBgPicMation(InputObject inputObject, OutputObject outputObject);

    void deleteSysEveWinLockBgPicMationById(InputObject inputObject, OutputObject outputObject);

    void insertSysEveWinBgPicMationByCustom(InputObject inputObject, OutputObject outputObject);

    void querySysEveWinBgPicCustomList(InputObject inputObject, OutputObject outputObject);

    void deleteSysEveWinBgPicMationCustomById(InputObject inputObject, OutputObject outputObject);

}
