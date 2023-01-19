/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.constans.DsFormConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.dao.DsFormPageContentDao;
import com.skyeye.dsform.entity.DsFormComponent;
import com.skyeye.dsform.entity.DsFormPageContent;
import com.skyeye.dsform.service.DsFormComponentService;
import com.skyeye.dsform.service.DsFormPageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void createEntity(InputObject inputObject, OutputObject outputObject) {
        DsFormPageContent entity = inputObject.getParams(clazz);
        String id = createEntity(entity, inputObject.getLogParams().get("id").toString());
        DsFormPageContent dsFormPageContent = selectById(id);
        outputObject.setBean(dsFormPageContent);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public DsFormPageContent selectById(String id) {
        DsFormPageContent dsFormPageContent = super.selectById(id);
        // 获取组件信息
        DsFormComponent dsFormComponent = dsFormComponentService.selectById(dsFormPageContent.getFormContentId());
        dsFormPageContent.setDsFormComponent(dsFormComponent);
        return dsFormPageContent;
    }

    @Override
    public void createPrepose(DsFormPageContent entity) {
        Map<String, Object> bean = skyeyeBaseMapper.queryDsFormPageOrderby(entity.getPageId());
        if (CollectionUtils.isEmpty(bean)) {
            entity.setOrderBy(1);
        } else {
            entity.setOrderBy(Integer.parseInt(bean.get("orderBy").toString()) + 1);
        }
        entity.setDeleteFlag(DeleteFlagEnum.NOT_DELETE.getKey());
    }

    @Override
    public void createPostpose(DsFormPageContent entity, String userId) {
        jedisClientService.del(DsFormConstants.dsFormContentListByPageId(entity.getPageId()));
    }

    /**
     * 编辑表单内容中的组件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void editDsFormPageContentByPageId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String formEditStr = map.get("formedit").toString();
        if (!ToolUtil.isBlank(formEditStr)) {
            List<DsFormPageContent> formEditList = JSONUtil.toList(formEditStr, DsFormPageContent.class);
            String userId = inputObject.getLogParams().get("id").toString();
            for (int i = 0; i < formEditList.size(); i++) {
                DsFormPageContent bean = formEditList.get(i);
                if (bean.getDeleteFlag().equals(DeleteFlagEnum.DELETED.getKey())) {
                    deleteById(bean.getId());
                } else {
                    updateEntity(bean, userId);
                }
                if (i == 0) {
                    // 删除该页面组件的redis
                    jedisClientService.del(DsFormConstants.dsFormContentListByPageId(bean.getPageId()));
                }
            }
        }
    }

    /**
     * 根据表单布局id获取组件列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFormPageContentByPageId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<DsFormPageContent> beans = getDsFormPageContentByPageId(map.get("pageId").toString());
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 根据表单布局id获取组件列表
     *
     * @param dsFormPageId 动态表单pageId
     * @return 动态表单的内容项
     */
    @Override
    public List<DsFormPageContent> getDsFormPageContentByPageId(String dsFormPageId) {
        String cacheKey = DsFormConstants.dsFormContentListByPageId(dsFormPageId);
        List<DsFormPageContent> dsFormPageContentList = redisCache.getList(cacheKey, key -> {
            // 查询该表单布局绑定的组件信息
            QueryWrapper<DsFormPageContent> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(DsFormPageContent::getOrderBy));
            queryWrapper.eq(MybatisPlusUtil.toColumns(clazz, CommonConstants.DELETE_FLAG), DeleteFlagEnum.NOT_DELETE.getKey());
            queryWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageContent::getPageId), dsFormPageId);
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
    public Map<String, Map<String, DsFormPageContent>> getDsFormPageContentByPageId(List<String> dsFormPageId) {
        Map<String, Map<String, DsFormPageContent>> result = new HashMap<>();
        if (CollectionUtil.isEmpty(dsFormPageId)) {
            return result;
        }
        dsFormPageId.forEach(pageId -> {
            List<DsFormPageContent> beans = getDsFormPageContentByPageId(pageId);
            Map<String, DsFormPageContent> formPageContentMap = beans.stream().collect(Collectors.toMap(DsFormPageContent::getId,
                Function.identity(), (key1, key2) -> key2));
            result.put(pageId, formPageContentMap);
        });
        return result;
    }

    @Override
    public void deleteDsFormContentByPageId(String dsFormPageId) {
        UpdateWrapper<DsFormPageContent> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageContent::getPageId), dsFormPageId);
        updateWrapper.set(MybatisPlusUtil.toColumns(clazz, CommonConstants.DELETE_FLAG), DeleteFlagEnum.DELETED.getKey());
        update(updateWrapper);
    }

}
