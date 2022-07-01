/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface JobDiaryService {

    void insertDayJobDiary(InputObject inputObject, OutputObject outputObject);

    void querySysEveUserStaff(InputObject inputObject, OutputObject outputObject);

    void queryJobDiaryDayReceived(InputObject inputObject, OutputObject outputObject);

    void queryJobDiaryDetails(InputObject inputObject, OutputObject outputObject);

    void queryJobDiaryDayMysend(InputObject inputObject, OutputObject outputObject);

    void deleteJobDiaryDayMysend(InputObject inputObject, OutputObject outputObject);

    void selectMysendDetails(InputObject inputObject, OutputObject outputObject);

    void editMyReceivedJobDiary(InputObject inputObject, OutputObject outputObject);

    void insertWeekJobDiary(InputObject inputObject, OutputObject outputObject);

    void selectMysendWeekDetails(InputObject inputObject, OutputObject outputObject);

    void queryWeekJobDiaryDetails(InputObject inputObject, OutputObject outputObject);

    void insertMonthJobDiary(InputObject inputObject, OutputObject outputObject);

    void selectMysendMonthDetails(InputObject inputObject, OutputObject outputObject);

    void queryMonthJobDiaryDetails(InputObject inputObject, OutputObject outputObject);

    void editJobDiaryDayMysend(InputObject inputObject, OutputObject outputObject);

    void queryJobDiaryDayMysendToEdit(InputObject inputObject, OutputObject outputObject);

    void editDayJobDiary(InputObject inputObject, OutputObject outputObject);

    void queryWeekJobDiaryDayMysendToEdit(InputObject inputObject, OutputObject outputObject);

    void editWeekDayJobDiary(InputObject inputObject, OutputObject outputObject);

    void queryMonthJobDiaryDayMysendToEdit(InputObject inputObject, OutputObject outputObject);

    void editMonthDayJobDiary(InputObject inputObject, OutputObject outputObject);

    void queryJobDiaryDayNumber(InputObject inputObject, OutputObject outputObject);

    void queryJobDiaryListToTimeTree(InputObject inputObject, OutputObject outputObject);

    void editReceivedJobDiaryToAlreadyRead(InputObject inputObject, OutputObject outputObject);

}
