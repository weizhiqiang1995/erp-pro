/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.eve.entity.organization.jobscore.JobScoreField;

import java.util.List;

/**
 * @ClassName: CompanyJobScoreFieldService
 * @Description: 职位定级薪资字段设计范围服务接口
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/25 23:00
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CompanyJobScoreFieldService extends SkyeyeBusinessService<JobScoreField> {

    void saveJobScoreFieldList(String jobScoreId, List<JobScoreField> scoreFields, String userId);

    List<JobScoreField> queryJobScoreFieldListByJobScoreId(String... jobScoreIds);

    void deleteJobScoreFieldByJobScoreId(String... jobScoreIds);

}
