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

   void queryMyScheduleList(InputObject inputObject, OutputObject outputObject);

    void insertScheduleMationByOtherModule(InputObject inputObject, OutputObject outputObject);

    /**
     * 将其他模块同步到日程
     * 不启动日程定时任务，如需启动，在该接口外面自行启动
     *
     * @param title      标题
     * @param content    日程内容
     * @param startTime  开始时间,格式为：yyyy-MM-dd HH:mm:ss
     * @param endTime    结束时间,格式为：yyyy-MM-dd HH:mm:ss
     * @param userId     执行人
     * @param objectId   关联id
     * @param objectType object类型：1.任务计划id，2.项目任务id
     */
   String synchronizationSchedule(String title, String content, String startTime, String endTime, String userId, String objectId, int objectType);
}
