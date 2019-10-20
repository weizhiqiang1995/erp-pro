package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 奈何繁华如云烟
 * @Description 收入单
 * @Date 2019/10/20 10:22
 */
@Controller
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    /**
     * 查询收入单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/queryIncomeByList")
    @ResponseBody
    public void queryIncomeByList(InputObject inputObject, OutputObject outputObject) throws Exception{
        incomeService.queryIncomeByList(inputObject, outputObject);
    }

    /**
     * 添加收入单
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/insertIncome")
    @ResponseBody
    public void insertIncome(InputObject inputObject, OutputObject outputObject) throws Exception{
        incomeService.insertIncome(inputObject, outputObject);
    }

    /**
     * 查询单个收入单，用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/queryIncomeById")
    @ResponseBody
    public void queryIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception{
        incomeService.queryIncomeById(inputObject, outputObject);
    }

    /**
     * 编辑收入单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/editIncomeById")
    @ResponseBody
    public void editIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception{
        incomeService.editIncomeById(inputObject, outputObject);
    }

    /**
     * 删除收入单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/deleteIncomeById")
    @ResponseBody
    public void deleteIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception{
        incomeService.deleteIncomeById(inputObject, outputObject);
    }

    /**
     * 查看收入单详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/queryIncomeByDetail")
    @ResponseBody
    public void queryIncomeByDetail(InputObject inputObject, OutputObject outputObject) throws Exception{
        incomeService.queryIncomeByDetail(inputObject, outputObject);
    }
}
