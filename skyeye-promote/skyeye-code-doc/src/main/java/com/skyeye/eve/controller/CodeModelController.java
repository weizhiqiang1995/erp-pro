/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.CodeModelService;

@Controller
public class CodeModelController {
	
	@Autowired
	private CodeModelService codeModelService;

	/**
	 * 获取模板列表
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelController/queryCodeModelList")
	@ResponseBody
	public void queryCodeModelList(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelService.queryCodeModelList(inputObject, outputObject);
	}

	/**
	 * 新增模板列表
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelController/insertCodeModelMation")
	@ResponseBody
	public void insertCodeModelMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelService.insertCodeModelMation(inputObject, outputObject);
	}

	/**
	 * 删除模板信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelController/deleteCodeModelById")
	@ResponseBody
	public void deleteCodeModelById(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelService.deleteCodeModelById(inputObject, outputObject);
	}

	/**
	 * 编辑模板信息时进行回显
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelController/queryCodeModelMationToEditById")
	@ResponseBody
	public void queryCodeModelMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelService.queryCodeModelMationToEditById(inputObject, outputObject);
	}

	/**
	 * 编辑模板信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelController/editCodeModelMationById")
	@ResponseBody
	public void editCodeModelMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelService.editCodeModelMationById(inputObject, outputObject);
	}
	
}
