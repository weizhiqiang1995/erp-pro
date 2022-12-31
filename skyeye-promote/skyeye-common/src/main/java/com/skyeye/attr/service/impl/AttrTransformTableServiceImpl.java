/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.attr.dao.AttrTransformTableDao;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.attr.entity.AttrTransformTable;
import com.skyeye.attr.service.AttrDefinitionService;
import com.skyeye.attr.service.AttrTransformTableService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AttrTransformTableServiceImpl
 * @Description: 表格模型属性管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AttrTransformTableServiceImpl extends SkyeyeBusinessServiceImpl<AttrTransformTableDao, AttrTransformTable> implements AttrTransformTableService {

    @Autowired
    private AttrDefinitionService attrDefinitionService;

    /**
     * 批量保存表格模型属性
     *
     * @param parentServiceClassName
     * @param parentAttrKey
     * @param attrTransformTableList
     */
    @Override
    public void saveAttrTransformTable(String parentServiceClassName, String parentAttrKey, List<AttrTransformTable> attrTransformTableList) {
        deleteAttrTransformTable(parentServiceClassName, parentAttrKey);
        attrTransformTableList.forEach(attrTransformTable -> {
            attrTransformTable.setParentClassName(parentServiceClassName);
            attrTransformTable.setParentAttrKey(parentAttrKey);
        });
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        createEntity(attrTransformTableList, userId);
    }

    /**
     * 根据父节点的属性字段删除表格模型属性
     *
     * @param parentServiceClassName
     * @param parentAttrKey
     */
    @Override
    public void deleteAttrTransformTable(String parentServiceClassName, String parentAttrKey) {
        QueryWrapper<AttrTransformTable> queryWrapper = new QueryWrapper();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getParentClassName), parentServiceClassName);
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getParentAttrKey), parentAttrKey);
        remove(queryWrapper);
    }

    /**
     * 根据父节点的属性字段查询表格模型属性
     *
     * @param parentServiceClassName
     * @param parentAttrKey
     */
    @Override
    public List<AttrTransformTable> queryAttrTransformTable(String parentServiceClassName, String parentAttrKey) {
        QueryWrapper<AttrTransformTable> queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(AttrTransformTable::getOrderBy));
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getParentClassName), parentServiceClassName);
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getParentAttrKey), parentAttrKey);
        return list(queryWrapper);
    }

    /**
     * 根据父节点的属性字段查询表格模型属性
     *
     * @param parentServiceClassName
     * @param parentAttrKey
     */
    @Override
    public Map<String, List<AttrTransformTable>> queryAttrTransformTable(String parentServiceClassName, List<String> parentAttrKey) {
        QueryWrapper<AttrTransformTable> queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(AttrTransformTable::getOrderBy));
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getParentClassName), parentServiceClassName);
        queryWrapper.in(MybatisPlusUtil.toColumns(AttrTransformTable::getParentAttrKey), parentAttrKey);
        List<AttrTransformTable> attrTransformTableList = list(queryWrapper);
        if (CollectionUtil.isEmpty(attrTransformTableList)) {
            return new HashMap<>();
        }

        Map<String, List<AttrTransformTable>> collect = attrTransformTableList.stream().collect(Collectors.groupingBy(AttrTransformTable::getClassName));
        collect.forEach((className, value) -> {
            // 获取属性的基本信息
            List<String> attrKeyList = attrTransformTableList.stream().map(AttrTransformTable::getAttrKey).collect(Collectors.toList());
            Map<String, AttrDefinition> attrDefinitionMap = attrDefinitionService.queryAttrDefinitionMap(className, attrKeyList);
            // 将属性的基本信息进行赋值
            attrTransformTableList.forEach(attrTransformTable -> {
                AttrDefinition attrDefinition = attrDefinitionMap.get(attrTransformTable.getAttrKey());
                if (attrDefinition != null && StrUtil.equals(attrTransformTable.getClassName(), className)) {
                    attrTransformTable.setLabel(attrDefinition.getName());
                }
            });
        });
        return attrTransformTableList.stream().collect(Collectors.groupingBy(AttrTransformTable::getParentAttrKey));
    }

    @Override
    public List<String> queryParentServiceName(String serviceClassName, String attrKey) {
        QueryWrapper<AttrTransformTable> queryWrapper = new QueryWrapper();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getClassName), serviceClassName);
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getAttrKey), attrKey);
        List<AttrTransformTable> attrTransformTableList = list(queryWrapper);
        return attrTransformTableList.stream().map(AttrTransformTable::getParentClassName).distinct().collect(Collectors.toList());
    }

}
