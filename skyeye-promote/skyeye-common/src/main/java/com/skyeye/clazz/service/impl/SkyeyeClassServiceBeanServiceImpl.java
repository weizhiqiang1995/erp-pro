/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.application.service.ApplicationService;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.attr.service.AttrDefinitionService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.clazz.dao.SkyeyeClassServiceBeanDao;
import com.skyeye.clazz.entity.classservice.SkyeyeClassServiceBean;
import com.skyeye.clazz.entity.classservice.SkyeyeClassServiceBeanApi;
import com.skyeye.clazz.service.SkyeyeClassServiceBeanService;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.MapUtil;
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
import java.util.*;
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

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private AttrDefinitionService attrDefinitionService;

    /**
     * 服务类注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void registerServiceBean(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassServiceBeanApi skyeyeClassServiceBeanApi = inputObject.getParams(SkyeyeClassServiceBeanApi.class);

        // 获取数据库中的数据
        QueryWrapper<SkyeyeClassServiceBean> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassServiceBean::getSpringApplicationName), skyeyeClassServiceBeanApi.getSpringApplicationName());
        List<SkyeyeClassServiceBean> oldList = super.list(wrapper);
        List<String> oldKeys = oldList.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());

        // 获取入参的数据
        List<SkyeyeClassServiceBean> classNameList = skyeyeClassServiceBeanApi.getClassNameList();
        classNameList.forEach(className -> {
            className.setAppId(skyeyeClassServiceBeanApi.getAppId());
            className.setSpringApplicationName(skyeyeClassServiceBeanApi.getSpringApplicationName());
        });

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

        // 保存属性信息
        saveAttrDefinition(skyeyeClassServiceBeanApi.getAppId(), classNameList);
    }

    private void saveAttrDefinition(String appId, List<SkyeyeClassServiceBean> classNameList) {
        List<AttrDefinition> attrDefinitionList = classNameList.stream()
            .filter(classNamee -> CollectionUtil.isNotEmpty(classNamee.getAttrDefinitionList()))
            .flatMap(className -> className.getAttrDefinitionList().stream())
            .filter(Objects::nonNull).collect(Collectors.toList());
        attrDefinitionService.saveBarchAttrDefinition(appId, attrDefinitionList);
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

    /**
     * 获取服务类信息(树结构)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryServiceClassForTree(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> applications = applicationService.queryApplicationList();
        // 查询服务类信息
        QueryWrapper<SkyeyeClassServiceBean> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassServiceBean::getManageShow), true);
        List<Map<String, Object>> serviceClass = super.list(wrapper)
            .stream().map(bean -> BeanUtil.beanToMap(bean)).collect(Collectors.toList());
        List<Map<String, Object>> result = buildResult(serviceClass);
        applications.forEach(application -> {
            result.add(getResultMap(application.get("appId").toString(), application.get("appName").toString(), "0", true));
        });
        // 转为树
        List<Tree<String>> treeNodes = TreeUtil.build(result, String.valueOf(CommonNumConstants.NUM_ZERO), new TreeNodeConfig(),
            (treeNode, tree) -> {
                tree.setId(treeNode.get("key").toString());
                tree.setParentId(treeNode.get("parentKey").toString());
                tree.setName(treeNode.get("name").toString());
                tree.putExtra("isParent", treeNode.get("isParent"));
            });
        outputObject.setBeans(treeNodes);
        outputObject.settotal(treeNodes.size());

    }

    private List<Map<String, Object>> buildResult(List<Map<String, Object>> serviceClass) {
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, List<Map<String, Object>>> collect = serviceClass.stream().collect(Collectors.groupingBy(bean -> bean.get("appId").toString()));
        collect.forEach((appId, classNameList) -> {
            // 获取没有分组的业务对象服务类
            List<String> noGroupNameClassNameIdList = classNameList.stream()
                .filter(bean -> MapUtil.checkKeyIsNull(bean, "groupName"))
                .map(bean -> bean.get("id").toString()).collect(Collectors.toList());
            if (CollectionUtil.isNotEmpty(noGroupNameClassNameIdList)) {
                String groupKey = appId + ".noGroupName";
                result.add(getResultMap(groupKey, "未分组", appId, true));
                serviceClass.forEach(bean -> {
                    String id = bean.get("id").toString();
                    if (noGroupNameClassNameIdList.indexOf(id) >= 0) {
                        result.add(getResultMap(bean.get("className").toString(), bean.get("name").toString(), groupKey, false));
                    }
                });
            }
            // 获取有分组的业务对象服务类
            Map<String, List<Map<String, Object>>> hasGroupNameIdList = classNameList.stream()
                .filter(bean -> !MapUtil.checkKeyIsNull(bean, "groupName"))
                .collect(Collectors.groupingBy(bean -> bean.get("groupName").toString()));
            hasGroupNameIdList.forEach((groupName, classNames) -> {
                String groupKey = appId + ".hasGroupName" + "." + groupName;
                result.add(getResultMap(groupKey, groupName, appId, true));

                List<String> classNameIds = classNames.stream().map(bean -> bean.get("id").toString()).collect(Collectors.toList());
                serviceClass.forEach(bean -> {
                    String id = bean.get("id").toString();
                    if (classNameIds.indexOf(id) >= 0) {
                        result.add(getResultMap(bean.get("className").toString(), bean.get("name").toString(), groupKey, false));
                    }
                });
            });
        });
        return result;
    }

    private Map<String, Object> getResultMap(String key, String name, String parentKey, Boolean isParent) {
        Map<String, Object> groupName = new HashMap<>();
        groupName.put("key", key);
        groupName.put("name", name);
        groupName.put("parentKey", parentKey);
        groupName.put("isParent", isParent);
        return groupName;
    }

    /**
     * 获取服务类信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryServiceClass(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        QueryWrapper<SkyeyeClassServiceBean> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassServiceBean::getClassName), className);
        SkyeyeClassServiceBean skyeyeClassServiceBean = getOne(wrapper);
        outputObject.setBean(skyeyeClassServiceBean);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

}
