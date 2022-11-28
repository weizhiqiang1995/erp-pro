/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.service;

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

    void insertUploadFileToDataByUserId(InputObject inputObject, OutputObject outputObject);

    void queryEnclosureInfo(InputObject inputObject, OutputObject outputObject);

}
