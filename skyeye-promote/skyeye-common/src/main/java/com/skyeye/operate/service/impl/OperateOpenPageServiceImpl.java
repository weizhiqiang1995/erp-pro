/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import com.skyeye.operate.dao.OperateOpenPageDao;
import com.skyeye.operate.entity.OperateOpenPage;
import com.skyeye.operate.service.OperateOpenPageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: OperateOpenPageServiceImpl
 * @Description: 操作信息对应的新开页面信息服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:07
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class OperateOpenPageServiceImpl extends SkyeyeBusinessServiceImpl<OperateOpenPageDao, OperateOpenPage> implements OperateOpenPageService {

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = {Exception.class})
    public String createEntity(OperateOpenPage entity, String userId) {
        if (StrUtil.isEmpty(entity.getOperateId())) {
            throw new CustomException("操作信息id为空，请确认.");
        }
        deleteByOperateId(entity.getOperateId());
        return super.createEntity(entity, userId);
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = {Exception.class})
    public void deleteByOperateId(String operateId) {
        QueryWrapper<OperateOpenPage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(OperateOpenPage::getOperateId), operateId);
        remove(queryWrapper);
    }

    @Override
    public OperateOpenPage selectByOperateId(String operateId) {
        QueryWrapper<OperateOpenPage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(OperateOpenPage::getOperateId), operateId);
        return getOne(queryWrapper);
    }

    @Override
    public Map<String, OperateOpenPage> selectByOperateIds(List<String> operateIds) {
        if (CollectionUtil.isEmpty(operateIds)) {
            return new HashMap<>();
        }
        QueryWrapper<OperateOpenPage> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(OperateOpenPage::getOperateId), operateIds);
        List<OperateOpenPage> operateOpenPageList = list(queryWrapper);
        return operateOpenPageList.stream().collect(Collectors.toMap(OperateOpenPage::getOperateId, bean -> bean));
    }
}
