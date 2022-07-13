/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveUserNoticeService {

    void getNoticeListByUserId(InputObject inputObject, OutputObject outputObject);

    void getAllNoticeListByUserId(InputObject inputObject, OutputObject outputObject);

    void editNoticeMationById(InputObject inputObject, OutputObject outputObject);

    void deleteNoticeMationById(InputObject inputObject, OutputObject outputObject);

    void editNoticeMationByIds(InputObject inputObject, OutputObject outputObject);

    void deleteNoticeMationByIds(InputObject inputObject, OutputObject outputObject);

    void queryNoticeMationById(InputObject inputObject, OutputObject outputObject);

}
