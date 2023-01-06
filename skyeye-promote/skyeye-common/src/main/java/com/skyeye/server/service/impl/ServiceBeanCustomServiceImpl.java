/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.server.dao.ServiceBeanCustomDao;
import com.skyeye.server.entity.ServiceBean;
import com.skyeye.server.entity.ServiceBeanCustom;
import com.skyeye.server.service.ServiceBeanCustomService;
import com.skyeye.server.service.ServiceBeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: ServiceBeanCustomServiceImpl
 * @Description: 自定义服务管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/6 22:44
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ServiceBeanCustomServiceImpl extends SkyeyeBusinessServiceImpl<ServiceBeanCustomDao, ServiceBeanCustom> implements ServiceBeanCustomService {

    @Autowired
    private ServiceBeanService serviceBeanService;

    @Override
    public void queryServiceBeanCustom(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        ServiceBeanCustom serviceBeanCustom = selectById(className);
        outputObject.setBean(serviceBeanCustom);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    @Override
    public ServiceBeanCustom selectById(String className) {
        ServiceBeanCustom serviceBeanCustom = super.selectById(className);
        ServiceBean serviceBean = serviceBeanService.queryServiceClass(className);
        if (serviceBeanCustom == null) {
            serviceBeanCustom = new ServiceBeanCustom();
        }
        serviceBeanCustom.setServiceBean(serviceBean);
        return serviceBeanCustom;
    }

    @Override
    public ServiceBeanCustom getDataFromDb(String className) {
        QueryWrapper<ServiceBeanCustom> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(ServiceBeanCustom::getClassName), className);
        ServiceBeanCustom serviceBeanCustom = getOne(wrapper);
        return serviceBeanCustom;
    }

    @Override
    public void builderByHandler(ServiceBeanCustom bean) {
        if (bean != null) {
            super.builderByHandler(bean);
        }
    }

}
