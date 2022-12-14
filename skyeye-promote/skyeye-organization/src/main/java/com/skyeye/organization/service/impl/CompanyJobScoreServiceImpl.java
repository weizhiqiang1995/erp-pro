/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.entity.organization.jobscore.JobScore;
import com.skyeye.eve.entity.organization.jobscore.JobScoreField;
import com.skyeye.eve.entity.organization.jobscore.JobScoreQueryDo;
import com.skyeye.organization.dao.CompanyJobScoreDao;
import com.skyeye.organization.service.CompanyJobScoreFieldService;
import com.skyeye.organization.service.CompanyJobScoreService;
import com.skyeye.wages.service.IWagesFieldTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CompanyJobScoreServiceImpl
 * @Description: 职位定级信息管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:57
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CompanyJobScoreServiceImpl extends SkyeyeBusinessServiceImpl<CompanyJobScoreDao, JobScore> implements CompanyJobScoreService {

    @Autowired
    private CompanyJobScoreDao companyJobScoreDao;

    @Autowired
    private CompanyJobScoreFieldService companyJobScoreFieldService;

    @Autowired
    private IWagesFieldTypeService iWagesFieldTypeService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        JobScoreQueryDo pageInfo = inputObject.getParams(JobScoreQueryDo.class);
        List<Map<String, Object>> beans = companyJobScoreDao.queryCompanyJobScoreList(pageInfo);
        return beans;
    }

    @Override
    public void writePostpose(JobScore entity, String userId) {
        super.writePostpose(entity, userId);
        companyJobScoreFieldService.saveJobScoreFieldList(entity.getId(), entity.getScoreFields(), userId);
    }

    @Override
    public JobScore getDataFromDb(String id) {
        JobScore jobScore = super.getDataFromDb(id);
        List<JobScoreField> jobScoreFields = companyJobScoreFieldService.queryJobScoreFieldListByJobScoreId(jobScore.getId());
        jobScore.setScoreFields(jobScoreFields);
        return jobScore;
    }

    @Override
    public List<JobScore> getDataFromDb(List<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return new ArrayList<>();
        }
        List<JobScore> jobScoreList = super.getDataFromDb(ids);
        // 批量获取关联的薪资字段信息
        List<JobScoreField> jobScoreFields = companyJobScoreFieldService.queryJobScoreFieldListByJobScoreId(ids.toArray(new String[]{}));
        Map<String, List<JobScoreField>> collect = jobScoreFields.stream().collect(Collectors.groupingBy(JobScoreField::getJobScoreId));

        jobScoreList.forEach(jobScore -> jobScore.setScoreFields(collect.get(jobScore.getId())));
        return jobScoreList;
    }

    @Override
    public JobScore selectById(String id) {
        JobScore jobScore = super.selectById(id);
        List<JobScoreField> jobScoreFields = jobScore.getScoreFields();
        if (CollectionUtil.isNotEmpty(jobScoreFields)) {
            List<String> keys = jobScoreFields.stream().map(JobScoreField::getFieldKey).collect(Collectors.toList());
            String keyStr = Joiner.on(CommonCharConstants.COMMA_MARK).join(keys);
            List<Map<String, Object>> list = iWagesFieldTypeService.queryWagesFieldListByKeys(keyStr);
            Map<String, String> fieldMation = list.stream()
                .collect(Collectors.toMap(bean -> bean.get("key").toString(), bean -> bean.get("name").toString()));
            jobScoreFields.forEach(jobScoreField -> {
                jobScoreField.setFieldName(fieldMation.get(jobScoreField.getFieldKey()));
            });
            jobScore.setScoreFields(jobScoreFields);
        }
        return jobScore;
    }

    @Override
    public void deletePostpose(String id) {
        companyJobScoreFieldService.deleteJobScoreFieldByJobScoreId(id);
    }

    @Override
    public void deletePostpose(List<String> ids) {
        companyJobScoreFieldService.deleteJobScoreFieldByJobScoreId(ids.toArray(new String[]{}));
    }

    /**
     * 获取已经启用的职位定级信息列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryEnableCompanyJobScoreList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        map.put("enabled", EnableEnum.ENABLE_USING.getKey());
        List<Map<String, Object>> beans = companyJobScoreDao.queryEnableCompanyJobScoreList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    @Override
    public void deleteByJobId(String jobId) {
        QueryWrapper<JobScore> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(JobScore::getJobId), jobId);
        List<JobScore> jobScoreList = list(queryWrapper);
        if (CollectionUtil.isEmpty(jobScoreList)) {
            return;
        }
        List<String> jobScoreIds = jobScoreList.stream().map(JobScore::getId).collect(Collectors.toList());
        super.deleteById(jobScoreIds);
    }

}
