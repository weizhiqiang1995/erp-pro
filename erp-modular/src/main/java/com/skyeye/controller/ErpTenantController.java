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
import com.skyeye.service.ErpTenantService;

@Controller
public class ErpTenantController {
	
	@Autowired
	private ErpTenantService erpTenantService;
	
	/**
	 * 
	     * @Title: insertErpTenantGroupMation
	     * @Description: 新增租户组
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/insertErpTenantGroupMation")
	@ResponseBody
	public void insertErpTenantGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.insertErpTenantGroupMation(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: selectAllErpTenantGroupMation
	     * @Description: 遍历所有的租户组
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/selectAllErpTenantGroupMation")
	@ResponseBody
	public void selectAllErpTenantGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.selectAllErpTenantGroupMation(inputObject, outputObject);
	}

	/**
	 * 
	     * @Title: insertErpTenantGroupUserByGroupId
	     * @Description: 给租户组新增租户
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/insertErpTenantGroupUserByGroupId")
	@ResponseBody
	public void insertErpTenantGroupUserByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.insertErpTenantGroupUserByGroupId(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: editErpTenantGroupNameByGroupId
	     * @Description: 编辑租户组名
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/editErpTenantGroupNameByGroupId")
	@ResponseBody
	public void editErpTenantGroupNameByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.editErpTenantGroupNameByGroupId(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: deleteErpTenantGroupByGroupId
	     * @Description: 删除租户组
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/deleteErpTenantGroupByGroupId")
	@ResponseBody
	public void deleteErpTenantGroupByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.deleteErpTenantGroupByGroupId(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: deleteErpTenantGroupUserByGroupIdAndUserId
	     * @Description: 移除租户组中的某个租户
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/deleteErpTenantGroupUserByGroupIdAndUserId")
	@ResponseBody
	public void deleteErpTenantGroupUserByGroupIdAndUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.deleteErpTenantGroupUserByGroupIdAndUserId(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: selectUserInfoOnErpTenantGroup
	     * @Description: 展示租户组的租户信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/selectUserInfoOnErpTenantGroup")
	@ResponseBody
	public void selectUserInfoOnErpTenantGroup(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.selectUserInfoOnErpTenantGroup(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: deleteAllErpTenantGroupUserByGroupId
	     * @Description: 一键移除指定租户组下的所有租户
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/ErpTenantController/deleteAllErpTenantGroupUserByGroupId")
	@ResponseBody
	public void deleteAllErpTenantGroupUserByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception{
		erpTenantService.deleteAllErpTenantGroupUserByGroupId(inputObject, outputObject);
	}
	
}
