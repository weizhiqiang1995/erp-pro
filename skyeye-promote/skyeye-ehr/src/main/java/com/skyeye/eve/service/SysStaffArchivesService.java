/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysStaffArchivesService {

    void queryAllSysStaffArchivesList(InputObject inputObject, OutputObject outputObject);

    void querySysLeaveStaffArchivesList(InputObject inputObject, OutputObject outputObject);

    void querySysStaffNotInArchivesList(InputObject inputObject, OutputObject outputObject);

    void querySysStaffNoArchivesList(InputObject inputObject, OutputObject outputObject);

    void insertSysStaffArchivesMation(InputObject inputObject, OutputObject outputObject);

    void querySysStaffArchivesMationToEdit(InputObject inputObject, OutputObject outputObject);

    void editSysStaffArchivesMationById(InputObject inputObject, OutputObject outputObject);

}
