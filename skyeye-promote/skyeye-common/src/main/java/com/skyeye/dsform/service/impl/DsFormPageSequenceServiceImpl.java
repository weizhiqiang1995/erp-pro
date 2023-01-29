/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.dao.DsFormPageSequenceDao;
import com.skyeye.dsform.entity.DsFormPage;
import com.skyeye.dsform.entity.DsFormPageData;
import com.skyeye.dsform.entity.DsFormPageSequence;
import com.skyeye.dsform.entity.DsFormPageSequenceApiBox;
import com.skyeye.dsform.service.DsFormPageDataService;
import com.skyeye.dsform.service.DsFormPageSequenceService;
import com.skyeye.dsform.service.DsFormPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: DsFormPageSequenceServiceImpl
 * @Description: 业务数据关联的表单布局服务层 todo 待删除
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/2 0:32
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DsFormPageSequenceServiceImpl extends SkyeyeBusinessServiceImpl<DsFormPageSequenceDao, DsFormPageSequence> implements DsFormPageSequenceService {

    @Autowired
    private DsFormPageService dsFormPageService;

    @Autowired
    private DsFormPageDataService dsFormPageDataService;

    /**
     * 根据objectId获取用户已经提交的动态表单信息
     *
     * @param objectId 业务数据id
     * @return 用户已经提交的动态表单信息
     */
    @Override
    public List<DsFormPageSequence> queryDsFormPageSequenceByObjectId(String objectId) {
        QueryWrapper<DsFormPageSequence> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageSequence::getObjectId), objectId);
        List<DsFormPageSequence> dsFormPageSequenceList = list(queryWrapper);
        List<String> pageIds = dsFormPageSequenceList.stream().map(DsFormPageSequence::getPageId).collect(Collectors.toList());
        // 获取表单布局信息
        Map<String, DsFormPage> dsFormPageMap = dsFormPageService.selectMapByIds(pageIds);
        dsFormPageSequenceList.forEach(dsFormPageSequence -> {
            dsFormPageSequence.setDsFormPage(dsFormPageMap.get(dsFormPageSequence.getPageId()));
        });
        return dsFormPageSequenceList;
    }

    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void deleteByObjectId(String objectId) {
        QueryWrapper<DsFormPageSequence> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageSequence::getObjectId), objectId);
        remove(queryWrapper);
        // 删除之前用户填写的数据
        dsFormPageDataService.deleteByObjectId(objectId);
    }

    /**
     * 保存表单布局的数据信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = TRANSACTION_MANAGER_VALUE, rollbackFor = Exception.class)
    public void saveDsFormData(InputObject inputObject, OutputObject outputObject) {
        DsFormPageSequenceApiBox dsFormPageSequenceApiBox = inputObject.getParams(DsFormPageSequenceApiBox.class);
        dsFormPageSequenceApiBox.getDsFormPageSequenceList().forEach(dsFormPageSequence -> {
            dsFormPageSequence.setObjectId(dsFormPageSequenceApiBox.getObjectId());
        });
        String userId = inputObject.getLogParams().get("id").toString();
        createEntity(dsFormPageSequenceApiBox.getDsFormPageSequenceList(), userId);
    }

    @Override
    public void createPrepose(List<DsFormPageSequence> entity) {
        if (CollectionUtil.isEmpty(entity)) {
            return;
        }
        String objectId = entity.stream().findFirst().orElse(new DsFormPageSequence()).getObjectId();
        deleteByObjectId(objectId);
    }

    @Override
    public void createPostpose(List<DsFormPageSequence> entity, String userId) {
        if (CollectionUtil.isEmpty(entity)) {
            return;
        }
        // 保存表单布局中保存的数据
        List<DsFormPageData> dsFormPageDataList = new ArrayList<>();
        entity.forEach(dsFormPageSequence -> {
            dsFormPageSequence.getDsFormPageDataList().forEach(dsFormPageData -> {
                dsFormPageData.setObjectId(dsFormPageSequence.getObjectId());
                dsFormPageData.setSequenceId(dsFormPageSequence.getId());
                dsFormPageData.setPageId(dsFormPageSequence.getPageId());
            });
            dsFormPageDataList.addAll(dsFormPageSequence.getDsFormPageDataList());
        });
        dsFormPageDataService.createEntity(dsFormPageDataList, userId);
    }

    /**
     * 根据objectId获取用户已经提交的动态表单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDsFormDataByObjectId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String objectId = map.get("objectId").toString();
        List<DsFormPageSequence> dsFormPageSequenceList = queryDsFormPageSequenceByObjectId(objectId);
        if (CollectionUtil.isEmpty(dsFormPageSequenceList)) {
            return;
        }
        // 查询业务数据关联的表单布局的数据
        List<String> sequenceId = dsFormPageSequenceList.stream().map(DsFormPageSequence::getId).collect(Collectors.toList());
        Map<String, List<DsFormPageData>> pageData = dsFormPageDataService.queryDsFormPageDataBySequenceId(sequenceId);
        dsFormPageSequenceList.forEach(dsFormPageSequence -> {
            dsFormPageSequence.setDsFormPageDataList(pageData.get(dsFormPageSequence.getId()));
        });
        outputObject.setBeans(dsFormPageSequenceList);
        outputObject.settotal(dsFormPageSequenceList.size());
    }
}
