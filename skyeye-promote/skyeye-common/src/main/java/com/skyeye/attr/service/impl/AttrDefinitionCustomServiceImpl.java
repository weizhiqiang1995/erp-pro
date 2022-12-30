/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.attr.dao.AttrDefinitionCustomDao;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.attr.entity.AttrDefinitionCustom;
import com.skyeye.attr.service.AttrDefinitionCustomService;
import com.skyeye.attr.service.AttrDefinitionService;
import com.skyeye.attr.service.AttrTransformTableService;
import com.skyeye.attr.service.IAttrTransformService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AttrDefinitionCustomServiceImpl
 * @Description: 用户自定义服务类属性服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/30 10:57
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class AttrDefinitionCustomServiceImpl extends SkyeyeBusinessServiceImpl<AttrDefinitionCustomDao, AttrDefinitionCustom> implements AttrDefinitionCustomService {

    @Autowired
    private AttrDefinitionService attrDefinitionService;

    @Autowired
    private IAttrTransformService iAttrTransformService;

    @Autowired
    private AttrTransformTableService attrTransformTableService;

    @Override
    public void writePostpose(AttrDefinitionCustom entity, String userId) {
        super.writePostpose(entity, userId);
        // 1. 删除当前业务对象的工作流缓存属性信息
        String cacheKey = iAttrTransformService.getCacheKey(entity.getClassName(), "*");
        jedisClientService.delKeys(cacheKey);
        // 2. 获取该业务对象所属父类的信息
        List<String> list = attrTransformTableService.queryParentServiceName(entity.getClassName(), entity.getAttrKey());
        if (CollectionUtil.isNotEmpty(list)) {
            list.forEach(str -> {
                jedisClientService.delKeys(iAttrTransformService.getCacheKey(str, "*"));
            });
        }
    }

    @Override
    public List<AttrDefinitionCustom> queryAttrDefinitionCustomList(String className, List<String> attrKey) {
        QueryWrapper<AttrDefinitionCustom> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinitionCustom::getClassName), className);
        wrapper.in(MybatisPlusUtil.toColumns(AttrDefinitionCustom::getAttrKey), attrKey);
        List<AttrDefinitionCustom> attrDefinitionCustomList = list(wrapper);
        return attrDefinitionCustomList;
    }

    @Override
    public Map<String, AttrDefinitionCustom> queryAttrDefinitionCustomMap(String className, List<String> attrKey) {
        List<AttrDefinitionCustom> attrDefinitionCustomList = queryAttrDefinitionCustomList(className, attrKey);
        return attrDefinitionCustomList.stream().collect(Collectors.toMap(AttrDefinitionCustom::getAttrKey, item -> item));
    }

    @Override
    public AttrDefinitionCustom queryAttrDefinitionCustom(String className, String attrKey) {
        QueryWrapper<AttrDefinitionCustom> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinitionCustom::getClassName), className);
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinitionCustom::getAttrKey), attrKey);
        return getOne(wrapper);
    }

    @Override
    public void queryAttrDefinitionCustom(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        String attrKey = params.get("attrKey").toString();
        // 获取属性的原始信息
        AttrDefinition attrDefinition = attrDefinitionService.queryAttrDefinition(className, attrKey);
        // 获取属性的自定义信息
        AttrDefinitionCustom attrDefinitionCustom = queryAttrDefinitionCustom(className, attrKey);
        if (ObjectUtil.isEmpty(attrDefinitionCustom)) {
            attrDefinitionCustom = new AttrDefinitionCustom();
            attrDefinitionCustom.setName(attrDefinition.getName());
            attrDefinitionCustom.setRemark(attrDefinition.getRemark());
            attrDefinitionCustom.setAttrKey(attrDefinition.getAttrKey());
        }
        attrDefinitionCustom.setAttrDefinition(attrDefinition);
        outputObject.setBean(attrDefinitionCustom);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 删除自定义属性信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteAttrDefinitionCustom(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        String attrKey = params.get("attrKey").toString();
        QueryWrapper<AttrDefinitionCustom> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinitionCustom::getClassName), className);
        wrapper.eq(MybatisPlusUtil.toColumns(AttrDefinitionCustom::getAttrKey), attrKey);
        remove(wrapper);
    }
}
