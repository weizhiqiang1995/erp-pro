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
import com.skyeye.service.MaterialCategoryService;

@Controller
public class MaterialCategoryController {
	
	@Autowired
	private MaterialCategoryService materialCategoryService;
	
	/**
     * 获取产品类型信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialCategoryController/queryMaterialCategoryList")
    @ResponseBody
    public void queryMaterialCategoryList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialCategoryService.queryMaterialCategoryList(inputObject, outputObject);
    }
    
    /**
	 * 
     * @Description: 添加产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
     * @throws
	 */
	@RequestMapping("/post/MaterialCategoryController/insertMaterialCategoryMation")
	@ResponseBody
	public void insertMaterialCategoryMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.insertMaterialCategoryMation(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 删除产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/deleteMaterialCategoryById")
	@ResponseBody
	public void deleteMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.deleteMaterialCategoryById(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 启用产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/updateUpMaterialCategoryById")
	@ResponseBody
	public void updateUpMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.updateUpMaterialCategoryById(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 禁用产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/updateDownMaterialCategoryById")
	@ResponseBody
	public void updateDownMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.updateDownMaterialCategoryById(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 通过id查找对应的产品类型信息
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/selectMaterialCategoryById")
	@ResponseBody
	public void selectMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.selectMaterialCategoryById(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 编辑产品类型
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/editMaterialCategoryMationById")
	@ResponseBody
	public void editMaterialCategoryMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.editMaterialCategoryMationById(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 产品类型上移
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/editMaterialCategoryMationOrderNumUpById")
	@ResponseBody
	public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.editMaterialCategoryMationOrderNumUpById(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 产品类型下移
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/editMaterialCategoryMationOrderNumDownById")
	@ResponseBody
	public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.editMaterialCategoryMationOrderNumDownById(inputObject, outputObject);
	}

	/**
	 * 
     * @Description: 获取已经上线的一级类型列表
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/queryFirstMaterialCategoryUpStateList")
	@ResponseBody
	public void queryFirstMaterialCategoryUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.queryFirstMaterialCategoryUpStateList(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 获取所有的一级类型列表
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/queryAllFirstMaterialCategoryStateList")
	@ResponseBody
	public void queryAllFirstMaterialCategoryStateList(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.queryAllFirstMaterialCategoryStateList(inputObject, outputObject);
	}
	
	/**
	 * 
     * @Description: 获取上线的一级类型对应的上线的二级类型列表
     * @param @param inputObject
     * @param @param outputObject
     * @param @throws Exception
	 */
	@RequestMapping("/post/MaterialCategoryController/querySecondMaterialCategoryUpStateList")
	@ResponseBody
	public void querySecondMaterialCategoryUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception{
		materialCategoryService.querySecondMaterialCategoryUpStateList(inputObject, outputObject);
	}
	
}
