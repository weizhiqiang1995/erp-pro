/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 */

package com.skyeye.eve.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysStaffLanguageLevelDao {

    List<Map<String, Object>> querySysStaffLanguageLevelList(Map<String, Object> params);

    Map<String, Object> querySysStaffLanguageLevelByName(Map<String, Object> params);

    int insertSysStaffLanguageLevelMation(Map<String, Object> params);

    Map<String, Object> querySysStaffLanguageLevelById(@Param("id") String id);

    Map<String, Object> querySysStaffLanguageLevelByNameAndId(Map<String, Object> params);

    int editSysStaffLanguageLevelMationById(Map<String, Object> params);

    Map<String, Object> querySysStaffLanguageLevelStateById(String id);

    void editSysStaffLanguageLevelStateById(@Param("id") String id, @Param("state") int state);

    List<Map<String, Object>> querySysStaffLanguageLevelUpMation(Map<String, Object> params);
}
