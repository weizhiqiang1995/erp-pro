/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.ReportTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportTypeController {

    @Autowired
    private ReportTypeService reportTypeService;

    /**
     * 获取举报类型列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/queryReportTypeList")
    public void queryReportTypeList(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.queryReportTypeList(inputObject, outputObject);
    }

    /**
     * 新增举报类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/insertReportTypeMation")
    public void insertReportTypeMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.insertReportTypeMation(inputObject, outputObject);
    }

    /**
     * 编辑举报类型时进行信息回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/queryReportTypeMationToEditById")
    public void queryReportTypeMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.queryReportTypeMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑举报类型信息
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/editReportTypeMationById")
    public void editReportTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.editReportTypeMationById(inputObject, outputObject);
    }

    /**
     * 举报类型展示顺序上移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/editReportTypeSortTopById")
    public void editReportTypeSortTopById(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.editReportTypeSortTopById(inputObject, outputObject);
    }

    /**
     * 举报类型展示顺序下移
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/editReportTypeSortLowerById")
    public void editReportTypeSortLowerById(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.editReportTypeSortLowerById(inputObject, outputObject);
    }

    /**
     * 删除举报类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/deleteReportTypeById")
    public void deleteReportTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.deleteReportTypeById(inputObject, outputObject);
    }

    /**
     * 举报类型上线
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/editReportTypeUpTypeById")
    public void editReportTypeUpTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.editReportTypeUpTypeById(inputObject, outputObject);
    }

    /**
     * 举报类型下线
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/editReportTypeDownTypeById")
    public void editReportTypeDownTypeById(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.editReportTypeDownTypeById(inputObject, outputObject);
    }

    /**
     * 获取举报上线类型列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ReportTypeController/queryReportTypeUpList")
    public void queryReportTypeUpList(InputObject inputObject, OutputObject outputObject) throws Exception {
        reportTypeService.queryReportTypeUpList(inputObject, outputObject);
    }

}
