/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.enclosure.dao.EnclosureLinkDao;
import com.skyeye.enclosure.entity.EnclosureLink;
import com.skyeye.enclosure.entity.EnclosureLinkApi;
import com.skyeye.enclosure.service.EnclosureLinkService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EnclosureLinkServiceImpl
 * @Description: 附件信息与业务对象关系的服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:00
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class EnclosureLinkServiceImpl extends SkyeyeBusinessServiceImpl<EnclosureLinkDao, EnclosureLink> implements EnclosureLinkService {

    /**
     * 新增/编辑附件与业务对象关系
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void writeEnclosureLink(InputObject inputObject, OutputObject outputObject) {
        EnclosureLinkApi enclosureLinkApi = inputObject.getParams(EnclosureLinkApi.class);
        String objectId = enclosureLinkApi.getObjectId();
        String objectKey = enclosureLinkApi.getObjectKey();
        List<String> objectFieldList = new ArrayList<>(enclosureLinkApi.getEnclosureIds().keySet());
        if (CollectionUtil.isEmpty(objectFieldList)) {
            return;
        }
        // 删除之前已经保存的
        remove(objectId, objectKey, objectFieldList);
        // 解析入参中最新的数据
        List<EnclosureLink> enclosureLinkList = new ArrayList<>();
        enclosureLinkApi.getEnclosureIds().forEach((objectField, enclosureIds) -> {
            enclosureIds.forEach(enclosureId -> {
                if (StrUtil.isEmpty(enclosureId)) {
                    return;
                }
                EnclosureLink enclosureLink = new EnclosureLink();
                enclosureLink.setObjectField(objectField);
                enclosureLink.setObjectId(objectId);
                enclosureLink.setObjectKey(objectKey);
                enclosureLink.setEnclosureId(enclosureId);
                enclosureLinkList.add(enclosureLink);
            });
        });
        String userId = inputObject.getLogParams().get("id").toString();
        // 保存
        super.createEntity(enclosureLinkList, userId);
    }

    private void remove(String objectId, String objectKey, List<String> objectFieldList) {
        QueryWrapper<EnclosureLink> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq(MybatisPlusUtil.toColumns(EnclosureLink::getObjectId), objectId);
        deleteWrapper.eq(MybatisPlusUtil.toColumns(EnclosureLink::getObjectKey), objectKey);
        if (CollectionUtil.isNotEmpty(objectFieldList)) {
            deleteWrapper.in(MybatisPlusUtil.toColumns(EnclosureLink::getObjectField), objectFieldList);
        }
        remove(deleteWrapper);
    }

    /**
     * 根据业务对象数据获取附件与业务对象关系
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryEnclosureLinkList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String objectId = params.get("objectId").toString();
        String objectKey = params.get("objectKey").toString();
        QueryWrapper<EnclosureLink> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(EnclosureLink::getObjectId), objectId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(EnclosureLink::getObjectKey), objectKey);
        List<EnclosureLink> enclosureLinkList = list(queryWrapper);
        outputObject.setBeans(enclosureLinkList);
        outputObject.settotal(enclosureLinkList.size());
    }

    /**
     * 根据业务对象数据删除附件与业务对象关系
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteEnclosureLink(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String objectId = params.get("objectId").toString();
        String objectKey = params.get("objectKey").toString();
        remove(objectId, objectKey, null);
    }
}
