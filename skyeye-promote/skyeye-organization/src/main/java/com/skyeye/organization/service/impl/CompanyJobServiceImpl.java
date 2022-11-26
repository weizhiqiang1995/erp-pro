/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.organization.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.entity.search.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.entity.organization.job.CompanyJob;
import com.skyeye.organization.dao.CompanyJobDao;
import com.skyeye.organization.service.CompanyJobScoreService;
import com.skyeye.organization.service.CompanyJobService;
import com.skyeye.organization.service.ICompanyService;
import com.skyeye.organization.service.IDepmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: CompanyJobServiceImpl
 * @Description: 公司部门职位信息管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:57
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CompanyJobServiceImpl extends SkyeyeBusinessServiceImpl<CompanyJobDao, CompanyJob> implements CompanyJobService {

    @Autowired
    private CompanyJobDao companyJobDao;

    @Autowired
    private ICompanyService iCompanyService;

    @Autowired
    private IDepmentService iDepmentService;

    @Autowired
    private CompanyJobScoreService companyJobScoreService;

    /**
     * 获取公司部门职位信息列表
     *
     * @param inputObject 入参以及用户信息等获取对象
     */
    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        TableSelectInfo tableSelectInfo = inputObject.getParams(TableSelectInfo.class);
        List<Map<String, Object>> beans = companyJobDao.queryCompanyJobList(tableSelectInfo);
        iCompanyService.setName(beans, "companyId", "companyName");
        iDepmentService.setName(beans, "departmentId", "departmentName");
        String[] s;
        for (Map<String, Object> bean : beans) {
            s = bean.get("parentId").toString().split(",");
            if (s.length > 0) {
                bean.put("parentId", s[s.length - 1]);
            } else {
                bean.put("parentId", "0");
            }
        }
        return beans;
    }

    public void deletePostpose(String id) {
        // 删除职位等级信息
        companyJobScoreService.deleteByJobId(id);
    }

    /**
     * 获取公司部门职位信息列表展示为树根据公司id
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCompanyJobListTreeByDepartmentId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = companyJobDao.queryCompanyJobListTreeByDepartmentId(map);
        String[] s;
        for (Map<String, Object> bean : beans) {
            s = bean.get("parentId").toString().split(",");
            bean.put("level", s.length);
            if (s.length > 0) {
                bean.put("parentId", s[s.length - 1]);
            } else {
                bean.put("parentId", "0");
            }
        }
        beans = ToolUtil.listToTree(beans, "id", "parentId", "children");
        if (!beans.isEmpty()) {
            ToolUtil.setLastIdentification(beans);
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 根据部门id获取职位列表展示为下拉选择框
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCompanyJobListByToSelect(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = companyJobDao.queryCompanyJobSimpleList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 根据部门id获取职位同级列表且不包含当前id的值展示为下拉选择框
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCompanyJobSimpleListByToSelect(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = companyJobDao.queryCompanyJobSimpleList(map);
        if (!beans.isEmpty()) {
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    @Override
    public List<Map<String, Object>> queryJobList(List<String> companyIds, List<String> departmentIds) {
        companyIds = companyIds.stream().filter(str -> !ToolUtil.isBlank(str)).collect(Collectors.toList());
        departmentIds = departmentIds.stream().filter(str -> !ToolUtil.isBlank(str)).collect(Collectors.toList());
        List<Map<String, Object>> beans = companyJobDao.queryJobList(companyIds, departmentIds);
        return CollectionUtil.isNotEmpty(beans) ? beans : new ArrayList<>();
    }

}
