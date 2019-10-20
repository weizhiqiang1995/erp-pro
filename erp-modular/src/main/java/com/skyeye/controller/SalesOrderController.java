package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.SalesOrderService;

/**
 * 销售管理
 * @author Lenovo
 *
 */
@Controller
public class SalesOrderController {
	
	@Autowired
	private SalesOrderService salesOrderService;
	
	/**
     * 获取销售单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/querySalesOrderToList")
    @ResponseBody
    public void querySalesOrderToList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.querySalesOrderToList(inputObject, outputObject);
    }
    
    /**
     * 新增销售单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/insertSalesOrderMation")
    @ResponseBody
    public void insertSalesOrderMation(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.insertSalesOrderMation(inputObject, outputObject);
    }
    
    /**
     * 删除销售单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/deleteSalesOrderMationById")
    @ResponseBody
    public void deleteSalesOrderMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.deleteSalesOrderMationById(inputObject, outputObject);
    }
	
    /**
     * 销售单信息编辑回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/querySalesOrderToEditById")
    @ResponseBody
    public void querySalesOrderToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.querySalesOrderToEditById(inputObject, outputObject);
    }
    
    /**
     * 编辑销售单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/editSalesOrderMationById")
    @ResponseBody
    public void editSalesOrderMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.editSalesOrderMationById(inputObject, outputObject);
    }
    
    /**
     * 销售单信息提交审核
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/editSalesOrderStateToSubExamineById")
    @ResponseBody
    public void editSalesOrderStateToSubExamineById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.editSalesOrderStateToSubExamineById(inputObject, outputObject);
    }
    
    /**
     * 销售单信息审核
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/editSalesOrderStateToExamineById")
    @ResponseBody
    public void editSalesOrderStateToExamineById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.editSalesOrderStateToExamineById(inputObject, outputObject);
    }
    
    /**
     * 销售单信息转销售出库回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/querySalesOrderToTurnPutById")
    @ResponseBody
    public void querySalesOrderToTurnPutById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.querySalesOrderToTurnPutById(inputObject, outputObject);
    }
    
    /**
     * 销售单信息转销售出库
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SalesOrderController/insertSalesOrderToTurnPut")
    @ResponseBody
    public void insertSalesOrderToTurnPut(InputObject inputObject, OutputObject outputObject) throws Exception{
    	salesOrderService.insertSalesOrderToTurnPut(inputObject, outputObject);
    }
    
}
