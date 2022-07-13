/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.diskcloud.filerecycle.FileRecycleQueryDo;
import com.skyeye.eve.entity.diskcloud.fileshare.FileShareQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FileConsoleDao {

    int insertFileFolderByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryFileFolderByUserIdAndParentId(Map<String, Object> map);

    List<Map<String, Object>> queryFilesListByFolderId(Map<String, Object> map);

    int deleteFileFolderById(String id);

    int editFileFolderById(Map<String, Object> map);

    int insertUploadFileByUserId(Map<String, Object> map);

    int editFilePaperNameById(Map<String, Object> map);

    int deleteFilePaperById(String id);

    Map<String, Object> queryFilePaperPathById(String id);

    Map<String, Object> queryUploadFileChunksByChunkMd5(Map<String, Object> map);

    List<Map<String, Object>> queryUploadFileChunksByMd5(Map<String, Object> map);

    int deleteUploadFileChunksByMd5(Map<String, Object> map);

    Map<String, Object> queryUploadFilePathById(Map<String, Object> map);

    /**
     * 根据文件夹id获取文件夹信息
     *
     * @param folderId 文件夹id
     * @return 文件夹信息
     */
    Map<String, Object> queryFolderMationById(@Param("folderId") String folderId);

    /**
     * 根据文件夹id获取该文件夹下的所有文件（包含子文件夹中的文件）
     *
     * @param id 文件夹id
     * @return 该文件夹下的所有文件
     */
    List<Map<String, Object>> queryFilesByFolderId(@Param("id") String id);

    int deleteFilesByFolderId(String id);

    int deleteFolderChildByFolderId(String id);

    int deleteUploadFileChunksByChunkMd5(Map<String, Object> map);

    Map<String, Object> queryUploadFilePathByKey(Map<String, Object> map);

    Map<String, Object> queryAllFileSizeByUserId(Map<String, Object> map);

    Map<String, Object> queryFileCatalogByUserIdAndId(Map<String, Object> map);

    int insertFileCatalogToRecycleById(Map<String, Object> map);

    List<Map<String, Object>> queryFileRecycleBinByUserId(FileRecycleQueryDo fileRecycleQuery);

    Map<String, Object> queryFileRecycleBinById(Map<String, Object> map);

    int deleteFileRecycleBinById(Map<String, Object> map);

    int updateFileFolderRecycleBinById(Map<String, Object> map);

    int updateFileRecycleBinById(Map<String, Object> map);

    Map<String, Object> queryThisFileCreaterByFileId(String id);

    int updateFileStateIsDeleteById(Map<String, Object> map);

    Map<String, Object> queryFileMationByIdAndUserId(Map<String, Object> map);

    int insertFileToShareById(Map<String, Object> map);

    List<Map<String, Object>> queryShareFileListByUserId(FileShareQueryDo fileShareQuery);

    int deleteShareFileById(Map<String, Object> map);

    Map<String, Object> queryShareFileMationById(Map<String, Object> map);

    Map<String, Object> queryShareFileMationCheckById(Map<String, Object> map);

    Map<String, Object> queryShareFileBaseMationById(Map<String, Object> map);

    List<Map<String, Object>> queryShareFileFirstListByParentId(Map<String, Object> map);

    List<Map<String, Object>> queryShareFileListByParentId(Map<String, Object> map);

    List<Map<String, Object>> queryShareFileFolderListByList(List<Map<String, Object>> folderBeans);

    List<Map<String, Object>> queryShareFileListByList(List<Map<String, Object>> folderNew);

    int insertShareFileFolderListByList(List<Map<String, Object>> folderNew);

    int insertShareFileListByList(List<Map<String, Object>> fileNew);

    List<Map<String, Object>> queryShareFileListByFileList(List<Map<String, Object>> fileBeans);

    Map<String, Object> quertWinFileOrFolderParentById(String id);

    Map<String, Object> queryFileToShowById(Map<String, Object> map);

    Map<String, Object> queryFileMationById(Map<String, Object> map);

    List<Map<String, Object>> queryToPackageFileFolderListByList(List<Map<String, Object>> folderBeans);

    List<Map<String, Object>> queryToPackageFileListByList(List<Map<String, Object>> folderNew);

    List<Map<String, Object>> queryToPackageFileListByFileList(List<Map<String, Object>> fileBeans);

    Map<String, Object> queryFilePackageMationById(Map<String, Object> map);

    int insertFolderByPackageAndUserId(List<Map<String, Object>> folderList);

    int insertFileByPackageAndUserId(List<Map<String, Object>> fileList);

    int deleteShareFileFolderListByList(List<Map<String, Object>> folderBeans);

    int deleteShareFileListByList(List<Map<String, Object>> folderNew);

    int deleteShareFileListByFileList(List<Map<String, Object>> fileBeans);

    int editFileUpdateTimeByKey(Map<String, Object> map);

    Map<String, Object> queryOfficeUpdateTimeToKey(Map<String, Object> map);

    Map<String, Object> queryAllNumFile(Map<String, Object> map);

    Map<String, Object> queryAllNumFileToday(Map<String, Object> map);

    Map<String, Object> queryAllNumFileThisWeek(Map<String, Object> map);

    List<Map<String, Object>> queryFileTypeNum(Map<String, Object> map);

    List<Map<String, Object>> queryFileStorageNum(Map<String, Object> map);

    List<Map<String, Object>> queryNewFileNum(Map<String, Object> map);

    List<Map<String, Object>> queryFileTypeNumSevenDay(Map<String, Object> map);

}
