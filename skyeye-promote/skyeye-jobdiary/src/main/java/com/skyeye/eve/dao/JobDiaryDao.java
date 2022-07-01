/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: JobDiaryDao
 * @Description: 工作日报管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:59
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface JobDiaryDao {

    int insertDayJobDiary(Map<String, Object> map);

    List<Map<String, Object>> querySysEveUserStaff(Map<String, Object> map);

    int insertDayJobDiaryReceived(List<Map<String, Object>> beans);

    List<Map<String, Object>> queryJobDiaryDayReceived(Map<String, Object> map);

    Map<String, Object> queryJobDiaryDetails(Map<String, Object> map);

    int editJobDiaryState(Map<String, Object> map);

    List<Map<String, Object>> queryJobDiaryDayMysend(Map<String, Object> map);

    Map<String, Object> queryCreateTime(Map<String, Object> map);

    int editJobDiaryDayMysendState(Map<String, Object> map);

    Map<String, Object> selectMysendDetails(Map<String, Object> map);

    int editMyReceivedJobDiary(Map<String, Object> map);

    Map<String, Object> queryJobDiaryState(Map<String, Object> map);

    int insertWeekJobDiary(Map<String, Object> map);

    int insertWeekJobDiaryReceived(List<Map<String, Object>> beans);

    int editJobDiaryWeekMysendState(Map<String, Object> map);

    int editMyReceivedWeekJobDiary(Map<String, Object> map);

    Map<String, Object> queryDiaryType(Map<String, Object> map);

    Map<String, Object> selectMysendWeekDetails(Map<String, Object> map);

    Map<String, Object> queryWeekJobDiaryDetails(Map<String, Object> map);

    Map<String, Object> queryWeekJobDiaryState(Map<String, Object> map);

    int editWeekJobDiaryState(Map<String, Object> map);

    int insertMonthJobDiary(Map<String, Object> map);

    int insertMonthJobDiaryReceived(List<Map<String, Object>> beans);

    int editMyReceivedMonthJobDiary(Map<String, Object> map);

    int editJobDiaryMonthMysendState(Map<String, Object> map);

    Map<String, Object> selectMysendMonthDetails(Map<String, Object> map);

    Map<String, Object> queryMonthJobDiaryState(Map<String, Object> map);

    int editMonthJobDiaryState(Map<String, Object> map);

    Map<String, Object> queryMonthJobDiaryDetails(Map<String, Object> map);

    Map<String, Object> queryJobDiaryType(Map<String, Object> map);

    int editJobDiaryDayMysendDelete(Map<String, Object> map);

    int editJobDiaryWeekMysendDelete(Map<String, Object> map);

    int editJobDiaryMonthMysendDelete(Map<String, Object> map);

    Map<String, Object> queryJobDiaryDayMysendToEdit(Map<String, Object> map);

    int editDayJobDiary(Map<String, Object> map);

    int deleteJobDiaryReceived(Map<String, Object> map);

    Map<String, Object> queryWeekJobDiaryDayMysendToEdit(Map<String, Object> map);

    int editWeekDayJobDiary(Map<String, Object> map);

    int deleteWeekJobDiaryReceived(Map<String, Object> map);

    Map<String, Object> queryMonthJobDiaryDayMysendToEdit(Map<String, Object> map);

    int editMonthDayJobDiary(Map<String, Object> map);

    int deleteMonthJobDiaryReceived(Map<String, Object> map);

    List<Map<String, Object>> queryJobDiaryDayNumber(Map<String, Object> map);

    List<Map<String, Object>> queryJobDiaryListToTimeTree(Map<String, Object> map);

    List<Map<String, Object>> queryJobDiaryDayChildListToTimeTree(Map<String, Object> bean);

    int editReceivedJobDiaryToAlreadyRead(Map<String, Object> map);

    void editReceivedWeekJobDiaryToAlreadyRead(Map<String, Object> map);

    void editReceivedMonthJobDiaryToAlreadyRead(Map<String, Object> map);

    List<Map<String, Object>> queryJobDiaryDayReceivedUserInfoById(Map<String, Object> map);

    List<Map<String, Object>> queryWeekJobDiaryDayReceivedUserInfoById(Map<String, Object> map);

    List<Map<String, Object>> queryMonthJobDiaryDayReceivedUserInfoById(Map<String, Object> map);

}
