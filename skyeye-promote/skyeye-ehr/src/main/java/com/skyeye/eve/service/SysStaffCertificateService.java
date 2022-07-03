/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysStaffCertificateService {

    void queryAllSysStaffCertificateList(InputObject inputObject, OutputObject outputObject);

    void insertSysStaffCertificateMation(InputObject inputObject, OutputObject outputObject);

    void querySysStaffCertificateMationToEdit(InputObject inputObject, OutputObject outputObject);

    void editSysStaffCertificateMationById(InputObject inputObject, OutputObject outputObject);

    void deleteSysStaffCertificateMationById(InputObject inputObject, OutputObject outputObject);

    void queryPointStaffSysStaffCertificateList(InputObject inputObject, OutputObject outputObject);

}
