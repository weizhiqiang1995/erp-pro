package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.PurchasePutService;

/**
 * 采购入库
 * @author Lenovo
 *
 */
@Controller
public class PurchasePutController {
	
	@Autowired
	private PurchasePutService purchasePutService;
	
	/**
     * 获取采购入库列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/PurchasePutController/queryPurchasePutToList")
    @ResponseBody
    public void queryPurchasePutToList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	purchasePutService.queryPurchasePutToList(inputObject, outputObject);
    }
	
}
