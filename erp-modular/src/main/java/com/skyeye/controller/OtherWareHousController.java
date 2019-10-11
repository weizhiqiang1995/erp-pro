package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.OtherWareHousService;

/**
 * 其他入库
 * @author Lenovo
 *
 */
@Controller
public class OtherWareHousController {
	
	@Autowired
	private OtherWareHousService otherWareHousService;
	
	/**
     * 获取其他入库列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/OtherWareHousController/queryOtherWareHousToList")
    @ResponseBody
    public void queryOtherWareHousToList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	otherWareHousService.queryOtherWareHousToList(inputObject, outputObject);
    }
    
    /**
     * 新增其他入库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/OtherWareHousController/insertOtherWareHousMation")
    @ResponseBody
    public void insertOtherWareHousMation(InputObject inputObject, OutputObject outputObject) throws Exception{
    	otherWareHousService.insertOtherWareHousMation(inputObject, outputObject);
    }
	
}
