/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Joiner;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.dao.DsFormComponentDao;
import com.skyeye.dsform.entity.DsFormComponent;
import com.skyeye.dsform.entity.DsFormDisplayTemplate;
import com.skyeye.dsform.service.DsFormComponentService;
import com.skyeye.dsform.service.DsFormDisplayTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: DsFormComponentServiceImpl
 * @Description: 表单组件管理服务类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/6 22:22
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本组件仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class DsFormComponentServiceImpl extends SkyeyeBusinessServiceImpl<DsFormComponentDao, DsFormComponent> implements DsFormComponentService {

    @Autowired
    private DsFormDisplayTemplateService dsFormDisplayTemplateService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CommonPageInfo commonPageInfo = inputObject.getParams(CommonPageInfo.class);
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryDsFormComponentList(commonPageInfo);
        iSysDictDataService.setNameForMap(beans, "typeId", "typeName");
        return beans;
    }

    @Override
    public DsFormComponent selectById(String id) {
        DsFormComponent dsFormComponent = super.selectById(id);
        if (StrUtil.isNotEmpty(dsFormComponent.getDisplayTemplateId())) {
            DsFormDisplayTemplate displayTemplate = dsFormDisplayTemplateService.selectById(dsFormComponent.getDisplayTemplateId());
            dsFormComponent.setDsFormDisplayTemplate(displayTemplate);
        }
        return dsFormComponent;
    }

    @Override
    public List<DsFormComponent> selectByIds(String... ids) {
        List<DsFormComponent> dsFormComponents = super.selectByIds(ids);
        // 获取数据展示模板信息
        List<String> displayTemplateIdList = dsFormComponents.stream()
            .filter(dsFormComponent -> StrUtil.isNotEmpty(dsFormComponent.getDisplayTemplateId()))
            .map(DsFormComponent::getDisplayTemplateId).collect(Collectors.toList());
        Map<String, DsFormDisplayTemplate> displayTemplateMap = dsFormDisplayTemplateService.selectMapByIds(displayTemplateIdList);
        dsFormComponents.forEach(dsFormComponent -> {
            if (StrUtil.isNotEmpty(dsFormComponent.getDisplayTemplateId())) {
                dsFormComponent.setDsFormDisplayTemplate(displayTemplateMap.get(dsFormComponent.getDisplayTemplateId()));
            }
        });
        return dsFormComponents;
    }

    /**
     * 获取动态表单组件供展示
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryAllDsFormComponentList(InputObject inputObject, OutputObject outputObject) {
        List<DsFormComponent> formComponentList = list();
        // 获取组件的详细信息
        List<String> componentIds = formComponentList.stream()
            .map(DsFormComponent::getId).collect(Collectors.toList());
        formComponentList = selectByIds(componentIds.toArray(new String[]{}));
        iSysDictDataService.setName(formComponentList, "typeId", "typeName");

        List<Tree<String>> treeNodes = convertToTree(formComponentList);

        Map<String, List<DsFormComponent>> result = formComponentList.stream()
            .collect(Collectors.groupingBy(DsFormComponent::getTypeName));
        outputObject.setBean(result);
        outputObject.setBeans(treeNodes);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    private List<Tree<String>> convertToTree(List<DsFormComponent> formComponentList) {
        // 批量获取数据字典类型
        Map<String, List<DsFormComponent>> typeIdMap = formComponentList.stream()
            .collect(Collectors.groupingBy(DsFormComponent::getTypeId));
        Map<String, Map<String, Object>> dictDataMap = iSysDictDataService.queryDataMationForMapByIds(
            Joiner.on(CommonCharConstants.COMMA_MARK).join(typeIdMap.keySet()));
        // 转为树
        List<Tree<String>> treeNodes = new ArrayList<>();
        typeIdMap.forEach((typeId, dsFormComponentList) -> {
            Tree<String> tree = new Tree<>();
            tree.setId(typeId);
            tree.setName(dictDataMap.get(typeId).get("dictName").toString());
            tree.setParentId(String.valueOf(CommonNumConstants.NUM_ZERO));
            tree.putExtra("disabled", true);

            List<Tree<String>> chiledren = new ArrayList<>();
            dsFormComponentList.forEach(dsFormComponent -> {
                Tree<String> componentNode = new Tree<>();
                componentNode.setId(dsFormComponent.getId());
                componentNode.setName(dsFormComponent.getName());
                componentNode.setParentId(dsFormComponent.getTypeId());
                componentNode.putExtra("disabled", false);
                chiledren.add(componentNode);
            });
            tree.setChildren(chiledren);
            treeNodes.add(tree);
        });
        return treeNodes;
    }

}
