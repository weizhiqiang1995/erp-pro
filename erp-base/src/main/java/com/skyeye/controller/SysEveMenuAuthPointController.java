package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.SysEveMenuAuthPointService;

@Controller
public class SysEveMenuAuthPointController {
	
	@Autowired
	private SysEveMenuAuthPointService sysEveMenuAuthPointService;
	
	/**
	 * 
	     * @Title: querySysEveMenuAuthPointListByMenuId
	     * @Description: 获取菜单权限点列表根据菜单id
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointListByMenuId")
	@ResponseBody
	public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) throws Exception{
		sysEveMenuAuthPointService.querySysEveMenuAuthPointListByMenuId(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: insertSysEveMenuAuthPointMation
	     * @Description: 添加菜单权限点
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/SysEveMenuAuthPointController/insertSysEveMenuAuthPointMation")
	@ResponseBody
	public void insertSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) throws Exception{
		sysEveMenuAuthPointService.insertSysEveMenuAuthPointMation(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: querySysEveMenuAuthPointMationToEditById
	     * @Description: 编辑菜单权限点时进行回显
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointMationToEditById")
	@ResponseBody
	public void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception{
		sysEveMenuAuthPointService.querySysEveMenuAuthPointMationToEditById(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: editSysEveMenuAuthPointMationById
	     * @Description: 编辑菜单权限点
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/SysEveMenuAuthPointController/editSysEveMenuAuthPointMationById")
	@ResponseBody
	public void editSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		sysEveMenuAuthPointService.editSysEveMenuAuthPointMationById(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: deleteSysEveMenuAuthPointMationById
	     * @Description: 删除菜单权限点
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/SysEveMenuAuthPointController/deleteSysEveMenuAuthPointMationById")
	@ResponseBody
	public void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		sysEveMenuAuthPointService.deleteSysEveMenuAuthPointMationById(inputObject, outputObject);
	}
	
}
