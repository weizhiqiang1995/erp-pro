/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.clazz.dao.SkyeyeClassServiceBeanDao;
import com.skyeye.clazz.entity.classcatalog.SkyeyeClassServiceBean;
import com.skyeye.clazz.entity.classcatalog.SkyeyeClassServiceBeanApi;
import com.skyeye.clazz.service.SkyeyeClassServiceBeanService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @ClassName: SkyeyeClassServiceBeanServiceImpl
 * @Description: 所有实现了SkyeyeBusinessService的服务类的注册服务
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/29 22:30
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SkyeyeClassServiceBeanServiceImpl extends SkyeyeBusinessServiceImpl<SkyeyeClassServiceBeanDao, SkyeyeClassServiceBean> implements SkyeyeClassServiceBeanService {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 服务类注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void registerServiceBean(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassServiceBeanApi skyeyeClassServiceBeanApi = inputObject.getParams(SkyeyeClassServiceBeanApi.class);

        // 获取数据库中的数据
        QueryWrapper<SkyeyeClassServiceBean> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassServiceBean::getSpringApplicationName), skyeyeClassServiceBeanApi.getSpringApplicationName());
        List<SkyeyeClassServiceBean> oldList = super.list(wrapper);
        List<String> oldKeys = oldList.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());

        // 获取入参的数据
        List<SkyeyeClassServiceBean> classNameList = skyeyeClassServiceBeanApi.getClassNameList();
        classNameList.forEach(className -> className.setSpringApplicationName(skyeyeClassServiceBeanApi.getSpringApplicationName()));

        List<String> newKeys = classNameList.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());

        // (旧数据 - 新数据) 从数据库删除
        List<SkyeyeClassServiceBean> deleteBeans = oldList.stream().filter(item -> !newKeys.contains(item.getClassName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteBeans)) {
            List<String> classNames = deleteBeans.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());
            QueryWrapper<SkyeyeClassServiceBean> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassServiceBean::getSpringApplicationName), skyeyeClassServiceBeanApi.getSpringApplicationName());
            deleteWrapper.in(MybatisPlusUtil.toColumns(SkyeyeClassServiceBean::getClassName), classNames);
            remove(deleteWrapper);
        }

        // (新数据 - 旧数据) 添加到数据库
        List<SkyeyeClassServiceBean> addBeans = classNameList.stream().filter(item -> !oldKeys.contains(item.getClassName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addBeans)) {
            createEntity(addBeans, StringUtils.EMPTY);
        }
    }

    @Override
    public URI getServiceBean(String className) {
        QueryWrapper<SkyeyeClassServiceBean> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassServiceBean::getClassName), className);
        SkyeyeClassServiceBean serviceBean = getOne(wrapper);
        if (serviceBean == null) {
            throw new CustomException("未找到 service bean 对应的业务类配置信息.");
        }
        // 根据服务名获取服务实例
        List<ServiceInstance> allInstances = discoveryClient.getInstances(serviceBean.getSpringApplicationName());
        if (CollectionUtil.isEmpty(allInstances)) {
            throw new CustomException(String.format(Locale.ROOT, "this service[%s] has no instance.", serviceBean.getSpringApplicationName()));
        }
        return allInstances.get(0).getUri();
    }

}
