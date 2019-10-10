package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.MaterialService;

@Controller
public class MaterialController {
	
	@Autowired
	private MaterialService materialService;
	
	/**
     * 获取产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/queryMaterialListByUserId")
    @ResponseBody
    public void queryMaterialListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.queryMaterialListByUserId(inputObject, outputObject);
    }
    
    /**
     * 获取租户拥有的产品计量单位
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/queryMaterialUnitListToSelectByUserId")
    @ResponseBody
    public void queryMaterialUnitListToSelectByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.queryMaterialUnitListToSelectByUserId(inputObject, outputObject);
    }
    
    /**
     * 新增产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/insertMaterialMation")
    @ResponseBody
    public void insertMaterialMation(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.insertMaterialMation(inputObject, outputObject);
    }
    
    /**
     * 禁用产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/editMaterialEnabledToDisablesById")
    @ResponseBody
    public void editMaterialEnabledToDisablesById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.editMaterialEnabledToDisablesById(inputObject, outputObject);
    }
    
    /**
     * 启用产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/editMaterialEnabledToEnablesById")
    @ResponseBody
    public void editMaterialEnabledToEnablesById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.editMaterialEnabledToEnablesById(inputObject, outputObject);
    }
    
    /**
     * 删除产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/deleteMaterialMationById")
    @ResponseBody
    public void deleteMaterialMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.deleteMaterialMationById(inputObject, outputObject);
    }
    
    /**
     * 产品信息详情
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/queryMaterialMationDetailsById")
    @ResponseBody
    public void queryMaterialMationDetailsById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.queryMaterialMationDetailsById(inputObject, outputObject);
    }
    
    /**
     * 编辑产品信息进行回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/queryMaterialMationToEditById")
    @ResponseBody
    public void queryMaterialMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.queryMaterialMationToEditById(inputObject, outputObject);
    }
    
    /**
     * 编辑产品信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/MaterialController/editMaterialMationById")
    @ResponseBody
    public void editMaterialMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	materialService.editMaterialMationById(inputObject, outputObject);
    }
	
}
