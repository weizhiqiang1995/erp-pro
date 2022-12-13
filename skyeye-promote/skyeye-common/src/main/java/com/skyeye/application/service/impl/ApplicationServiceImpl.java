/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.application.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.application.dao.ApplicationDao;
import com.skyeye.application.entity.Application;
import com.skyeye.application.service.ApplicationService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: ApplicationServiceImpl
 * @Description: 应用管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/12/13 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ApplicationServiceImpl extends SkyeyeBusinessServiceImpl<ApplicationDao, Application> implements ApplicationService {

    /**
     * 应用注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void registerApplication(InputObject inputObject, OutputObject outputObject) {
        Application application = inputObject.getParams(Application.class);

        // 根据appId删除之前注册的
        QueryWrapper<Application> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(Application::getAppId), application.getAppId());
        remove(wrapper);

        // 新增
        createEntity(application, StrUtil.EMPTY);
    }

    @Override
    public List<Map<String, Object>> queryApplicationList() {
        List<Application> applicationList = super.list();
        if (CollectionUtil.isEmpty(applicationList)) {
            return new ArrayList<>();
        }
        return applicationList.stream().map(bean -> BeanUtil.beanToMap(bean)).collect(Collectors.toList());
    }
}
