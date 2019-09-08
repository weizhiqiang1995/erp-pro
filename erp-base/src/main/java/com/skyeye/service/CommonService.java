package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CommonService {

	public void uploadFile(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void uploadFileBase64(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void downloadFileByJsonData(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySysWinMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

}
