/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysNoticeTypeService {

    void querySysNoticeTypeList(InputObject inputObject, OutputObject outputObject);

    void insertSysNoticeTypeMation(InputObject inputObject, OutputObject outputObject);

    void deleteSysNoticeTypeById(InputObject inputObject, OutputObject outputObject);

    void updateUpSysNoticeTypeById(InputObject inputObject, OutputObject outputObject);

    void updateDownSysNoticeTypeById(InputObject inputObject, OutputObject outputObject);

    void selectSysNoticeTypeById(InputObject inputObject, OutputObject outputObject);

    void editSysNoticeTypeMationById(InputObject inputObject, OutputObject outputObject);

    void editSysNoticeTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject);

    void editSysNoticeTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject);

    void queryFirstSysNoticeTypeUpStateList(InputObject inputObject, OutputObject outputObject);

    void queryAllFirstSysNoticeTypeStateList(InputObject inputObject, OutputObject outputObject);

    void querySecondSysNoticeTypeUpStateList(InputObject inputObject, OutputObject outputObject);

}
