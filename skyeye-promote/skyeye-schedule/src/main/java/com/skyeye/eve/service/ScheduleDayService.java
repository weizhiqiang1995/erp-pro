/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ScheduleDayService {

    void insertScheduleDayMation(InputObject inputObject, OutputObject outputObject);

    void queryScheduleDayMationByUserId(InputObject inputObject, OutputObject outputObject);

    void queryScheduleDayMationTodayByUserId(InputObject inputObject, OutputObject outputObject);

    void editScheduleDayMationById(InputObject inputObject, OutputObject outputObject);

    void queryScheduleDayMationById(InputObject inputObject, OutputObject outputObject);

    void deleteScheduleDayMationById(InputObject inputObject, OutputObject outputObject);

    void queryMyScheduleList(InputObject inputObject, OutputObject outputObject);

    void insertScheduleMationByOtherModule(InputObject inputObject, OutputObject outputObject);

    void deleteScheduleMationByObjectId(InputObject inputObject, OutputObject outputObject);
}
