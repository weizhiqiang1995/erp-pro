/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysStaffJobResumeService {

    void queryAllSysStaffJobResumeList(InputObject inputObject, OutputObject outputObject);

    void insertSysStaffJobResumeMation(InputObject inputObject, OutputObject outputObject);

    void querySysStaffJobResumeMationToEdit(InputObject inputObject, OutputObject outputObject);

    void editSysStaffJobResumeMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSysStaffJobResumeMationById(InputObject inputObject, OutputObject outputObject);

    void queryPointStaffSysStaffJobResumeList(InputObject inputObject, OutputObject outputObject);

}
