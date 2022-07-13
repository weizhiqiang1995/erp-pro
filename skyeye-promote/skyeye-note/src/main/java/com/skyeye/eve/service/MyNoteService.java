/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface MyNoteService {

    void queryFileMyNoteByUserId(InputObject inputObject, OutputObject outputObject);

    void insertFileMyNoteByUserId(InputObject inputObject, OutputObject outputObject);

    void deleteFileFolderById(InputObject inputObject, OutputObject outputObject);

    void editFileFolderById(InputObject inputObject, OutputObject outputObject);

    void queryMyNoteListNewByUserId(InputObject inputObject, OutputObject outputObject);

    void insertMyNoteContentByUserId(InputObject inputObject, OutputObject outputObject);

    void queryFileAndContentListByFolderId(InputObject inputObject, OutputObject outputObject);

    void queryMyNoteContentMationById(InputObject inputObject, OutputObject outputObject);

    void editMyNoteContentById(InputObject inputObject, OutputObject outputObject);

    void editFileToDragById(InputObject inputObject, OutputObject outputObject);

    void editNoteToMoveById(InputObject inputObject, OutputObject outputObject);

    void queryTreeToMoveByUserId(InputObject inputObject, OutputObject outputObject);

    void queryShareNoteById(InputObject inputObject, OutputObject outputObject);

    void outputNoteIsZipJob(InputObject inputObject, OutputObject outputObject);

}
