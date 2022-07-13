/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinTypeService {

    void querySysWinTypeList(InputObject inputObject, OutputObject outputObject);

    void querySysWinFirstTypeList(InputObject inputObject, OutputObject outputObject);

    void insertSysWinTypeMation(InputObject inputObject, OutputObject outputObject);

    void querySysWinTypeMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editSysWinTypeMationById(InputObject inputObject, OutputObject outputObject);

    void querySysWinFirstTypeListNotIsThisId(InputObject inputObject, OutputObject outputObject);

    void deleteSysWinTypeMationById(InputObject inputObject, OutputObject outputObject);

    void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject);

    void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject);

    void editSysWinTypeMationStateUpById(InputObject inputObject, OutputObject outputObject);

    void editSysWinTypeMationStateDownById(InputObject inputObject, OutputObject outputObject);

    void querySysWinTypeFirstMationStateIsUp(InputObject inputObject, OutputObject outputObject);

    void querySysWinTypeSecondMationStateIsUp(InputObject inputObject, OutputObject outputObject);

}
