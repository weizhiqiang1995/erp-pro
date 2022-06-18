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
import com.skyeye.eve.service.SystemFoundationSettingsService;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @ClassName: SystemFoundationSettingsController
 * @Description: 系统基础设置控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/6/6 22:39
 *
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "系统基础设置", tags = "系统基础设置", modelName = "系统公共模块")
public class SystemFoundationSettingsController {

	@Autowired
	private SystemFoundationSettingsService systemFoundationSettingsService;

	/**
	 * 获取系统基础设置
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@ApiOperation(id = "sysfdsettings001", value = "获取系统基础设置", method = "GET", allUse = "2")
	@RequestMapping("/post/SystemFoundationSettingsController/querySystemFoundationSettingsList")
	public void querySystemFoundationSettingsList(InputObject inputObject, OutputObject outputObject) throws Exception{
		systemFoundationSettingsService.querySystemFoundationSettingsList(inputObject, outputObject);
	}

	/**
	 * 编辑系统基础设置
	 *
	 * @param inputObject
	 * @param outputObject
	 * @throws Exception
	 */
	@RequestMapping("/post/SystemFoundationSettingsController/editSystemFoundationSettings")
	public void editSystemFoundationSettings(InputObject inputObject, OutputObject outputObject) throws Exception{
		systemFoundationSettingsService.editSystemFoundationSettings(inputObject, outputObject);
	}

}
