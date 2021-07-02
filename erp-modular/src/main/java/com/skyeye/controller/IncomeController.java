/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/erp-pro
 ******************************************************************************/

package com.skyeye.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @ClassName: IncomeController
 * @Description: 收入单控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/2 21:35
 *   
 * @Copyright: 2021 https://gitee.com/doc_wei01/erp-pro Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
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
     * 查询收入单用于数据回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/queryIncomeToEditById")
    @ResponseBody
    public void queryIncomeToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
        incomeService.queryIncomeToEditById(inputObject, outputObject);
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
    
    /**
     * 导出Excel
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/IncomeController/queryMationToExcel")
    @ResponseBody
    public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception{
    	incomeService.queryMationToExcel(inputObject, outputObject);
    }
    
}
