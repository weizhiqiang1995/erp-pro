/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysStaffLanguageDao {

    List<Map<String, Object>> queryAllSysStaffLanguageList(Map<String, Object> params);

    int insertSysStaffLanguageMation(Map<String, Object> map);

    Map<String, Object> querySysStaffLanguageMationToEdit(@Param("id") String id);

    int editSysStaffLanguageMationById(Map<String, Object> map);

    int deleteSysStaffLanguageMationById(@Param("id") String id);

    List<Map<String, Object>> queryPointStaffSysStaffLanguageList(Map<String, Object> params);

}
