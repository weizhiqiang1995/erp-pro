/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinDragDropService {

    void insertWinCustomMenuBox(InputObject inputObject, OutputObject outputObject);

    void insertWinCustomMenu(InputObject inputObject, OutputObject outputObject);

    void deleteWinMenuOrBoxById(InputObject inputObject, OutputObject outputObject);

    void editMenuParentIdById(InputObject inputObject, OutputObject outputObject);

    void queryMenuMationTypeById(InputObject inputObject, OutputObject outputObject);

    void queryCustomMenuBoxMationEditById(InputObject inputObject, OutputObject outputObject);

    void editCustomMenuBoxMationById(InputObject inputObject, OutputObject outputObject);

    void queryCustomMenuMationEditById(InputObject inputObject, OutputObject outputObject);

    void editCustomMenuMationById(InputObject inputObject, OutputObject outputObject);

    void editCustomMenuToDeskTopById(InputObject inputObject, OutputObject outputObject);

}
