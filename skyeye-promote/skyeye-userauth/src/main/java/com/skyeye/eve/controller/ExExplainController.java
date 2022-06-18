/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.ExExplainService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @ClassName: ExExplainController
 * @Description: 部分功能的使用说明管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/15 18:46
 *   
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "功能使用说明", tags = "功能使用说明", modelName = "基础模块")
public class ExExplainController {
	
	@Autowired
	private ExExplainService exExplainService;

	/**
	 * 添加使用说明信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/ExExplainController/insertExExplainMation")
	public void insertExExplainMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		exExplainService.insertExExplainMation(inputObject, outputObject);
	}

	/**
	 * 编辑使用说明信息时进行回显
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/ExExplainController/queryExExplainMation")
	public void queryExExplainMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		exExplainService.queryExExplainMation(inputObject, outputObject);
	}

	/**
	 * 编辑使用说明信息
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/ExExplainController/editExExplainMationById")
	public void editExExplainMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		exExplainService.editExExplainMationById(inputObject, outputObject);
	}

	/**
	 * 获取使用说明信息供展示
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@ApiOperation(id = "queryExExplainMationToShow", value = "获取使用说明信息供展示", method = "GET", allUse = "2")
	@ApiImplicitParams({
		@ApiImplicitParam(id = "type", name = "type", value = "说明介绍类型", required = "required,num")})
	@RequestMapping("/post/ExExplainController/queryExExplainMationToShow")
	public void queryExExplainMationToShow(InputObject inputObject, OutputObject outputObject) throws Exception{
		exExplainService.queryExExplainMationToShow(inputObject, outputObject);
	}
	
}
