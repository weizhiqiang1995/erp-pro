package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.ErpCommonService;

@Controller
public class ErpCommonController {
	
	@Autowired
	private ErpCommonService erpCommonService;
	
	/**
     * 获取单据详情信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/ErpCommonController/queryDepotHeadDetailsMationById")
    @ResponseBody
    public void queryDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	erpCommonService.queryDepotHeadDetailsMationById(inputObject, outputObject);
    }
    
}
