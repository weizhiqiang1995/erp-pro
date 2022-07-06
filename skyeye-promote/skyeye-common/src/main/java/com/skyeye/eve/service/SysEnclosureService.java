/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

import java.util.List;
import java.util.Map;

public interface SysEnclosureService {

    void querySysEnclosureListByUserId(InputObject inputObject, OutputObject outputObject);

    void insertSysEnclosureMationByUserId(InputObject inputObject, OutputObject outputObject);

    void querySysEnclosureFirstTypeListByUserId(InputObject inputObject, OutputObject outputObject);

    void queryThisFolderChilsByFolderId(InputObject inputObject, OutputObject outputObject);

    void querySysEnclosureMationByUserIdToEdit(InputObject inputObject, OutputObject outputObject);

    void editSysEnclosureMationByUserId(InputObject inputObject, OutputObject outputObject);

    void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject);

    void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject);

    void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject);

    void querySysEnclosureListToTreeByUserId(InputObject inputObject, OutputObject outputObject);

    void queryAllPeopleToTree(InputObject inputObject, OutputObject outputObject);

    void queryCompanyPeopleToTreeByUserBelongCompany(InputObject inputObject, OutputObject outputObject);

    void queryDepartmentPeopleToTreeByUserBelongDepartment(InputObject inputObject, OutputObject outputObject);

    void queryJobPeopleToTreeByUserBelongJob(InputObject inputObject, OutputObject outputObject);

    void querySimpleDepPeopleToTreeByUserBelongSimpleDep(InputObject inputObject, OutputObject outputObject);

    void queryTalkGroupUserListByUserId(InputObject inputObject, OutputObject outputObject);

    void insertUploadFileToDataByUserId(InputObject inputObject, OutputObject outputObject);

    void queryEnclosureInfo(InputObject inputObject, OutputObject outputObject);

    /**
     * 根据ids(逗号隔开)获取多个附件信息
     *
     * @param enclosureInfoIds ids(逗号隔开)
     * @return
     */
    List<Map<String, Object>> queryEnclosureInfoListByIds(String enclosureInfoIds);

}
