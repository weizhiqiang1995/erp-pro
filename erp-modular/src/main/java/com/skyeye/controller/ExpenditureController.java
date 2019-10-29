package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.ExpenditureService;

/**
 * @Author 卫志强
 * @Description 支出单
 * @Date 2019/10/20 10:22
 */
@Controller
public class ExpenditureController {

    @Autowired
    private ExpenditureService expenditureService;

    /**
     * 查询支出单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ExpenditureController/queryExpenditureByList")
    @ResponseBody
    public void queryExpenditureByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        expenditureService.queryExpenditureByList(inputObject, outputObject);
    }

    /**
     * 添加支出单
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ExpenditureController/insertExpenditure")
    @ResponseBody
    public void insertExpenditure(InputObject inputObject, OutputObject outputObject) throws Exception{
        expenditureService.insertExpenditure(inputObject, outputObject);
    }

    /**
     * 查询支出单用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ExpenditureController/queryExpenditureToEditById")
    @ResponseBody
    public void queryExpenditureToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
        expenditureService.queryExpenditureToEditById(inputObject, outputObject);
    }

    /**
     * 编辑支出单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ExpenditureController/editExpenditureById")
    @ResponseBody
    public void editExpenditureById(InputObject inputObject, OutputObject outputObject) throws Exception{
        expenditureService.editExpenditureById(inputObject, outputObject);
    }

    /**
     * 删除支出单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ExpenditureController/deleteExpenditureById")
    @ResponseBody
    public void deleteExpenditureById(InputObject inputObject, OutputObject outputObject) throws Exception{
        expenditureService.deleteExpenditureById(inputObject, outputObject);
    }

    /**
     * 查看支出单详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ExpenditureController/queryExpenditureByDetail")
    @ResponseBody
    public void queryExpenditureByDetail(InputObject inputObject, OutputObject outputObject) throws Exception{
        expenditureService.queryExpenditureByDetail(inputObject, outputObject);
    }
    
    /**
     * 导出Excel
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ExpenditureController/queryMationToExcel")
    @ResponseBody
    public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception{
    	expenditureService.queryMationToExcel(inputObject, outputObject);
    }
    
}
