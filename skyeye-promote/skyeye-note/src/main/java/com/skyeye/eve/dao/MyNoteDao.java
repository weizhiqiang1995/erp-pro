/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MyNoteDao {

    int insertFileFolderByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryFileFolderByUserIdAndParentId(Map<String, Object> map);

    Map<String, Object> queryFolderMationById(@Param("folderId") String folderId);

    int deleteFileFolderById(Map<String, Object> map);

    int deleteFilesByFolderId(Map<String, Object> map);

    int deleteFolderChildByFolderId(Map<String, Object> map);

    int deleteNoteContentById(Map<String, Object> map);

    int editFileFolderById(Map<String, Object> map);

    int editNoteContentNameById(Map<String, Object> map);

    List<Map<String, Object>> queryMyNoteListNewByUserId(Map<String, Object> map);

    int insertMyNoteContentByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryFileAndContentListByFolderId(Map<String, Object> map);

    Map<String, Object> queryMyNoteContentMationById(Map<String, Object> map);

    int editMyNoteContentById(Map<String, Object> map);

    List<Map<String, Object>> queryFileFolderListByList(List<Map<String, Object>> folderBeans);

    int deleteFileFolderListByList(List<Map<String, Object>> folderBeans);

    List<Map<String, Object>> queryFileListByList(List<Map<String, Object>> folderNew);

    int deleteFileListByList(List<Map<String, Object>> folderNew);

    int insertFileFolderListByList(List<Map<String, Object>> folderNew);

    int insertFileListByList(List<Map<String, Object>> fileNew);

    List<Map<String, Object>> queryTreeToMoveByUserId(Map<String, Object> map);

    Map<String, Object> queryShareNoteById(@Param("fileId") String fileId);

    List<Map<String, Object>> queryAllFolderListByFolderId(@Param("folderId") String folderId);

    List<Map<String, Object>> queryAllFileListByFolderList(List<Map<String, Object>> beans);

    int editNoteToMoveById(@Param("fileId") String fileId, @Param("parentId") String parentId, @Param("userId") String userId);

}
