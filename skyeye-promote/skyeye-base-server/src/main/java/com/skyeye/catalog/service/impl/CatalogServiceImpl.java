/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.catalog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Joiner;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.catalog.classenum.CatalogTypeEnum;
import com.skyeye.catalog.dao.CatalogDao;
import com.skyeye.catalog.entity.Catalog;
import com.skyeye.catalog.service.CatalogService;
import com.skyeye.catalog.service.ICatalogService;
import com.skyeye.clazz.service.SkyeyeClassServiceBeanService;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: CatalogServiceImpl
 * @Description: 目录管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:59
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class CatalogServiceImpl extends SkyeyeBusinessServiceImpl<CatalogDao, Catalog> implements CatalogService {

    @Autowired
    private CatalogDao catalogDao;

    @Autowired
    private ICatalogService iCatalogService;

    @Autowired
    private SkyeyeClassServiceBeanService skyeyeClassServiceBeanService;

    /**
     * 一次性获取所有的目录为树结构
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCatalogForTree(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String objectId = params.get("objectId").toString();
        String objectKey = params.get("objectKey").toString();
        Boolean addOrUser = Boolean.valueOf(params.get("addOrUser").toString());
        String userId = inputObject.getLogParams().get("id").toString();
        List<Catalog> result = getCatalogs(objectId, objectKey, addOrUser, userId);
        // 转为树
        List<Tree<String>> treeNodes = TreeUtil.build(result, String.valueOf(CommonNumConstants.NUM_ZERO), new TreeNodeConfig(),
            (treeNode, tree) -> {
                tree.setId(treeNode.getId());
                tree.setParentId(treeNode.getParentId());
                tree.setName(treeNode.getName());
                tree.putExtra("isParent", true);
                tree.putExtra("icon", treeNode.getIcon());
                tree.putExtra("type", treeNode.getType());
            });
        outputObject.setBeans(treeNodes);
        outputObject.settotal(treeNodes.size());
    }

    /**
     * 一次性获取所有的目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryCatalogList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String objectId = params.get("objectId").toString();
        String objectKey = params.get("objectKey").toString();
        Boolean addOrUser = Boolean.valueOf(params.get("addOrUser").toString());
        String userId = inputObject.getLogParams().get("id").toString();
        List<Catalog> result = getCatalogs(objectId, objectKey, addOrUser, userId);
        outputObject.setBeans(result);
        outputObject.settotal(result.size());
    }

    @Override
    public List<Catalog> getCatalogs(String objectId, String objectKey, Boolean addOrUser, String userId) {
        // 查询这个业务对象所有公共的目录
        List<Catalog> publicCatalogList = queryList(StrUtil.EMPTY, objectKey, CatalogTypeEnum.PUBLIC.getKey(), addOrUser, userId);
        // 查询这个业务对象私有的目录
        List<Catalog> privateCatalogList = queryList(objectId, objectKey, CatalogTypeEnum.PRIVATELY_OWNED.getKey(), addOrUser, userId);

        List<Catalog> result = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(publicCatalogList)) {
            result.addAll(publicCatalogList);
        }
        if (CollectionUtil.isNotEmpty(privateCatalogList)) {
            result.addAll(privateCatalogList);
        }
        return result;
    }

    private List<Catalog> queryList(String objectId, String objectKey, Integer type, Boolean addOrUser, String userId) {
        QueryWrapper<Catalog> queryWrapper = new QueryWrapper();
        queryWrapper.eq(MybatisPlusUtil.toColumns(Catalog::getObjectKey), objectKey);
        if (type != null) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(Catalog::getType), type);
        }
        if (StrUtil.isNotEmpty(objectId)) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(Catalog::getObjectId), objectId);
        }
        if (addOrUser) {
            queryWrapper.eq(MybatisPlusUtil.toColumns(Catalog::getCreateId), userId);
        }
        return list(queryWrapper);
    }

    @Override
    public void deleteById(String id) {
        Catalog catalog = selectById(id);
        URI serviceBeanUri = skyeyeClassServiceBeanService.getServiceBean(catalog.getObjectKey());
        // 获取当前目录与所有的子目录id
        List<String> ids = catalogDao.queryAllChildIdsByParentId(Arrays.asList(id));
        deleteById(ids);
        // 删除业务数据
        iCatalogService.deleteDataMationByCatalogIds(serviceBeanUri, catalog.getObjectKey(), ids);
    }

    @Override
    public List<Catalog> getDataFromDb(List<String> idList) {
        List<Catalog> catalogList = super.getDataFromDb(idList);
        // 设置路径id
        List<Map<String, Object>> parentMationList = catalogDao.queryAllParentNodeById(idList);
        Map<String, List<Map<String, Object>>> groupByMap = parentMationList.stream()
            .sorted(Comparator.comparing(bean -> bean.get("level").toString(), Comparator.reverseOrder()))
            .collect(Collectors.groupingBy(bean -> bean.get("childId").toString()));

        catalogList.forEach(catalog -> {
            List<Map<String, Object>> parentList = groupByMap.get(catalog.getId());
            if (CollectionUtil.isNotEmpty(parentList)) {
                List<String> parentIds = parentList.stream().map(bean -> bean.get("_id").toString()).collect(Collectors.toList());
                catalog.setParentIds(Joiner.on(CommonCharConstants.COMMA_MARK).join(parentIds));
            }
        });
        return catalogList;
    }

}
