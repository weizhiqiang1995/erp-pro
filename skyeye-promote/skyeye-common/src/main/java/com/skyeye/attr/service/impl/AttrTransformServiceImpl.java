/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.attr.classenum.DsFormShowType;
import com.skyeye.attr.dao.AttrTransformDao;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.attr.entity.AttrDefinitionCustom;
import com.skyeye.attr.entity.AttrTransform;
import com.skyeye.attr.entity.AttrTransformTable;
import com.skyeye.attr.service.AttrDefinitionService;
import com.skyeye.attr.service.AttrTransformService;
import com.skyeye.attr.service.AttrTransformTableService;
import com.skyeye.attr.service.IAttrTransformService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AttrTransformServiceImpl
 * @Description: 提交到流程的属性信息管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AttrTransformServiceImpl extends SkyeyeBusinessServiceImpl<AttrTransformDao, AttrTransform> implements AttrTransformService {

    @Autowired
    private AttrTransformTableService attrTransformTableService;

    @Autowired
    private AttrDefinitionService attrDefinitionService;

    @Autowired
    private IAttrTransformService iAttrTransformService;

    @Autowired
    protected RedisCache redisCache;

    @Override
    public List<Map<String, Object>> queryDataList(InputObject inputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        String actFlowId = params.get("actFlowId").toString();
        String cacheKey = iAttrTransformService.getCacheKey(className, actFlowId);
        List<AttrTransform> result = redisCache.getList(cacheKey, key -> {
            QueryWrapper<AttrTransform> queryWrapper = new QueryWrapper();
            queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(AttrTransform::getOrderBy));
            queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransform::getClassName), className);
            queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransform::getActFlowId), actFlowId);
            List<AttrTransform> attrTransformList = list(queryWrapper);

            if (CollectionUtil.isEmpty(attrTransformList)) {
                return new ArrayList<>();
            }
            // 获取属性的基本信息
            List<String> attrKeyList = attrTransformList.stream().map(AttrTransform::getAttrKey).collect(Collectors.toList());
            Map<String, AttrDefinition> attrDefinitionMap = attrDefinitionService.queryAttrDefinitionMap(className, attrKeyList);
            // 将属性的基本信息进行赋值
            attrTransformList.forEach(attrTransform -> {
                AttrDefinition attrDefinition = attrDefinitionMap.get(attrTransform.getAttrKey());
                attrTransform.setAttrDefinition(attrDefinition);
            });
            // 移除掉已经不存在的属性
            attrTransformList.removeIf(bean -> ObjectUtil.isEmpty(bean.getAttrDefinition()));
            // 设置表格的属性
            List<String> tableAttrKeyList = attrTransformList.stream()
                .filter(bean -> getShowType(bean.getAttrDefinition()).equals(DsFormShowType.TABLE.getKey()))
                .map(AttrTransform::getAttrKey).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(tableAttrKeyList)) {
                Map<String, List<AttrTransformTable>> attrTransformTable = attrTransformTableService
                    .queryAttrTransformTable(className, tableAttrKeyList);
                attrTransformList.forEach(attrTransform -> {
                    attrTransform.setAttrTransformTableList(attrTransformTable.get(attrTransform.getAttrKey()));
                });
            }

            return attrTransformList;
        }, RedisConstants.THIRTY_DAY_SECONDS, AttrTransform.class);
        return result.stream().map(bean -> BeanUtil.beanToMap(bean)).collect(Collectors.toList());
    }

    private Integer getShowType(AttrDefinition attrDefinition) {
        AttrDefinitionCustom attrDefinitionCustom = attrDefinition.getAttrDefinitionCustom();
        if (StrUtil.isNotEmpty(attrDefinitionCustom.getComponentId())) {
            return attrDefinitionCustom.getDsFormComponent().getShowType();
        }
        return attrDefinitionCustom.getShowType();
    }

    @Override
    public void writePostpose(AttrTransform entity, String userId) {
        super.writePostpose(entity, userId);
        AttrDefinition attrDefinition = attrDefinitionService.queryAttrDefinition(entity.getClassName(), entity.getAttrKey());
        if (getShowType(attrDefinition).equals(DsFormShowType.TABLE.getKey())) {
            attrTransformTableService.saveAttrTransformTable(entity.getClassName(), entity.getAttrKey(), entity.getAttrTransformTableList());
        }
        String cacheKey = iAttrTransformService.getCacheKey(entity.getClassName(), entity.getActFlowId());
        jedisClientService.del(cacheKey);
    }

    @Override
    public void deletePostpose(AttrTransform attrTransform) {
        AttrDefinition attrDefinition = attrDefinitionService.queryAttrDefinition(attrTransform.getClassName(), attrTransform.getAttrKey());
        if (getShowType(attrDefinition).equals(DsFormShowType.TABLE.getKey())) {
            // 如果该属性是表格类型的属性，则删除该属性下的表格信息
            attrTransformTableService.deleteAttrTransformTable(attrTransform.getClassName(), attrTransform.getAttrKey());
        }

        String cacheKey = iAttrTransformService.getCacheKey(attrTransform.getClassName(), attrTransform.getActFlowId());
        jedisClientService.del(cacheKey);
    }

    @Override
    public AttrTransform getDataFromDb(String id) {
        AttrTransform attrTransform = super.getDataFromDb(id);
        AttrDefinition attrDefinition = attrDefinitionService.queryAttrDefinition(attrTransform.getClassName(), attrTransform.getAttrKey());
        if (getShowType(attrDefinition).equals(DsFormShowType.TABLE.getKey())) {
            List<AttrTransformTable> attrTransformTableList = attrTransformTableService
                .queryAttrTransformTable(attrTransform.getClassName(), attrTransform.getAttrKey());
            attrTransform.setAttrTransformTableList(attrTransformTableList);
        }
        return attrTransform;
    }

}
