/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.DwSurveyDirectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DwSurveyDirectoryController {

    @Autowired
    private DwSurveyDirectoryService dwSurveyDirectoryService;

    /**
     * 获取调查问卷列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyDirectoryList")
    public void queryDwSurveyDirectoryList(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.queryDwSurveyDirectoryList(inputObject, outputObject);
    }

    /**
     * 新增调查问卷
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/insertDwSurveyDirectoryMation")
    public void insertDwSurveyDirectoryMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.insertDwSurveyDirectoryMation(inputObject, outputObject);
    }

    /**
     * 获取调查问卷题目信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyDirectoryMationById")
    public void queryDwSurveyDirectoryMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.queryDwSurveyDirectoryMationById(inputObject, outputObject);
    }

    /**
     * 获取调查问卷信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyMationById")
    public void queryDwSurveyMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.queryDwSurveyMationById(inputObject, outputObject);
    }

    /**
     * 编辑调查问卷信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/editDwSurveyMationById")
    public void editDwSurveyMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.editDwSurveyMationById(inputObject, outputObject);
    }

    /**
     * 添加填空题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuFillblankMation")
    public void addQuFillblankMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuFillblankMation(inputObject, outputObject);
    }

    /**
     * 添加评分题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuScoreMation")
    public void addQuScoreMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuScoreMation(inputObject, outputObject);
    }

    /**
     * 添加排序题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuOrderquMation")
    public void addQuOrderquMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuOrderquMation(inputObject, outputObject);
    }

    /**
     * 添加分页标记
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuPagetagMation")
    public void addQuPagetagMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuPagetagMation(inputObject, outputObject);
    }

    /**
     * 添加单选题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuRadioMation")
    public void addQuRadioMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuRadioMation(inputObject, outputObject);
    }

    /**
     * 添加多选题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuCheckBoxMation")
    public void addQuCheckBoxMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuCheckBoxMation(inputObject, outputObject);
    }

    /**
     * 添加多选填空题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuMultiFillblankMation")
    public void addQuMultiFillblankMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuMultiFillblankMation(inputObject, outputObject);
    }

    /**
     * 添加段落题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuParagraphMation")
    public void addQuParagraphMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuParagraphMation(inputObject, outputObject);
    }

    /**
     * 添加矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuChenMation")
    public void addQuChenMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.addQuChenMation(inputObject, outputObject);
    }

    /**
     * 删除问题
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionMationById")
    public void deleteQuestionMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionMationById(inputObject, outputObject);
    }

    /**
     * 删除矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题列选项
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionChenColumnMationById")
    public void deleteQuestionChenColumnMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionChenColumnMationById(inputObject, outputObject);
    }

    /**
     * 删除矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题行选项
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionChenRowMationById")
    public void deleteQuestionChenRowMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionChenRowMationById(inputObject, outputObject);
    }

    /**
     * 删除单选题选项
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionRadioOptionMationById")
    public void deleteQuestionRadioOptionMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionRadioOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除多选题选项
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionChedkBoxOptionMationById")
    public void deleteQuestionChedkBoxOptionMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionChedkBoxOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除评分题选项
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionScoreOptionMationById")
    public void deleteQuestionScoreOptionMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionScoreOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除排序选项
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionOrderOptionMationById")
    public void deleteQuestionOrderOptionMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionOrderOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除多项填空题选项
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionMultiFillblankOptionMationById")
    public void deleteQuestionMultiFillblankOptionMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteQuestionMultiFillblankOptionMationById(inputObject, outputObject);
    }

    /**
     * 问卷发布
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/editSurveyStateToReleaseById")
    public void editSurveyStateToReleaseById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.editSurveyStateToReleaseById(inputObject, outputObject);
    }

    /**
     * 获取调查问卷题目信息用来生成html页面
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyDirectoryMationByIdToHTML")
    public void queryDwSurveyDirectoryMationByIdToHTML(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.queryDwSurveyDirectoryMationByIdToHTML(inputObject, outputObject);
    }

    /**
     * 删除问卷
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteSurveyMationById")
    public void deleteSurveyMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.deleteSurveyMationById(inputObject, outputObject);
    }

    /**
     * 分析报告问卷
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/querySurveyFxMationById")
    public void querySurveyFxMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.querySurveyFxMationById(inputObject, outputObject);
    }

    /**
     * 复制问卷
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/insertSurveyMationCopyById")
    public void insertSurveyMationCopyById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.insertSurveyMationCopyById(inputObject, outputObject);
    }

    /**
     * 判断该ip的用户是否回答过此问卷
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryAnswerSurveyMationByIp")
    public void queryAnswerSurveyMationByIp(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.queryAnswerSurveyMationByIp(inputObject, outputObject);
    }

    /**
     * 用户回答问卷
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/insertAnswerSurveyMationByIp")
    public void insertAnswerSurveyMationByIp(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.insertAnswerSurveyMationByIp(inputObject, outputObject);
    }

    /**
     * 手动结束问卷
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/DwSurveyDirectoryController/updateSurveyMationEndById")
    public void updateSurveyMationEndById(InputObject inputObject, OutputObject outputObject) throws Exception {
        dwSurveyDirectoryService.updateSurveyMationEndById(inputObject, outputObject);
    }

}
