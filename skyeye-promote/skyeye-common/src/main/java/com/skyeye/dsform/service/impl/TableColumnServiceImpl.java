/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.dao.TableColumnDao;
import com.skyeye.dsform.entity.TableColumn;
import com.skyeye.dsform.service.TableColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

/**
 * @ClassName: TableColumnServiceImpl
 * @Description: 表格布局的列属性服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/5 20:45
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class TableColumnServiceImpl extends SkyeyeBusinessServiceImpl<TableColumnDao, TableColumn> implements TableColumnService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<TableColumn> getTableColumnByPageId(String pageId) {
        String cacheKey = getCacheKeyByPageId(pageId);
        List<TableColumn> tableColumns = redisCache.getList(cacheKey, key -> {
            QueryWrapper<TableColumn> wrapper = new QueryWrapper<>();
            wrapper.orderByAsc(MybatisPlusUtil.toColumns(TableColumn::getOrderBy));
            wrapper.eq(MybatisPlusUtil.toColumns(TableColumn::getPageId), pageId);
            List<TableColumn> tableColumnList = list(wrapper);
            return tableColumnList;
        }, RedisConstants.ALL_USE_TIME, TableColumn.class);
        return tableColumns;
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void createList(List<TableColumn> entitys, String userId, String pageId) {
        deleteByPageId(pageId);
        entitys.forEach(tableColumn -> {
            tableColumn.setPageId(pageId);
        });
        createEntity(entitys, userId);
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = {Exception.class})
    public void deleteByPageId(String pageId) {
        QueryWrapper<TableColumn> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(TableColumn::getPageId), pageId);
        remove(queryWrapper);
        // 清空缓存
        String cacheKey = getCacheKeyByPageId(pageId);
        jedisClientService.del(cacheKey);
    }

    private String getCacheKeyByPageId(String pageId) {
        return String.format(Locale.ROOT, "skyeye:tableColumn:pageId:%s", pageId);
    }

}
