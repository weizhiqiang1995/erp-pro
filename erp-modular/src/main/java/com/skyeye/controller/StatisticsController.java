package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.StatisticsService;

@Controller
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsService;
	
	/**
     * 入库明细
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/StatisticsController/queryWarehousingDetails")
    @ResponseBody
    public void queryWarehousingDetails(InputObject inputObject, OutputObject outputObject) throws Exception{
    	statisticsService.queryWarehousingDetails(inputObject, outputObject);
    }
	
}
