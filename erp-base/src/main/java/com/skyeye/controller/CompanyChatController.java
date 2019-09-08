package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.CompanyChatService;

@Controller
public class CompanyChatController {
	
	@Autowired
	private CompanyChatService companyChatService;
	
	/**
	 * 
	     * @Title: getList
	     * @Description: 获取好友列表，群聊信息，个人信息
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CompanyChatController/getList")
	@ResponseBody
	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception{
		companyChatService.getList(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: editUserSignByUserId
	     * @Description: 编辑签名
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CompanyChatController/editUserSignByUserId")
	@ResponseBody
	public void editUserSignByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		companyChatService.editUserSignByUserId(inputObject, outputObject);
	}
	
}
