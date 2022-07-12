/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.ehr.common.PointStaffQueryDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysStaffJobResumeDao {

    List<Map<String, Object>> queryAllSysStaffJobResumeList(Map<String, Object> params);

    int insertSysStaffJobResumeMation(Map<String, Object> map);

    Map<String, Object> querySysStaffJobResumeMationToEdit(@Param("id") String id);

    int editSysStaffJobResumeMationById(Map<String, Object> map);

    int deleteSysStaffJobResumeMationById(@Param("id") String id);

    List<Map<String, Object>> queryPointStaffSysStaffJobResumeList(PointStaffQueryDo pointStaffQuery);

}
