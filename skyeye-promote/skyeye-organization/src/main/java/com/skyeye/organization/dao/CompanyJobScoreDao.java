/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CompanyJobScoreDao {

    List<Map<String, Object>> queryCompanyJobScoreList(Map<String, Object> map);

    Map<String, Object> queryCompanyJobScoreByNameAndNotId(@Param("nameCn") String nameCn, @Param("jobId") String jobId, @Param("notId") String id);

    int insertCompanyJobScoreMation(Map<String, Object> map);

    Map<String, Object> queryCompanyJobScoreMationById(@Param("id") String id);

    int editCompanyJobScoreMationById(Map<String, Object> map);

    int editCompanyJobScoreStateMationById(@Param("id") String id, @Param("state") int state);

    int editCompanyJobScoreStateMationByJobId(@Param("jobId") String jobId, @Param("state") int state);

    List<Map<String, Object>> queryEnableCompanyJobScoreList(Map<String, Object> map);
}
