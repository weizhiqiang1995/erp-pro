/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 */

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysStaffLanguageLevelService {

    void querySysStaffLanguageLevelList(InputObject inputObject, OutputObject outputObject);

    void insertSysStaffLanguageLevelMation(InputObject inputObject, OutputObject outputObject);

    void querySysStaffLanguageLevelMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject);

    void downSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject);

    void upSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSysStaffLanguageLevelMationById(InputObject inputObject, OutputObject outputObject);

    void querySysStaffLanguageLevelUpMation(InputObject inputObject, OutputObject outputObject);
}
