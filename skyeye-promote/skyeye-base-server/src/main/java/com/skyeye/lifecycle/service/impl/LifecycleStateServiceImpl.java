/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.lifecycle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.enumeration.DeleteFlagEnum;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.lifecycle.dao.LifecycleStateDao;
import com.skyeye.lifecycle.entity.LifecycleState;
import com.skyeye.lifecycle.service.LifecycleStateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: LifecycleStateServiceImpl
 * @Description: 生命周期状态管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/3 20:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class LifecycleStateServiceImpl extends SkyeyeBusinessServiceImpl<LifecycleStateDao, LifecycleState> implements LifecycleStateService {


    /**
     * 获取所有启用的状态列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllLifecycleStateList(InputObject inputObject, OutputObject outputObject) {
        QueryWrapper<LifecycleState> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(LifecycleState::getEnabled), EnableEnum.ENABLE_USING.getKey());
        wrapper.eq(MybatisPlusUtil.toColumns(LifecycleState::getDeleteFlag), DeleteFlagEnum.NOT_DELETE.getKey());
        List<LifecycleState> lifecycleStateList = list(wrapper);
        outputObject.setBeans(lifecycleStateList);
        outputObject.settotal(lifecycleStateList.size());
    }
}
