/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEnclosureService {

    void querySysEnclosureListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void insertSysEnclosureMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void querySysEnclosureFirstTypeListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryThisFolderChilsByFolderId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void querySysEnclosureMationByUserIdToEdit(InputObject inputObject, OutputObject outputObject) throws Exception;

    void editSysEnclosureMationByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject) throws Exception;

    void querySysEnclosureListToTreeByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject) throws Exception;

    void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void insertUploadFileToDataByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryEnclosureInfo(InputObject inputObject, OutputObject outputObject) throws Exception;

}
