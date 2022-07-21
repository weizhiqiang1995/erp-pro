/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.eve.dao.PlanProjectDao;
import com.skyeye.eve.service.PlanProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PlanProjectServiceImpl implements PlanProjectService {

    @Autowired
    private PlanProjectDao planProjectDao;

    /**
     * 获取业务流程规划列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPlanProjectList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Page pages = PageHelper.startPage(Integer.parseInt(map.get("page").toString()), Integer.parseInt(map.get("limit").toString()));
        List<Map<String, Object>> beans = planProjectDao.queryPlanProjectList(map);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增业务流程规划
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertPlanProjectMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectDao.judgePlanProjectName(map);
        if (bean == null) {
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            planProjectDao.insertPlanProjectMation(map);
        } else {
            outputObject.setreturnMessage("该业务流程规划名称已存在.");
        }
    }

    /**
     * 删除业务流程规划
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deletePlanProjectMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        planProjectDao.deletePlanProjectMationById(map);
    }

    /**
     * 编辑业务流程规划时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPlanProjectMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectDao.queryPlanProjectMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑业务流程规划
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editPlanProjectMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectDao.judgePlanProjectName(map);
        if (bean == null) {
            Map<String, Object> user = inputObject.getLogParams();
            map.put("lastUpdateId", user.get("id"));
            map.put("lastUpdateTime", DateUtil.getTimeAndToString());
            planProjectDao.editPlanProjectMationById(map);
        } else {
            outputObject.setreturnMessage("该业务流程规划名称已存在.");
        }
    }

}
