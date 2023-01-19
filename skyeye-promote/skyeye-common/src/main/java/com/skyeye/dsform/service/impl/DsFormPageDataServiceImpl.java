/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dsform.dao.DsFormPageDataDao;
import com.skyeye.dsform.entity.DsFormPageContent;
import com.skyeye.dsform.entity.DsFormPageData;
import com.skyeye.dsform.service.DsFormPageContentService;
import com.skyeye.dsform.service.DsFormPageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: DsFormPageDataServiceImpl
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/8 17:42
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DsFormPageDataServiceImpl extends SkyeyeBusinessServiceImpl<DsFormPageDataDao, DsFormPageData> implements DsFormPageDataService {

    @Autowired
    private DsFormPageContentService dsFormPageContentService;

    @Override
    public void deleteByObjectId(String objectId) {
        QueryWrapper<DsFormPageData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(DsFormPageData::getObjectId), objectId);
        remove(queryWrapper);
    }

    @Override
    public Map<String, List<DsFormPageData>> queryDsFormPageDataBySequenceId(List<String> sequenceIdList) {
        QueryWrapper<DsFormPageData> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(MybatisPlusUtil.toColumns(DsFormPageData::getSequenceId), sequenceIdList);
        List<DsFormPageData> dsFormPageDataList = list(queryWrapper);
        // 获取组件信息
        List<String> pageIds = dsFormPageDataList.stream().map(DsFormPageData::getPageId).distinct().collect(Collectors.toList());
        Map<String, Map<String, DsFormPageContent>> dsFormPageContentMap = dsFormPageContentService.getDsFormPageContentByPageId(pageIds);
        dsFormPageDataList.forEach(dsFormPageData -> {
            Map<String, DsFormPageContent> dsFormPageContent = dsFormPageContentMap.get(dsFormPageData.getPageId());
            if (CollectionUtil.isEmpty(dsFormPageContent)) {
                return;
            }
            dsFormPageData.setDsFormPageContent(dsFormPageContent.get(dsFormPageData.getContentId()));
        });
        // 过滤掉组件内容已经不存在的数据
        dsFormPageDataList = dsFormPageDataList.stream()
            .filter(dsFormPageData -> ObjectUtil.isNotEmpty(dsFormPageData.getDsFormPageContent())).collect(Collectors.toList());
        // 排序
        dsFormPageDataList.sort(Comparator.comparing(dsFormPageData -> dsFormPageData.getDsFormPageContent().getOrderBy()));

        return dsFormPageDataList.stream().collect(Collectors.groupingBy(DsFormPageData::getSequenceId));
    }

}
