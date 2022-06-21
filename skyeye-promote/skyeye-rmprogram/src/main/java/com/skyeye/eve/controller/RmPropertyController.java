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
import com.skyeye.eve.service.RmPropertyService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RmPropertyController {
	
	@Autowired
	private RmPropertyService rmPropertyService;

	/**
	 * 获取小程序样式属性列表
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/RmPropertyController/queryRmPropertyList")
	public void queryRmPropertyList(InputObject inputObject, OutputObject outputObject) throws Exception{
		rmPropertyService.queryRmPropertyList(inputObject, outputObject);
	}

	/**
	 * 添加小程序样式属性信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/RmPropertyController/insertRmPropertyMation")
	public void insertRmPropertyMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		rmPropertyService.insertRmPropertyMation(inputObject, outputObject);
	}

	/**
	 * 删除小程序样式属性信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/RmPropertyController/deleteRmPropertyMationById")
	public void deleteRmPropertyMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		rmPropertyService.deleteRmPropertyMationById(inputObject, outputObject);
	}

	/**
	 * 编辑小程序样式属性信息时进行回显
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/RmPropertyController/queryRmPropertyMationToEditById")
	public void queryRmPropertyMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
		rmPropertyService.queryRmPropertyMationToEditById(inputObject, outputObject);
	}

	/**
	 * 编辑小程序样式属性信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/RmPropertyController/editRmPropertyMationById")
	public void editRmPropertyMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		rmPropertyService.editRmPropertyMationById(inputObject, outputObject);
	}

	/**
	 * 获取小程序样式属性供展示
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/RmPropertyController/queryRmPropertyListToShow")
	public void queryRmPropertyListToShow(InputObject inputObject, OutputObject outputObject) throws Exception{
		rmPropertyService.queryRmPropertyListToShow(inputObject, outputObject);
	}
	
}