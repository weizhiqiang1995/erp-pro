/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.eve.dao.PlanProjectFlowDao;
import com.skyeye.eve.service.PlanProjectFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class PlanProjectFlowServiceImpl implements PlanProjectFlowService {

    @Autowired
    private PlanProjectFlowDao planProjectFlowDao;

    /**
     * 获取项目规划-项目流程图表列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPlanProjectFlowList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = planProjectFlowDao.queryPlanProjectFlowList(map);
        if (!beans.isEmpty()) {
            beans.forEach(bean -> {
                if ("1".equals(bean.get("isParent").toString())) {
                    bean.put("isParent", true);
                } else {
                    bean.put("isParent", false);
                }
            });
            outputObject.setBeans(beans);
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 添加项目规划-项目流程图表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertPlanProjectFlowMation(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectFlowDao.queryPlanProjectFlowMationByName(map);
        if (bean == null) {
            DataCommonUtil.setCommonData(map, inputObject.getLogParams().get("id").toString());
            planProjectFlowDao.insertPlanProjectFlowMation(map);
        } else {
            outputObject.setreturnMessage("该项目规划-项目流程图表名称已存在，不可进行二次保存");
        }
    }

    /**
     * 删除项目规划-项目流程图表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deletePlanProjectFlowMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectFlowDao.queryChildNumMationById(map);
        if (Integer.parseInt(bean.get("childNum").toString()) == 0) {
            // 判断是否有子项
            planProjectFlowDao.deletePlanProjectFlowMationById(map);
        } else {
            outputObject.setreturnMessage("该目录下存在子项，无法直接删除。");
        }

    }

    /**
     * 编辑项目规划-项目流程图表信息时进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPlanProjectFlowMationToEditById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectFlowDao.queryPlanProjectFlowMationToEditById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 编辑项目规划-项目流程图表信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editPlanProjectFlowMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectFlowDao.queryPlanProjectFlowMationByNameAndId(map);
        if (bean == null) {
            planProjectFlowDao.editPlanProjectFlowMationById(map);
        } else {
            outputObject.setreturnMessage("该项目规划-项目流程图表名称已存在，不可进行二次保存");
        }
    }

    /**
     * 获取项目流程图内容进行设计
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryPlanProjectFlowJsonContentMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = planProjectFlowDao.queryPlanProjectFlowJsonContentMationById(map);
        outputObject.setBean(bean);
    }

    /**
     * 设计项目流程图
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editPlanProjectFlowJsonContentMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        planProjectFlowDao.editPlanProjectFlowJsonContentMationById(map);
    }

}
