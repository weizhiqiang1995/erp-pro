/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @ClassName: HolidayScheduleService
 * @Description: 企业节假日安排服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/1 21:58
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface HolidayScheduleService {

    void queryHolidayScheduleList(InputObject inputObject, OutputObject outputObject);

    void downloadScheduleTemplate(InputObject inputObject, OutputObject outputObject);

    void exploreScheduleTemplate(InputObject inputObject, OutputObject outputObject);

    void deleteHolidayScheduleById(InputObject inputObject, OutputObject outputObject);

    void deleteHolidayScheduleByThisYear(InputObject inputObject, OutputObject outputObject);

    void addHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject);

    void deleteHolidayScheduleRemind(InputObject inputObject, OutputObject outputObject);

    void queryScheduleByIdToEdit(InputObject inputObject, OutputObject outputObject);

    void editScheduleById(InputObject inputObject, OutputObject outputObject);

    void addSchedule(InputObject inputObject, OutputObject outputObject);

    void queryHolidayScheduleListBySys(InputObject inputObject, OutputObject outputObject);

}
