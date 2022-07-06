/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/
package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface FileConsoleService {

    void queryFileFolderByUserId(InputObject inputObject, OutputObject outputObject);

    void insertFileFolderByUserId(InputObject inputObject, OutputObject outputObject);

    void queryFilesListByFolderId(InputObject inputObject, OutputObject outputObject);

    void deleteFileFolderById(InputObject inputObject, OutputObject outputObject);

    void editFileFolderById(InputObject inputObject, OutputObject outputObject);

    void insertUploadFileByUserId(InputObject inputObject, OutputObject outputObject);

    void insertUploadFileChunksByUserId(InputObject inputObject, OutputObject outputObject);

    void queryUploadFileChunksByChunkMd5(InputObject inputObject, OutputObject outputObject);

    void queryUploadFilePathById(InputObject inputObject, OutputObject outputObject);

    void editUploadOfficeFileById(InputObject inputObject, OutputObject outputObject);

    void queryAllFileSizeByUserId(InputObject inputObject, OutputObject outputObject);

    void insertFileCatalogToRecycleById(InputObject inputObject, OutputObject outputObject);

    void queryFileRecycleBinByUserId(InputObject inputObject, OutputObject outputObject);

    void deleteFileRecycleBinById(InputObject inputObject, OutputObject outputObject);

    void insertFileToShareById(InputObject inputObject, OutputObject outputObject);

    void queryShareFileListByUserId(InputObject inputObject, OutputObject outputObject);

    void deleteShareFileById(InputObject inputObject, OutputObject outputObject);

    void queryShareFileMationById(InputObject inputObject, OutputObject outputObject);

    void queryShareFileMationCheckById(InputObject inputObject, OutputObject outputObject);

    void queryShareFileBaseMationById(InputObject inputObject, OutputObject outputObject);

    void queryShareFileListByParentId(InputObject inputObject, OutputObject outputObject);

    void insertShareFileListToSave(InputObject inputObject, OutputObject outputObject);

    void queryFileToShowById(InputObject inputObject, OutputObject outputObject);

    void insertWordFileToService(InputObject inputObject, OutputObject outputObject);

    void insertExcelFileToService(InputObject inputObject, OutputObject outputObject);

    void insertPPTFileToService(InputObject inputObject, OutputObject outputObject);

    void insertTXTFileToService(InputObject inputObject, OutputObject outputObject);

    void insertHtmlFileToService(InputObject inputObject, OutputObject outputObject);

    void insertDuplicateCopyToService(InputObject inputObject, OutputObject outputObject);

    void queryFileMationById(InputObject inputObject, OutputObject outputObject);

    void insertFileMationToPackageToFolder(InputObject inputObject, OutputObject outputObject);

    void insertFileMationPackageToFolder(InputObject inputObject, OutputObject outputObject);

    void insertPasteCopyToService(InputObject inputObject, OutputObject outputObject);

    void insertPasteCutToService(InputObject inputObject, OutputObject outputObject);

    void queryOfficeUpdateTimeToKey(InputObject inputObject, OutputObject outputObject);

    void queryFileNumStatistics(InputObject inputObject, OutputObject outputObject);

    void insertFileMationToPackageDownload(InputObject inputObject, OutputObject outputObject);

}
