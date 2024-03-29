/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.dao.DsFormPageContentDao;
import com.skyeye.dsform.entity.DsFormComponent;
import com.skyeye.dsform.entity.DsFormPageContent;
import com.skyeye.dsform.service.DsFormComponentService;
import com.skyeye.dsform.service.DsFormPageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
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

    /**
     * 根据表单布局id获取组件列表
     *
     * @param pageId 动态表单pageId
     * @return 动态表单的内容项
     */
    @Override
    public List<DsFormPageContent> getDsFormPageContentByPageId(String pageId) {
        String cacheKey = getCacheKeyByPageId(pageId);
        List<DsFormPageContent> dsFormPageContentList = redisCache.getList(cacheKey, key -> {
            // 查询该表单布局绑定的组件信息
            QueryWrapper<DsFormPageContent> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(DsFormPageContent::getOrderBy));
            queryWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageContent::getPageId), pageId);
            return list(queryWrapper);
        }, RedisConstants.ALL_USE_TIME, DsFormPageContent.class);

        // 获取组件信息
        List<String> componentIds = dsFormPageContentList.stream().map(DsFormPageContent::getFormContentId).distinct().collect(Collectors.toList());
        Map<String, DsFormComponent> dsFormComponentMap = dsFormComponentService.selectMapByIds(componentIds);
        dsFormPageContentList.forEach(dsFormPageData -> {
            dsFormPageData.setDsFormComponent(dsFormComponentMap.get(dsFormPageData.getFormContentId()));
        });
        return dsFormPageContentList;
    }

    @Override
    public void deleteDsFormContentByPageId(String pageId) {
        QueryWrapper<DsFormPageContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageContent::getPageId), pageId);
        remove(queryWrapper);
        String cacheKey = getCacheKeyByPageId(pageId);
        jedisClientService.del(cacheKey);
    }

    private String getCacheKeyByPageId(String pageId) {
        return String.format(Locale.ROOT, "skyeye:pageContent:pageId:%s", pageId);
    }

}
