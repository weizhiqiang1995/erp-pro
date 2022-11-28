/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysEnclosureDao {

    List<Map<String, Object>> querySysEnclosureFirstTypeListByUserId(Map<String, Object> map);

    List<Map<String, Object>> querySysEnclosureSecondTypeListByUserId(Map<String, Object> map);

    Map<String, Object> querySysEnclosureMationByUserIdAndParentId(Map<String, Object> map);

    int insertSysEnclosureMationByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryThisFolderChilsByFolderId(Map<String, Object> map);

    Map<String, Object> querySysEnclosureMationByUserIdToEdit(Map<String, Object> map);

    int editSysEnclosureFolderMationByUserId(Map<String, Object> map);

    int editSysEnclosureFileMationByUserId(Map<String, Object> map);

    int insertUploadFileByUserId(Map<String, Object> map);

    List<Map<String, Object>> querySysEnclosureListToTreeByUserId(Map<String, Object> map);

    List<Map<String, Object>> queryEnclosureInfo(@Param("enclosure") String enclosure);

}
