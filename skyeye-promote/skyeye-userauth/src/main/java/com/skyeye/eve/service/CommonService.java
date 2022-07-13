/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CommonService {

    void uploadFile(InputObject inputObject, OutputObject outputObject);

    void uploadFileBase64(InputObject inputObject, OutputObject outputObject);

    void downloadFileByJsonData(InputObject inputObject, OutputObject outputObject);

    void querySysWinMationById(InputObject inputObject, OutputObject outputObject);

    void queryAllSysUserIsIncumbency(InputObject inputObject, OutputObject outputObject);

    void queryFilePathByFileType(InputObject inputObject, OutputObject outputObject);
}
