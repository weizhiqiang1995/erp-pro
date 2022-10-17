/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinBgPicService {

    void querySysEveWinBgPicList(InputObject inputObject, OutputObject outputObject);

    void insertSysEveWinBgPicMation(InputObject inputObject, OutputObject outputObject);

    void deleteSysEveWinBgPicMationById(InputObject inputObject, OutputObject outputObject);

    void insertSysEveWinBgPicMationByCustom(InputObject inputObject, OutputObject outputObject);

    void querySysEveWinBgPicCustomList(InputObject inputObject, OutputObject outputObject);

    void deleteSysEveWinBgPicMationCustomById(InputObject inputObject, OutputObject outputObject);

}
