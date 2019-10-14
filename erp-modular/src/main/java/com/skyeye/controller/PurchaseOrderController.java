package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.PurchaseOrderService;

/**
 * 采购管理
 * @author Lenovo
 *
 */
@Controller
public class PurchaseOrderController {
	
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	/**
     * 获取采购单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PurchaseOrderController/queryPurchaseOrderToList")
    @ResponseBody
    public void queryPurchaseOrderToList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	purchaseOrderService.queryPurchaseOrderToList(inputObject, outputObject);
    }
    
    /**
     * 新增采购单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PurchaseOrderController/insertPurchaseOrderMation")
    @ResponseBody
    public void insertPurchaseOrderMation(InputObject inputObject, OutputObject outputObject) throws Exception{
    	purchaseOrderService.insertPurchaseOrderMation(inputObject, outputObject);
    }
	
}
