package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.AssemblySheetService;

/**
 * @Author 卫志强
 * @Description 组装单
 * @Date 2019/10/16 15:32
 */
@Controller
public class AssemblySheetController {
	
	@Autowired
	private AssemblySheetService assemblySheetService;
	
	/**
     * 获取组装单列表信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AssemblySheetController/queryAssemblySheetToList")
    @ResponseBody
    public void queryAssemblySheetToList(InputObject inputObject, OutputObject outputObject) throws Exception{
    	assemblySheetService.queryAssemblySheetToList(inputObject, outputObject);
    }
	
    /**
     * 新增组装单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AssemblySheetController/insertAssemblySheetMation")
    @ResponseBody
    public void insertAssemblySheetMation(InputObject inputObject, OutputObject outputObject) throws Exception{
    	assemblySheetService.insertAssemblySheetMation(inputObject, outputObject);
    }
    
    /**
     * 编辑组装单信息时进行回显
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AssemblySheetController/queryAssemblySheetMationToEditById")
    @ResponseBody
    public void queryAssemblySheetMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	assemblySheetService.queryAssemblySheetMationToEditById(inputObject, outputObject);
    }
    
    /**
     * 编辑组装单信息
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/AssemblySheetController/editAssemblySheetMationById")
    @ResponseBody
    public void editAssemblySheetMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
    	assemblySheetService.editAssemblySheetMationById(inputObject, outputObject);
    }
    
}
