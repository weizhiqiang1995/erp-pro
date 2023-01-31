/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.business.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.business.dao.BusinessApiDao;
import com.skyeye.business.entity.BusinessApi;
import com.skyeye.business.service.BusinessApiService;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: BusinessApiServiceImpl
 * @Description: 业务对象对应的接口信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:07
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class BusinessApiServiceImpl extends SkyeyeBusinessServiceImpl<BusinessApiDao, BusinessApi> implements BusinessApiService {

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = {Exception.class})
    public String createEntity(BusinessApi entity, String userId) {
        if (StrUtil.isEmpty(entity.getObjectId())) {
            throw new CustomException("关联信息id为空，请确认.");
        }
        deleteByObjectId(entity.getObjectId());
        return super.createEntity(entity, userId);
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = {Exception.class})
    public void deleteByObjectId(String objectId) {
        QueryWrapper<BusinessApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(BusinessApi::getObjectId), objectId);
        remove(queryWrapper);
    }

    @Override
    public BusinessApi selectByObjectId(String objectId) {
        QueryWrapper<BusinessApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(BusinessApi::getObjectId), objectId);
        return getOne(queryWrapper);
    }

    @Override
    public Map<String, BusinessApi> selectByObjectIds(List<String> objectIds) {
        if (CollectionUtil.isEmpty(objectIds)) {
            return new HashMap<>();
        }
        QueryWrapper<BusinessApi> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(BusinessApi::getObjectId), objectIds);
        List<BusinessApi> businessApiList = list(queryWrapper);
        return businessApiList.stream().collect(Collectors.toMap(BusinessApi::getObjectId, bean -> bean));
    }

}
