package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.PurchaseOutService;

/**
 * @Author 卫志强
 * @Description 采购退货
 * @Date 2019/10/16 15:32
 */
@Controller
public class PurchaseOutController {
	
	@Autowired
	private PurchaseOutService purchaseOutService;
	
	/**
     * 获取采购退货列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PurchaseOutController/queryPurchaseOutToList")
    @ResponseBody
    public void queryPurchaseOutToList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	purchaseOutService.queryPurchaseOutToList(inputObject, outputObject);
    }
	
    /**
     * 新增采购退货信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PurchaseOutController/insertPurchaseOutMation")
    @ResponseBody
    public void insertPurchaseOutMation(InputObject inputObject, OutputObject outputObject) throws Exception{
    	purchaseOutService.insertPurchaseOutMation(inputObject, outputObject);
    }
    
    /**
     * 编辑采购退货信息时进行回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PurchaseOutController/queryPurchaseOutMationToEditById")
    @ResponseBody
    public void queryPurchaseOutMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	purchaseOutService.queryPurchaseOutMationToEditById(inputObject, outputObject);
    }
    
    /**
     * 编辑采购退货信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PurchaseOutController/editPurchaseOutMationById")
    @ResponseBody
    public void editPurchaseOutMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	purchaseOutService.editPurchaseOutMationById(inputObject, outputObject);
    }
    
}
