/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.attr.dao.AttrDefinitionDao;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.attr.service.AttrDefinitionService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AttrDefinitionServiceImpl
 * @Description: 服务类属性管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AttrDefinitionServiceImpl extends SkyeyeBusinessServiceImpl<AttrDefinitionDao, AttrDefinition> implements AttrDefinitionService {

    /**
     * 系统启动时，批量扫描业务对象属性入库
     *
     * @param appId
     * @param attrDefinitionList
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void saveBarchAttrDefinition(String appId, List<AttrDefinition> attrDefinitionList) {
        // 获取数据库中的数据
        QueryWrapper<AttrDefinition> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinition::getAppId), appId);
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinition::getModelAttribute), true);
        List<AttrDefinition> oldList = super.list(wrapper);
        List<String> oldKeys = oldList.stream().map(bean -> getKey(bean)).collect(Collectors.toList());

        List<String> newKeys = attrDefinitionList.stream().map(bean -> getKey(bean)).collect(Collectors.toList());

        // (旧数据 - 新数据) 从数据库删除
        List<AttrDefinition> deleteBeans = oldList.stream()
            .filter(item -> !newKeys.contains(getKey(item))).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteBeans)) {
            List<String> ids = deleteBeans.stream().map(AttrDefinition::getId).collect(Collectors.toList());
            deleteById(ids);
        }

        // (新数据 - 旧数据) 添加到数据库
        List<AttrDefinition> addBeans = attrDefinitionList.stream()
            .filter(item -> !oldKeys.contains(getKey(item))).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(addBeans)) {
            addBeans.forEach(attrDefinition -> {
                attrDefinition.setAppId(appId);
                attrDefinition.setModelAttribute(true);
            });
            // 新增模型属性
            createEntity(addBeans, StrUtil.EMPTY);
        }

        // 新数据与旧数据取交集 编辑
        List<AttrDefinition> editBeans = oldList.stream()
            .filter(item -> newKeys.contains(getKey(item))).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(editBeans)) {
            Map<String, AttrDefinition> collect = attrDefinitionList.stream().collect(Collectors.toMap(bean -> getKey(bean), item -> item));
            if (CollectionUtil.isEmpty(collect)) {
                return;
            }
            editBeans.forEach(bean -> {
                String key = getKey(bean);
                AttrDefinition attrDefinition = collect.get(key);
                if (attrDefinition == null) {
                    return;
                }
                bean.setRemark(attrDefinition.getRemark());
                bean.setRequired(attrDefinition.getRequired());
                bean.setWhetherInputParams(attrDefinition.getWhetherInputParams());
            });
            updateEntity(editBeans, StrUtil.EMPTY);
        }
    }

    private String getKey(AttrDefinition attrDefinition) {
        return String.format(Locale.ROOT, "%s:%s", attrDefinition.getClassName(), attrDefinition.getAttrKey());
    }

    /**
     * 根据service的className获取属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAttrDefinitionList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        QueryWrapper<AttrDefinition> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinition::getClassName), className);
        List<AttrDefinition> attrDefinitionList = list(wrapper);
        outputObject.setBeans(attrDefinitionList);
        outputObject.settotal(attrDefinitionList.size());
    }

}
