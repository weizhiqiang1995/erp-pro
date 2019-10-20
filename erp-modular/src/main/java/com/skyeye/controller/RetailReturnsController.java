package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.RetailReturnsService;

/**
 * @Author 卫志强
 * @Description 零售退货
 * @Date 2019/10/16 15:32
 */
@Controller
public class RetailReturnsController {
	
	@Autowired
	private RetailReturnsService retailReturnsService;
	
	/**
     * 获取零售退货列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RetailReturnsController/queryRetailReturnsToList")
    @ResponseBody
    public void queryRetailReturnsToList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	retailReturnsService.queryRetailReturnsToList(inputObject, outputObject);
    }
	
    /**
     * 新增零售退货信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RetailReturnsController/insertRetailReturnsMation")
    @ResponseBody
    public void insertRetailReturnsMation(InputObject inputObject, OutputObject outputObject) throws Exception{
    	retailReturnsService.insertRetailReturnsMation(inputObject, outputObject);
    }
    
    /**
     * 编辑零售退货信息时进行回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RetailReturnsController/queryRetailReturnsMationToEditById")
    @ResponseBody
    public void queryRetailReturnsMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	retailReturnsService.queryRetailReturnsMationToEditById(inputObject, outputObject);
    }
    
    /**
     * 编辑零售退货信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/RetailReturnsController/editRetailReturnsMationById")
    @ResponseBody
    public void editRetailReturnsMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	retailReturnsService.editRetailReturnsMationById(inputObject, outputObject);
    }
    
}
