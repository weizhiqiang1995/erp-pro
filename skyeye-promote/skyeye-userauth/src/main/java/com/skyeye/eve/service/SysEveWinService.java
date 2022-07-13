/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinService {

    void queryWinMationList(InputObject inputObject, OutputObject outputObject);

    void insertWinMation(InputObject inputObject, OutputObject outputObject);

    void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editWinMationById(InputObject inputObject, OutputObject outputObject);

    void deleteWinMationById(InputObject inputObject, OutputObject outputObject);

    void editAuthorizationById(InputObject inputObject, OutputObject outputObject);

    void editCancleAuthorizationById(InputObject inputObject, OutputObject outputObject);

    void queryWinMationListToShow(InputObject inputObject, OutputObject outputObject);

    void insertWinMationImportantSynchronization(InputObject inputObject, OutputObject outputObject);

    void queryWinMationImportantSynchronizationData(InputObject inputObject, OutputObject outputObject);

}
