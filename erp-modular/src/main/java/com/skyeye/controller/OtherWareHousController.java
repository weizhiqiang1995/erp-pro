/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */

package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.OtherWareHousService;

/**
 *
 * @ClassName: OtherWareHousController
 * @Description: 其他入库控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 16:44
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/erp-pro Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
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
    
    /**
     * 其他入库信息编辑回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/OtherWareHousController/queryOtherWareHousToEditById")
    @ResponseBody
    public void queryOtherWareHousToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	otherWareHousService.queryOtherWareHousToEditById(inputObject, outputObject);
    }
    
    /**
     * 编辑其他入库信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/OtherWareHousController/editOtherWareHousMationById")
    @ResponseBody
    public void editOtherWareHousMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	otherWareHousService.editOtherWareHousMationById(inputObject, outputObject);
    }
    
    /**
     * 导出Excel
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/OtherWareHousController/queryMationToExcel")
    @ResponseBody
    public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception{
    	otherWareHousService.queryMationToExcel(inputObject, outputObject);
    }
	
}
