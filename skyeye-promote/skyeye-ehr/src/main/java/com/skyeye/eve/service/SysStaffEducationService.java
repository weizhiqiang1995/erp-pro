/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysStaffEducationService {

    void queryAllSysStaffEducationList(InputObject inputObject, OutputObject outputObject);

    void insertSysStaffEducationMation(InputObject inputObject, OutputObject outputObject);

    void querySysStaffEducationMationToEdit(InputObject inputObject, OutputObject outputObject);

    void editSysStaffEducationMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSysStaffEducationMationById(InputObject inputObject, OutputObject outputObject);

    void queryPointStaffSysStaffEducationList(InputObject inputObject, OutputObject outputObject);

}
