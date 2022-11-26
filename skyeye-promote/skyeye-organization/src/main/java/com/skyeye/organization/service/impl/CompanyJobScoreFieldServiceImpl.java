/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.entity.organization.jobscore.JobScoreField;
import com.skyeye.organization.dao.CompanyJobScoreFieldDao;
import com.skyeye.organization.service.CompanyJobScoreFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: CompanyJobScoreFieldServiceImpl
 * @Description: 职位定级薪资字段设计范围服务
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/25 23:01
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CompanyJobScoreFieldServiceImpl extends SkyeyeBusinessServiceImpl<CompanyJobScoreFieldDao, JobScoreField> implements CompanyJobScoreFieldService {

    @Autowired
    private CompanyJobScoreFieldDao companyJobScoreFieldDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void saveJobScoreFieldList(String jobScoreId, List<JobScoreField> scoreFields, String userId) {
        deleteJobScoreFieldByJobScoreId(jobScoreId);
        scoreFields.forEach(jobScoreField -> jobScoreField.setJobScoreId(jobScoreId));
        super.createEntity(scoreFields, userId);
    }

    @Override
    public List<JobScoreField> queryJobScoreFieldListByJobScoreId(String... jobScoreIds) {
        List<String> jobScoreIdList = Arrays.asList(jobScoreIds);
        jobScoreIdList = jobScoreIdList.stream().filter(str -> !ToolUtil.isBlank(str)).distinct().collect(Collectors.toList());
        if (CollectionUtil.isEmpty(jobScoreIdList)) {
            return new ArrayList<>();
        }
        QueryWrapper<JobScoreField> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(JobScoreField::getJobScoreId), jobScoreIdList);
        return list(queryWrapper);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteJobScoreFieldByJobScoreId(String... jobScoreIds) {
        List<String> jobScoreIdList = Arrays.asList(jobScoreIds);
        jobScoreIdList = jobScoreIdList.stream().filter(str -> !ToolUtil.isBlank(str)).distinct().collect(Collectors.toList());
        if (CollectionUtil.isEmpty(jobScoreIdList)) {
            return;
        }
        companyJobScoreFieldDao.deleteCompanyJobScoreFieldByJobScoreId(jobScoreIdList);
    }


}
