/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.dao;

import com.skyeye.eve.dao.SkyeyeBaseMapper;
import com.skyeye.eve.entity.organization.jobscore.JobScoreField;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CompanyJobScoreFieldDao extends SkyeyeBaseMapper<JobScoreField> {

    int deleteCompanyJobScoreFieldByJobScoreId(@Param("jobScoreIdList") List<String> jobScoreIdList);

}
