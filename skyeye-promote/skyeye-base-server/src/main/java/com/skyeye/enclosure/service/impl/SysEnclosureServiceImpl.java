/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.catalog.entity.Catalog;
import com.skyeye.catalog.entity.CatalogBusinessQueryDo;
import com.skyeye.catalog.service.CatalogService;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.BytesUtil;
import com.skyeye.enclosure.dao.SysEnclosureDao;
import com.skyeye.enclosure.entity.Enclosure;
import com.skyeye.enclosure.service.SysEnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysEnclosureServiceImpl extends SkyeyeBusinessServiceImpl<SysEnclosureDao, Enclosure> implements SysEnclosureService {

    @Autowired
    private SysEnclosureDao sysEnclosureDao;

    @Autowired
    private CatalogService catalogService;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        CatalogBusinessQueryDo pageInfo = inputObject.getParams(CatalogBusinessQueryDo.class);
        pageInfo.setCreateId(inputObject.getLogParams().get("id").toString());
        List<Map<String, Object>> beans = sysEnclosureDao.queryEnclosureList(pageInfo);
        beans.forEach(bean -> {
            String size = BytesUtil.sizeFormatNum2String(Long.parseLong(bean.get("size").toString()));
            bean.put("size", size);
        });
        return beans;
    }

    /**
     * 根据ids(逗号隔开)获取多个附件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryEnclosureInfo(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysEnclosureDao.queryEnclosureInfo(map.get("enclosureInfoIds").toString());
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 获取我的附件树
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryEnclosureTree(InputObject inputObject, OutputObject outputObject) {
        String userId = inputObject.getLogParams().get("id").toString();
        List<Map<String, Object>> enclosureList = sysEnclosureDao.queryEnclosureTree(userId);
        // 获取目录
        List<Catalog> catalogs = catalogService.getCatalogs(StrUtil.EMPTY, getServiceClassName(), true, userId);
        for (Catalog catalog : catalogs) {
            enclosureList.add(BeanUtil.beanToMap(catalog));
        }

        enclosureList = enclosureList.stream()
            .sorted(Comparator.comparing(bean -> bean.get("objectType").toString(), Comparator.naturalOrder())).collect(Collectors.toList());
        // 转为树
        List<Tree<String>> treeNodes = TreeUtil.build(enclosureList, String.valueOf(CommonNumConstants.NUM_ZERO), new TreeNodeConfig(),
            (treeNode, tree) -> {
                tree.setId(treeNode.get("id").toString());
                tree.setParentId(treeNode.get("parentId").toString());
                tree.setName(treeNode.get("name").toString());
                String objectType = treeNode.get("objectType").toString();
                if (StrUtil.equals(objectType, "catalog")) {
                    tree.putExtra("isParent", true);
                }
                tree.putExtra("objectType", objectType);
            });
        outputObject.setBeans(treeNodes);
        outputObject.settotal(treeNodes.size());
    }

}
