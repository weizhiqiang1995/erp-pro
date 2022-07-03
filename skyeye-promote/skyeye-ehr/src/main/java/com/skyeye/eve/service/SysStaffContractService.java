/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysStaffContractService {

    void queryAllSysStaffContractList(InputObject inputObject, OutputObject outputObject);

    void insertSysStaffContractMation(InputObject inputObject, OutputObject outputObject);

    void querySysStaffContractMationToEdit(InputObject inputObject, OutputObject outputObject);

    void editSysStaffContractMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSysStaffContractMationById(InputObject inputObject, OutputObject outputObject);

    void queryPointStaffSysStaffContractList(InputObject inputObject, OutputObject outputObject);

    void signSysStaffContractById(InputObject inputObject, OutputObject outputObject);

}
