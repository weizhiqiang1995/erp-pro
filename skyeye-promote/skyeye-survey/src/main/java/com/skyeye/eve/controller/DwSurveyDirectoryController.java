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
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyDirectoryList")
    public void queryDwSurveyDirectoryList(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.queryDwSurveyDirectoryList(inputObject, outputObject);
    }

    /**
     * 新增调查问卷
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/insertDwSurveyDirectoryMation")
    public void insertDwSurveyDirectoryMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.insertDwSurveyDirectoryMation(inputObject, outputObject);
    }

    /**
     * 获取调查问卷题目信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyDirectoryMationById")
    public void queryDwSurveyDirectoryMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.queryDwSurveyDirectoryMationById(inputObject, outputObject);
    }

    /**
     * 获取调查问卷信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyMationById")
    public void queryDwSurveyMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.queryDwSurveyMationById(inputObject, outputObject);
    }

    /**
     * 编辑调查问卷信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/editDwSurveyMationById")
    public void editDwSurveyMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.editDwSurveyMationById(inputObject, outputObject);
    }

    /**
     * 添加填空题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuFillblankMation")
    public void addQuFillblankMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuFillblankMation(inputObject, outputObject);
    }

    /**
     * 添加评分题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuScoreMation")
    public void addQuScoreMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuScoreMation(inputObject, outputObject);
    }

    /**
     * 添加排序题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuOrderquMation")
    public void addQuOrderquMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuOrderquMation(inputObject, outputObject);
    }

    /**
     * 添加分页标记
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuPagetagMation")
    public void addQuPagetagMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuPagetagMation(inputObject, outputObject);
    }

    /**
     * 添加单选题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuRadioMation")
    public void addQuRadioMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuRadioMation(inputObject, outputObject);
    }

    /**
     * 添加多选题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuCheckBoxMation")
    public void addQuCheckBoxMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuCheckBoxMation(inputObject, outputObject);
    }

    /**
     * 添加多选填空题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuMultiFillblankMation")
    public void addQuMultiFillblankMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuMultiFillblankMation(inputObject, outputObject);
    }

    /**
     * 添加段落题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuParagraphMation")
    public void addQuParagraphMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuParagraphMation(inputObject, outputObject);
    }

    /**
     * 添加矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/addQuChenMation")
    public void addQuChenMation(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.addQuChenMation(inputObject, outputObject);
    }

    /**
     * 删除问题
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionMationById")
    public void deleteQuestionMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionMationById(inputObject, outputObject);
    }

    /**
     * 删除矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题列选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionChenColumnMationById")
    public void deleteQuestionChenColumnMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionChenColumnMationById(inputObject, outputObject);
    }

    /**
     * 删除矩阵单选题,矩阵多选题,矩阵评分题,矩阵填空题行选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionChenRowMationById")
    public void deleteQuestionChenRowMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionChenRowMationById(inputObject, outputObject);
    }

    /**
     * 删除单选题选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionRadioOptionMationById")
    public void deleteQuestionRadioOptionMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionRadioOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除多选题选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionChedkBoxOptionMationById")
    public void deleteQuestionChedkBoxOptionMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionChedkBoxOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除评分题选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionScoreOptionMationById")
    public void deleteQuestionScoreOptionMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionScoreOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除排序选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionOrderOptionMationById")
    public void deleteQuestionOrderOptionMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionOrderOptionMationById(inputObject, outputObject);
    }

    /**
     * 删除多项填空题选项
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteQuestionMultiFillblankOptionMationById")
    public void deleteQuestionMultiFillblankOptionMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteQuestionMultiFillblankOptionMationById(inputObject, outputObject);
    }

    /**
     * 问卷发布
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/editSurveyStateToReleaseById")
    public void editSurveyStateToReleaseById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.editSurveyStateToReleaseById(inputObject, outputObject);
    }

    /**
     * 获取调查问卷题目信息用来生成html页面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryDwSurveyDirectoryMationByIdToHTML")
    public void queryDwSurveyDirectoryMationByIdToHTML(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.queryDwSurveyDirectoryMationByIdToHTML(inputObject, outputObject);
    }

    /**
     * 删除问卷
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/deleteSurveyMationById")
    public void deleteSurveyMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.deleteSurveyMationById(inputObject, outputObject);
    }

    /**
     * 分析报告问卷
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/querySurveyFxMationById")
    public void querySurveyFxMationById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.querySurveyFxMationById(inputObject, outputObject);
    }

    /**
     * 复制问卷
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/insertSurveyMationCopyById")
    public void insertSurveyMationCopyById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.insertSurveyMationCopyById(inputObject, outputObject);
    }

    /**
     * 判断该ip的用户是否回答过此问卷
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/queryAnswerSurveyMationByIp")
    public void queryAnswerSurveyMationByIp(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.queryAnswerSurveyMationByIp(inputObject, outputObject);
    }

    /**
     * 用户回答问卷
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/insertAnswerSurveyMationByIp")
    public void insertAnswerSurveyMationByIp(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.insertAnswerSurveyMationByIp(inputObject, outputObject);
    }

    /**
     * 手动结束问卷
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DwSurveyDirectoryController/updateSurveyMationEndById")
    public void updateSurveyMationEndById(InputObject inputObject, OutputObject outputObject) {
        dwSurveyDirectoryService.updateSurveyMationEndById(inputObject, outputObject);
    }

}
