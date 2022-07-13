/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysNoticeService {

    void querySysNoticeList(InputObject inputObject, OutputObject outputObject);

    void insertSysNoticeMation(InputObject inputObject, OutputObject outputObject);

    void deleteSysNoticeById(InputObject inputObject, OutputObject outputObject);

    void updateUpSysNoticeById(InputObject inputObject, OutputObject outputObject);

    void updateDownSysNoticeById(InputObject inputObject, OutputObject outputObject);

    void selectSysNoticeById(InputObject inputObject, OutputObject outputObject);

    void editSysNoticeMationById(InputObject inputObject, OutputObject outputObject);

    void editSysNoticeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject);

    void editSysNoticeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject);

    void editSysNoticeTimeUpById(InputObject inputObject, OutputObject outputObject);

    void querySysNoticeDetailsById(InputObject inputObject, OutputObject outputObject);

    void queryUserReceivedSysNotice(InputObject inputObject, OutputObject outputObject);

    void queryReceivedSysNoticeDetailsById(InputObject inputObject, OutputObject outputObject);

}
