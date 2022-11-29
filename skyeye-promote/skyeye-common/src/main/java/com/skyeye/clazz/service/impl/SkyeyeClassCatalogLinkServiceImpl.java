/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.clazz.dao.SkyeyeClassCatalogLinkDao;
import com.skyeye.clazz.entity.classcatalog.SkyeyeClassCatalogLink;
import com.skyeye.clazz.entity.classcatalog.SkyeyeClassCatalogLinkApi;
import com.skyeye.clazz.service.SkyeyeClassCatalogLinkService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: SkyeyeClassCatalogLinkServiceImpl
 * @Description: 需要关联目录的服务注册业务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/29 22:30
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SkyeyeClassCatalogLinkServiceImpl extends SkyeyeBusinessServiceImpl<SkyeyeClassCatalogLinkDao, SkyeyeClassCatalogLink> implements SkyeyeClassCatalogLinkService {


    /**
     * 需要关联目录的服务注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeCatalogLink(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassCatalogLinkApi skyeyeClassCatalogLinkApi = inputObject.getParams(SkyeyeClassCatalogLinkApi.class);

        // 获取数据库中的数据
        QueryWrapper<SkyeyeClassCatalogLink> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassCatalogLink::getSpringApplicationName), skyeyeClassCatalogLinkApi.getSpringApplicationName());
        List<SkyeyeClassCatalogLink> oldList = super.list(wrapper);
        List<String> oldKeys = oldList.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());

        // 获取入参的数据
        List<SkyeyeClassCatalogLink> classNameList = skyeyeClassCatalogLinkApi.getClassNameList();
        classNameList.forEach(className -> className.setSpringApplicationName(skyeyeClassCatalogLinkApi.getSpringApplicationName()));

        List<String> newKeys = classNameList.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());

        // (旧数据 - 新数据) 从数据库删除
        List<SkyeyeClassCatalogLink> deleteBeans = oldList.stream().filter(item -> !newKeys.contains(item.getClassName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteBeans)) {
            List<String> classNames = deleteBeans.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());
            QueryWrapper<SkyeyeClassCatalogLink> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassCatalogLink::getSpringApplicationName), skyeyeClassCatalogLinkApi.getSpringApplicationName());
            deleteWrapper.in(MybatisPlusUtil.toColumns(SkyeyeClassCatalogLink::getClassName), classNames);
            remove(deleteWrapper);
        }

        // (新数据 - 旧数据) 添加到数据库
        List<SkyeyeClassCatalogLink> addBeans = classNameList.stream().filter(item -> !oldKeys.contains(item.getClassName())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addBeans)) {
            createEntity(addBeans, StringUtils.EMPTY);
        }
    }

}
