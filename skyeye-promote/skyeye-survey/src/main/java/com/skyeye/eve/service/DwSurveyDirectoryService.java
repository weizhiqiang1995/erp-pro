/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface DwSurveyDirectoryService {

    void queryDwSurveyDirectoryList(InputObject inputObject, OutputObject outputObject);

    void insertDwSurveyDirectoryMation(InputObject inputObject, OutputObject outputObject);

    void queryDwSurveyDirectoryMationById(InputObject inputObject, OutputObject outputObject);

    void queryDwSurveyMationById(InputObject inputObject, OutputObject outputObject);

    void editDwSurveyMationById(InputObject inputObject, OutputObject outputObject);

    void addQuFillblankMation(InputObject inputObject, OutputObject outputObject);

    void addQuScoreMation(InputObject inputObject, OutputObject outputObject);

    void addQuOrderquMation(InputObject inputObject, OutputObject outputObject);

    void addQuPagetagMation(InputObject inputObject, OutputObject outputObject);

    void addQuRadioMation(InputObject inputObject, OutputObject outputObject);

    void addQuCheckBoxMation(InputObject inputObject, OutputObject outputObject);

    void addQuMultiFillblankMation(InputObject inputObject, OutputObject outputObject);

    void addQuParagraphMation(InputObject inputObject, OutputObject outputObject);

    void addQuChenMation(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionChenColumnMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionChenRowMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionRadioOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionChedkBoxOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionScoreOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionOrderOptionMationById(InputObject inputObject, OutputObject outputObject);

    void deleteQuestionMultiFillblankOptionMationById(InputObject inputObject, OutputObject outputObject);

    void editSurveyStateToReleaseById(InputObject inputObject, OutputObject outputObject);

    void queryDwSurveyDirectoryMationByIdToHTML(InputObject inputObject, OutputObject outputObject);

    void deleteSurveyMationById(InputObject inputObject, OutputObject outputObject);

    void querySurveyFxMationById(InputObject inputObject, OutputObject outputObject);

    void insertSurveyMationCopyById(InputObject inputObject, OutputObject outputObject);

    void queryAnswerSurveyMationByIp(InputObject inputObject, OutputObject outputObject);

    void insertAnswerSurveyMationByIp(InputObject inputObject, OutputObject outputObject);

    void updateSurveyMationEndById(InputObject inputObject, OutputObject outputObject);

}
