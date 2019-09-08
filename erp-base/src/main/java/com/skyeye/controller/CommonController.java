package com.skyeye.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.service.CommonService;

@Controller
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 
	     * @Title: uploadFile
	     * @Description: 上传文件
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/uploadFile")
	@ResponseBody
	public void uploadFile(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.uploadFile(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: uploadFileBase64
	     * @Description: 上传文件Base64
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/uploadFileBase64")
	@ResponseBody
	public void uploadFileBase64(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.uploadFileBase64(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: downloadFileByJsonData
	     * @Description: 代码生成器生成下载文件
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/downloadFileByJsonData")
	@ResponseBody
	public void downloadFileByJsonData(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.downloadFileByJsonData(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: querySysWinMationById
	     * @Description: 获取win系统桌列表信息供展示
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/querySysWinMationById")
	@ResponseBody
	public void querySysWinMationById(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.querySysWinMationById(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: queryAllPeopleToTree
	     * @Description: 人员选择获取所有公司和人
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/queryAllPeopleToTree")
	@ResponseBody
	public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.queryAllPeopleToTree(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: queryCompanyPeopleToTreeByUserBelongCompany
	     * @Description: 人员选择根据当前用户所属公司获取这个公司的人
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/queryCompanyPeopleToTreeByUserBelongCompany")
	@ResponseBody
	public void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.queryCompanyPeopleToTreeByUserBelongCompany(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: queryDepartmentPeopleToTreeByUserBelongDepartment
	     * @Description: 人员选择根据当前用户所属公司获取这个公司部门展示的人
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/queryDepartmentPeopleToTreeByUserBelongDepartment")
	@ResponseBody
	public void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.queryDepartmentPeopleToTreeByUserBelongDepartment(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: queryJobPeopleToTreeByUserBelongJob
	     * @Description: 人员选择根据当前用户所属公司获取这个公司岗位展示的人
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/queryJobPeopleToTreeByUserBelongJob")
	@ResponseBody
	public void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.queryJobPeopleToTreeByUserBelongJob(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: querySimpleDepPeopleToTreeByUserBelongSimpleDep
	     * @Description: 人员选择根据当前用户所属公司获取这个公司同级部门展示的人
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/querySimpleDepPeopleToTreeByUserBelongSimpleDep")
	@ResponseBody
	public void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.querySimpleDepPeopleToTreeByUserBelongSimpleDep(inputObject, outputObject);
	}
	
	/**
	 * 
	     * @Title: queryTalkGroupUserListByUserId
	     * @Description: 根据聊天组展示用户
	     * @param @param inputObject
	     * @param @param outputObject
	     * @param @throws Exception    参数
	     * @return void    返回类型
	     * @throws
	 */
	@RequestMapping("/post/CommonController/queryTalkGroupUserListByUserId")
	@ResponseBody
	public void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception{
		commonService.queryTalkGroupUserListByUserId(inputObject, outputObject);
	}

}
