/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.clazz.dao.SkyeyeClassFlowableDao;
import com.skyeye.clazz.entity.classflowable.SkyeyeClassFlowableLinkApiMation;
import com.skyeye.clazz.entity.classflowable.SkyeyeClassFlowableLinkMation;
import com.skyeye.clazz.service.SkyeyeClassFlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: SkyeyeClassFlowableServiceImpl
 * @Description: 工作流业务对象服务的服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SkyeyeClassFlowableServiceImpl extends ServiceImpl<SkyeyeClassFlowableDao, SkyeyeClassFlowableLinkMation> implements SkyeyeClassFlowableService {

    @Autowired
    private SkyeyeClassFlowableDao skyeyeClassFlowableDao;

    /**
     * 批量新增工作流业务对象服务
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeClassFlowable(InputObject inputObject, OutputObject outputObject) {
        SkyeyeClassFlowableLinkApiMation skyeyeClassFlowableLinkApiMation = inputObject.getParams(SkyeyeClassFlowableLinkApiMation.class);

        // 获取数据库中的数据
        QueryWrapper<SkyeyeClassFlowableLinkMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassFlowableLinkMation::getAppId), skyeyeClassFlowableLinkApiMation.getAppId());
        List<SkyeyeClassFlowableLinkMation> oldList = super.list(wrapper);
        List<String> oldKeys = oldList.stream().map(bean -> bean.getClassName() + bean.getServiceName() + bean.getListenerClassStr()).collect(Collectors.toList());

        // 获取入参的数据
        List<SkyeyeClassFlowableLinkMation> flowableServiceList = skyeyeClassFlowableLinkApiMation.getFlowableServiceList();
        for (SkyeyeClassFlowableLinkMation classNameBean : flowableServiceList) {
            classNameBean.setAppId(skyeyeClassFlowableLinkApiMation.getAppId());
            DataCommonUtil.setCommonDataByGenericity(classNameBean, "0dc9dd4cd4d446ae9455215fe753c44e");
            DataCommonUtil.setId(classNameBean);
        }
        List<String> newKeys = flowableServiceList.stream().map(bean -> bean.getClassName() + bean.getServiceName() + bean.getListenerClassStr()).collect(Collectors.toList());

        // (旧数据 - 新数据) 从数据库删除
        List<SkyeyeClassFlowableLinkMation> deleteBeans = oldList.stream()
            .filter(item -> !newKeys.contains(item.getClassName() + item.getServiceName() + item.getListenerClassStr())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(deleteBeans)) {
            List<String> classNames = deleteBeans.stream().map(bean -> bean.getClassName()).collect(Collectors.toList());
            QueryWrapper<SkyeyeClassFlowableLinkMation> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassFlowableLinkMation::getAppId), skyeyeClassFlowableLinkApiMation.getAppId());
            deleteWrapper.in(MybatisPlusUtil.toColumns(SkyeyeClassFlowableLinkMation::getClassName), classNames);
            remove(deleteWrapper);
        }

        // (新数据 - 旧数据) 添加到数据库
        List<SkyeyeClassFlowableLinkMation> addBeans = flowableServiceList.stream()
            .filter(item -> !oldKeys.contains(item.getClassName() + item.getServiceName() + item.getListenerClassStr())).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(addBeans)) {
            saveBatch(addBeans);
        }

    }

    /**
     * 获取工作流业务对象服务
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void getClassFlowableData(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String className = params.get("className").toString();
        String appId = params.get("appId").toString();
        QueryWrapper<SkyeyeClassFlowableLinkMation> wrapper = new QueryWrapper<>();
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassFlowableLinkMation::getAppId), appId);
        wrapper.eq(MybatisPlusUtil.toColumns(SkyeyeClassFlowableLinkMation::getClassName), className);
        SkyeyeClassFlowableLinkMation classFlowableLinkMation = super.getOne(wrapper);
        outputObject.setBean(classFlowableLinkMation);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 获取所有工作流业务对象服务列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryClassFlowableDataList(InputObject inputObject, OutputObject outputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        Page pages = PageHelper.startPage(commonPageInfo.getPage(), commonPageInfo.getLimit());
        List<SkyeyeClassFlowableLinkMation> beans = skyeyeClassFlowableDao.queryClassFlowableDataList(commonPageInfo);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }
}
