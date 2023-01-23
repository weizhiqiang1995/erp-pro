/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.DsFormConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.classenum.PageComponentDataType;
import com.skyeye.dsform.dao.DsFormPageContentDao;
import com.skyeye.dsform.entity.DsFormComponent;
import com.skyeye.dsform.entity.DsFormDisplayTemplate;
import com.skyeye.dsform.entity.DsFormPageContent;
import com.skyeye.dsform.service.DsFormComponentService;
import com.skyeye.dsform.service.DsFormDisplayTemplateService;
import com.skyeye.dsform.service.DsFormPageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ClassName: DsFormPageContentServiceImpl
 * @Description: 动态表单页面内容项服务类
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/8 16:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DsFormPageContentServiceImpl extends SkyeyeBusinessServiceImpl<DsFormPageContentDao, DsFormPageContent> implements DsFormPageContentService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private DsFormComponentService dsFormComponentService;

    @Autowired
    private DsFormDisplayTemplateService dsFormDisplayTemplateService;

    /**
     * 根据表单布局id获取组件列表
     *
     * @param pageId 动态表单pageId
     * @return 动态表单的内容项
     */
    @Override
    public List<DsFormPageContent> getDsFormPageContentByPageId(String pageId) {
        String cacheKey = DsFormConstants.dsFormContentListByPageId(pageId);
        List<DsFormPageContent> dsFormPageContentList = redisCache.getList(cacheKey, key -> {
            // 查询该表单布局绑定的组件信息
            QueryWrapper<DsFormPageContent> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(DsFormPageContent::getOrderBy));
            queryWrapper.eq(MybatisPlusUtil.toColumns(clazz, CommonConstants.DELETE_FLAG), DeleteFlagEnum.NOT_DELETE.getKey());
            queryWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageContent::getPageId), pageId);
            return list(queryWrapper);
        }, RedisConstants.ALL_USE_TIME, DsFormPageContent.class);

        // 获取组件信息
        List<String> componentIds = dsFormPageContentList.stream().map(DsFormPageContent::getFormContentId).distinct().collect(Collectors.toList());
        Map<String, DsFormComponent> dsFormComponentMap = dsFormComponentService.selectMapByIds(componentIds);
        dsFormPageContentList.forEach(dsFormPageData -> {
            dsFormPageData.setDsFormComponent(dsFormComponentMap.get(dsFormPageData.getFormContentId()));
        });
        // 获取数据展示模板信息
        List<String> displayTemplateIdList = dsFormPageContentList.stream()
            .filter(dsFormComponent -> StrUtil.isNotEmpty(dsFormComponent.getDisplayTemplateId()))
            .map(DsFormPageContent::getDisplayTemplateId).collect(Collectors.toList());
        Map<String, DsFormDisplayTemplate> displayTemplateMap = dsFormDisplayTemplateService.selectMapByIds(displayTemplateIdList);
        dsFormPageContentList.forEach(dsFormPageContent -> {
            if (dsFormPageContent.getDataType() == PageComponentDataType.CUSTOM.getKey()) {
                dsFormPageContent.setDsFormDisplayTemplate(displayTemplateMap.get(dsFormPageContent.getDisplayTemplateId()));
            }
        });
        return dsFormPageContentList;
    }

    @Override
    public Map<String, Map<String, DsFormPageContent>> getDsFormPageContentByPageId(List<String> pageIdList) {
        Map<String, Map<String, DsFormPageContent>> result = new HashMap<>();
        if (CollectionUtil.isEmpty(pageIdList)) {
            return result;
        }
        pageIdList.forEach(pageId -> {
            List<DsFormPageContent> beans = getDsFormPageContentByPageId(pageId);
            Map<String, DsFormPageContent> formPageContentMap = beans.stream().collect(Collectors.toMap(DsFormPageContent::getId,
                Function.identity(), (key1, key2) -> key2));
            result.put(pageId, formPageContentMap);
        });
        return result;
    }

    @Override
    public Map<String, List<DsFormPageContent>> getDsFormPageContentListByPageId(List<String> pageIdList) {
        Map<String, List<DsFormPageContent>> result = new HashMap<>();
        if (CollectionUtil.isEmpty(pageIdList)) {
            return result;
        }
        pageIdList.forEach(pageId -> {
            List<DsFormPageContent> beans = getDsFormPageContentByPageId(pageId);
            result.put(pageId, beans);
        });
        return result;
    }

    @Override
    public void deleteDsFormContentByPageId(String pageId) {
        UpdateWrapper<DsFormPageContent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageContent::getPageId), pageId);
        updateWrapper.set(MybatisPlusUtil.toColumns(clazz, CommonConstants.DELETE_FLAG), DeleteFlagEnum.DELETED.getKey());
        update(updateWrapper);
    }

}
