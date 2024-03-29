/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.entity.DsFormPage;

public interface DsFormPageService extends SkyeyeBusinessService<DsFormPage> {

    void queryDsFormPageList(InputObject inputObject, OutputObject outputObject);

    void writeDsFormPageContent(InputObject inputObject, OutputObject outputObject);

    void writeDsFormPageTable(InputObject inputObject, OutputObject outputObject);

    void queryBusinessDataByObject(InputObject inputObject, OutputObject outputObject);

    void queryDsFormPageForProcess(InputObject inputObject, OutputObject outputObject);
}
