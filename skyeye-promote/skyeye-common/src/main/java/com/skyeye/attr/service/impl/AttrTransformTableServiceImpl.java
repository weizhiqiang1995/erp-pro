/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.attr.dao.AttrTransformTableDao;
import com.skyeye.attr.entity.AttrTransformTable;
import com.skyeye.attr.service.AttrTransformTableService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 批量保存表格模型属性
     *
     * @param serviceClassName
     * @param parentAttrKey
     * @param attrTransformTableList
     */
    @Override
    public void saveAttrTransformTable(String serviceClassName, String parentAttrKey, List<AttrTransformTable> attrTransformTableList) {
        deleteAttrTransformTable(serviceClassName, parentAttrKey);
        attrTransformTableList.forEach(attrTransformTable -> {
            attrTransformTable.setClassName(serviceClassName);
            attrTransformTable.setParentAttrKey(parentAttrKey);
        });
        String userId = InputObject.getLogParamsStatic().get("id").toString();
        createEntity(attrTransformTableList, userId);
    }

    /**
     * 根据父节点的属性字段删除表格模型属性
     *
     * @param serviceClassName
     * @param parentAttrKey
     */
    @Override
    public void deleteAttrTransformTable(String serviceClassName, String parentAttrKey) {
        QueryWrapper<AttrTransformTable> queryWrapper = new QueryWrapper();
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getClassName), serviceClassName);
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getParentAttrKey), parentAttrKey);
        remove(queryWrapper);
    }

    /**
     * 根据父节点的属性字段查询表格模型属性
     *
     * @param serviceClassName
     * @param parentAttrKey
     */
    @Override
    public List<AttrTransformTable> queryAttrTransformTable(String serviceClassName, String parentAttrKey) {
        QueryWrapper<AttrTransformTable> queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc(MybatisPlusUtil.toColumns(AttrTransformTable::getOrderBy));
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getClassName), serviceClassName);
        queryWrapper.eq(MybatisPlusUtil.toColumns(AttrTransformTable::getParentAttrKey), parentAttrKey);
        return list(queryWrapper);
    }

}
