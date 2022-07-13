/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface DwSurveyDirectoryDao {

    List<Map<String, Object>> queryDwSurveyDirectoryList(Map<String, Object> map);

    int insertDwSurveyDirectoryMation(Map<String, Object> map);

    List<Map<String, Object>> queryQuestionListByBelongId(Map<String, Object> map);

    List<Map<String, Object>> queryQuestionLogicListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenRowListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenColumnListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionMultiFillBlankListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionRadioListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionCheckBoxListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionChenOptionListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionScoreListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionOrderByListByQuestionId(Map<String, Object> question);

    List<Map<String, Object>> queryChildQuestionListByBelongId(Map<String, Object> question);

    Map<String, Object> querySurveyMationById(Map<String, Object> map);

    int editDwSurveyMationById(Map<String, Object> map);

    int addQuestionMation(Map<String, Object> map);

    int addQuestionLogicsMationList(List<Map<String, Object>> quLogics);

    int addQuestionScoreMationList(List<Map<String, Object>> quScore);

    int addQuestionOrderquMationList(List<Map<String, Object>> quOrderqu);

    int addQuestionRadioMationList(List<Map<String, Object>> quRadio);

    int addQuestionCheckBoxMationList(List<Map<String, Object>> quCheckBox);

    int addQuestionMultiFillblankMationList(List<Map<String, Object>> quMultiFillblank);

    int addQuestionColumnMationList(List<Map<String, Object>> quColumn);

    int addQuestionRowMationList(List<Map<String, Object>> quRow);

    Map<String, Object> queryQuestionMationById(Map<String, Object> map);

    int deleteLogicQuestionMationById(Map<String, Object> map);

    int deleteQuestionMationById(Map<String, Object> map);

    int deleteQuestionOptionMationByQuId(Map<String, Object> map);

    int updateQuestionOrderByIdByQuId(Map<String, Object> question);

    Map<String, Object> queryQuestionChenColumnById(Map<String, Object> map);

    int deleteLogicQuestionChenColumnMationById(Map<String, Object> map);

    int deleteQuestionChenColumnMationById(Map<String, Object> map);

    Map<String, Object> queryQuestionChenRowById(Map<String, Object> map);

    int deleteQuestionChenRowMationById(Map<String, Object> map);

    int deleteLogicQuestionChenRowMationById(Map<String, Object> map);

    Map<String, Object> queryQuestionRadioOptionById(Map<String, Object> map);

    int deleteQuestionRadioOptionMationById(Map<String, Object> map);

    int deleteLogicQuestionRadioOptionMationById(Map<String, Object> map);

    Map<String, Object> queryQuestionChedkBoxOptionById(Map<String, Object> map);

    int deleteQuestionChedkBoxOptionMationById(Map<String, Object> map);

    int deleteLogicQuestionChedkBoxOptionMationById(Map<String, Object> map);

    Map<String, Object> queryQuestionScoreOptionById(Map<String, Object> map);

    int deleteQuestionScoreOptionMationById(Map<String, Object> map);

    int deleteLogicQuestionScoreOptionMationById(Map<String, Object> map);

    Map<String, Object> queryQuestionOrderOptionById(Map<String, Object> map);

    int deleteQuestionOrderOptionMationById(Map<String, Object> map);

    int deleteLogicQuestionOrderOptionMationById(Map<String, Object> map);

    Map<String, Object> queryQuestionMultiFillblankOptionById(Map<String, Object> map);

    int deleteQuestionMultiFillblankOptionMationById(Map<String, Object> map);

    int deleteLogicQuestionMultiFillblankOptionMationById(Map<String, Object> map);

    int editQuestionMationById(Map<String, Object> map);

    int editQuestionLogicsMationList(List<Map<String, Object>> editquLogics);

    int editQuestionScoreMationList(List<Map<String, Object>> editquScore);

    int editQuestionOrderquMationList(List<Map<String, Object>> editquOrderqu);

    int editQuestionRadioMationList(List<Map<String, Object>> editquRadio);

    int editQuestionCheckBoxMationList(List<Map<String, Object>> editquCheckbox);

    int editQuestionMultiFillblankMationList(List<Map<String, Object>> editquMultiFillblank);

    int editQuestionColumnMationList(List<Map<String, Object>> editquColumn);

    int editQuestionRowMationList(List<Map<String, Object>> editquRow);

    int deleteSurveyMationById(Map<String, Object> map);

    int editSurveyStateToReleaseById(Map<String, Object> map);

    List<Map<String, Object>> queryRadioGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryCheckBoxGroupStat(Map<String, Object> question);

    Map<String, Object> queryFillBlankGroupStat(Map<String, Object> question);

    Map<String, Object> queryAnswerGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryMultiFillBlankGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryEnumQuGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryChenRadioGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryChenFbkGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryChenCheckBoxGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryChenScoreGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryScoreGroupStat(Map<String, Object> question);

    List<Map<String, Object>> queryOrderQuGroupStat(Map<String, Object> question);

    int insertSurveyMationCopyById(Map<String, Object> map);

    List<Map<String, Object>> queryQuestionMationCopyById(Map<String, Object> map);

    int addQuestionMationCopyBySurveyId(List<Map<String, Object>> questions);

    List<Map<String, Object>> queryQuestionRadioListByCopyId(Map<String, Object> question);

    int addQuestionRadioMationCopyList(List<Map<String, Object>> questionRadio);

    List<Map<String, Object>> queryQuestionCheckBoxListByCopyId(Map<String, Object> question);

    int addQuestionCheckBoxMationCopyList(List<Map<String, Object>> questionCheckBoxs);

    List<Map<String, Object>> queryQuestionMultiFillBlankListByCopyId(Map<String, Object> question);

    int addQuestionMultiFillBlankMationCopyList(List<Map<String, Object>> questionMultiFillBlanks);

    List<Map<String, Object>> queryQuestionChenRowListByCopyId(Map<String, Object> question);

    int addQuestionChenRowMationCopyList(List<Map<String, Object>> questionChenRows);

    List<Map<String, Object>> queryQuestionChenColumnListByCopyId(Map<String, Object> question);

    int addQuestionChenColumnMationCopyList(List<Map<String, Object>> questionChenColumns);

    List<Map<String, Object>> queryQuestionChenOptionListByCopyId(Map<String, Object> question);

    List<Map<String, Object>> queryQuestionScoreListByCopyId(Map<String, Object> question);

    int addQuestionScoreMationCopyList(List<Map<String, Object>> questionScores);

    List<Map<String, Object>> queryQuestionOrderByListByCopyId(Map<String, Object> question);

    int addQuestionOrderByMationCopyList(List<Map<String, Object>> questionOrderBys);

    int editSurveyAnswerNumById(Map<String, Object> surveyMation);

    int saveAnYesnoMaps(List<Map<String, Object>> beans);

    int saveAnRadioMaps(List<Map<String, Object>> beans);

    int saveAnMultiFillMaps(List<Map<String, Object>> beans);

    int saveScoreMaps(List<Map<String, Object>> beans);

    int saveChenCheckboxMaps(List<Map<String, Object>> beans);

    int saveCompAnRadioMaps(List<Map<String, Object>> beans);

    int saveCompChehRadioMaps(List<Map<String, Object>> beans);

    int saveChenScoreMaps(List<Map<String, Object>> beans);

    int saveAnCheckboxMaps(List<Map<String, Object>> beans);

    int saveAnFillMaps(List<Map<String, Object>> beans);

    int saveAnAnswerMaps(List<Map<String, Object>> beans);

    int saveCompAnCheckboxMaps(List<Map<String, Object>> beans);

    int saveEnumMaps(List<Map<String, Object>> beans);

    int saveQuOrderMaps(List<Map<String, Object>> beans);

    int saveChenRadioMaps(List<Map<String, Object>> beans);

    int saveChenFbkMaps(List<Map<String, Object>> beans);

    int insertSurveyAnswer(Map<String, Object> surveyAnswer);

    Map<String, Object> querySurveyAnswerMationByIp(Map<String, Object> map);

    List<Map<String, Object>> querySurveyAnswerMationOverFiveMinByIp(Map<String, Object> map);

    int editSurveyStateToEndNumById(Map<String, Object> surveyMation);

    int updateSurveyMationEndById(Map<String, Object> map);

}
