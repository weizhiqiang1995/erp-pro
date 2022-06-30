/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dao;

import com.skyeye.eve.entity.mq.JobMateQueryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JobMateMationDao {

    List<Map<String, Object>> queryJobMateMationByBigTypeList(JobMateQueryDO jobMateQuery);

    int insertJobMation(Map<String, Object> parentJob);

    Map<String, Object> queryJobMationByJobId(@Param("jobId") String jobId);

    int editJobMationByJobId(@Param("jobId") String jobId, @Param("status") String status,
                             @Param("responseBody") String responseBody, @Param("complateTime") String complateTime);

    List<Map<String, Object>> queryNoComChildJobMationByJobId(@Param("jobId") String jobId);

    List<Map<String, Object>> queryFailChildJobMationByJobId(@Param("jobId") String jobId);

    int editJobRequestBodyMation(Map<String, Object> parentJob);

}
