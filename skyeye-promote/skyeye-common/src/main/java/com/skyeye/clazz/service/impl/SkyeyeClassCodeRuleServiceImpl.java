/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.CodeRuleDao;
import com.skyeye.clazz.dao.SkyeyeClassCodeRuleDao;
import com.skyeye.eve.entity.coderule.CodeRuleMation;
import com.skyeye.clazz.entity.coderule.SkyeyeClassCodeRuleApiMation;
import com.skyeye.clazz.entity.coderule.SkyeyeClassCodeRuleMation;
import com.skyeye.clazz.service.SkyeyeClassCodeRuleService;
import com.skyeye.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SkyeyeClassCodeRuleServiceImpl
 * @Description: 需要获取编码的服务类服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SkyeyeClassCodeRuleServiceImpl extends ServiceImpl<SkyeyeClassCodeRuleDao, SkyeyeClassCodeRuleMation> implements SkyeyeClassCodeRuleService {

    @Autowired
    private CodeRuleDao codeRuleDao;

    /**
     * 批量新增需要获取编码的服务类
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeClassCodeRule(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassCodeRuleApiMation skyeyeClassCodeRuleApiMation = inputObject.getParams(SkyeyeClassCodeRuleApiMation.class);

        // 获取数据库中的数据
        QueryWrapper<SkyeyeClassCodeRuleMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassCodeRuleMation::getAppId), skyeyeClassCodeRuleApiMation.getAppId());
        List<SkyeyeClassCodeRuleMation> oldList = super.list(wrapper);
        Map<String, String> classNameToColeRuleId = oldList.stream()
            .filter(bean -> !ToolUtil.isBlank(bean.getCodeRuleId()))
            .collect(Collectors.toMap(SkyeyeClassCodeRuleMation::getClassName, SkyeyeClassCodeRuleMation::getCodeRuleId));
        List<String> oldKeys = oldList.stream().map(bean -> bean.getClassName() + bean.getGroupName() + bean.getServiceName()).collect(Collectors.toList());

        // 获取入参的数据
        List<SkyeyeClassCodeRuleMation> classNameList = skyeyeClassCodeRuleApiMation.getClassNameList();
        for (SkyeyeClassCodeRuleMation classNameBean : classNameList) {
            classNameBean.setAppId(skyeyeClassCodeRuleApiMation.getAppId());
            classNameBean.setAppName(skyeyeClassCodeRuleApiMation.getAppName());
            DataCommonUtil.setCommonDataByGenericity(classNameBean, "0dc9dd4cd4d446ae9455215fe753c44e");
            DataCommonUtil.setId(classNameBean);
        }
        List<String> newKeys = classNameList.stream().map(bean -> bean.getClassName() + bean.getGroupName() + bean.getServiceName()).collect(Collectors.toList());

        // (旧数据 - 新数据) 从数据库删除
        List<SkyeyeClassCodeRuleMation> deleteBeans = oldList.stream()
            .filter(item -> !newKeys.contains(item.getClassName() + item.getGroupName() + item.getServiceName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteBeans)) {
            List<String> classNames = deleteBeans.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());
            QueryWrapper<SkyeyeClassCodeRuleMation> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassCodeRuleMation::getAppId), skyeyeClassCodeRuleApiMation.getAppId());
            deleteWrapper.in(MybatisPlusUtil.toColumns(SkyeyeClassCodeRuleMation::getClassName), classNames);
            remove(deleteWrapper);
        }

        // (新数据 - 旧数据) 添加到数据库
        List<SkyeyeClassCodeRuleMation> addBeans = classNameList.stream()
            .filter(item -> !oldKeys.contains(item.getClassName() + item.getGroupName() + item.getServiceName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addBeans)) {
            addBeans.forEach(bean -> {
                bean.setCodeRuleId(classNameToColeRuleId.get(bean.getClassName()));
            });
            saveBatch(addBeans);
        }

    }

    /**
     * 根据serviceClassName获取编码信息，用于rest层调用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void getClassCodeRuleData(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        String appId = params.get("appId").toString();
        QueryWrapper<SkyeyeClassCodeRuleMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassCodeRuleMation::getAppId), appId);
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassCodeRuleMation::getClassName), className);
        SkyeyeClassCodeRuleMation classCodeRuleMation = super.getOne(wrapper);
        if (classCodeRuleMation == null) {
            throw new CustomException("该服务配置不存在");
        }

        Map<String, Object> result = ToolUtil.javaBean2Map(classCodeRuleMation);

        // 获取编码规则
        if (!ToolUtil.isBlank(classCodeRuleMation.getCodeRuleId())) {
            CodeRuleMation codeRuleMation = codeRuleDao.selectById(classCodeRuleMation.getCodeRuleId());
            if (codeRuleMation == null) {
                throw new CustomException("编码不存在");
            }
            result.put("codeRuleNumber", codeRuleMation.getCodeNum());
        }

        outputObject.setBean(result);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 获取需要配置编码的服务类列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryClassCodeRuleList(InputObject inputObject, OutputObject outputObject) {
        List<SkyeyeClassCodeRuleMation> classCodeRuleMationList = super.list();
        Map<String, Map<String, List<SkyeyeClassCodeRuleMation>>> collect = classCodeRuleMationList.stream()
            .collect(Collectors.groupingBy(SkyeyeClassCodeRuleMation::getAppName, Collectors.groupingBy(SkyeyeClassCodeRuleMation::getGroupName)));
        outputObject.setBean(collect);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据id配置编码规则
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editClassCodeRuleConfig(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String id = params.get("id").toString();
        String cudeRuleId = params.get("cudeRuleId").toString();
        UpdateWrapper<SkyeyeClassCodeRuleMation> wrapper = new UpdateWrapper<>();
        wrapper.eq(CommonConstants.ID, id);
        wrapper.set(MybatisPlusUtil.toColumns(SkyeyeClassCodeRuleMation::getCodeRuleId), cudeRuleId);
        super.update(wrapper);
    }
}