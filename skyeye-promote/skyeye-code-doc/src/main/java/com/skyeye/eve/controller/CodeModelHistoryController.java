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
import com.skyeye.eve.service.CodeModelHistoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeModelHistoryController {
	
	@Autowired
	private CodeModelHistoryService codeModelHistoryService;

	/**
	 * 获取模板生成历史列表
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelHistoryController/queryCodeModelHistoryList")
	public void queryCodeModelHistoryList(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelHistoryService.queryCodeModelHistoryList(inputObject, outputObject);
	}

	/**
	 * 重新生成文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelHistoryController/insertCodeModelHistoryCreate")
	public void insertCodeModelHistoryCreate(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelHistoryService.insertCodeModelHistoryCreate(inputObject, outputObject);
	}

	/**
	 * 下载文件
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/CodeModelHistoryController/downloadCodeModelHistory")
	public void downloadCodeModelHistory(InputObject inputObject, OutputObject outputObject) throws Exception{
		codeModelHistoryService.downloadCodeModelHistory(inputObject, outputObject);
	}
	
}