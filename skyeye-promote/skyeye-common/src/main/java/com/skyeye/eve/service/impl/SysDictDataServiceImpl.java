/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.base.Joiner;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.enumeration.IsDefaultEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SysDictDataDao;
import com.skyeye.eve.dao.SysDictTypeDao;
import com.skyeye.eve.entity.dict.SysDictData;
import com.skyeye.eve.entity.dict.SysDictType;
import com.skyeye.eve.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: SysDictDataServiceImpl
 * @Description: 数据字典服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/2 13:19
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysDictDataServiceImpl extends SkyeyeBusinessServiceImpl<SysDictDataDao, SysDictData> implements SysDictDataService {

    @Autowired
    private SysDictTypeDao sysDictTypeDao;

    @Override
    public List<Map<String, Object>> queryPageDataList(InputObject inputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = skyeyeBaseMapper.queryDictDataList(map);
        return beans;
    }

    @Override
    public void writePostpose(SysDictData entity, String userId) {
        super.writePostpose(entity, userId);
        // 设置默认值
        setIsDefault(entity);
    }

    private void setIsDefault(SysDictData sysDictData) {
        if (sysDictData.getIsDefault().equals(IsDefaultEnum.IS_DEFAULT.getKey())) {
            UpdateWrapper<SysDictData> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(MybatisPlusUtil.toColumns(SysDictData::getDictTypeId), sysDictData.getDictTypeId());
            updateWrapper.ne(CommonConstants.ID, sysDictData.getId());
            updateWrapper.set(MybatisPlusUtil.toColumns(SysDictData::getIsDefault), IsDefaultEnum.NOT_DEFAULT.getKey());
            update(null, updateWrapper);
        }
    }

    @Override
    public SysDictData selectById(String id) {
        SysDictData sysDictData = super.selectById(id);

        // 设置路径名称
        Map<String, List<Map<String, Object>>> groupByMap = getParentNameMap(Arrays.asList(id));
        List<Map<String, Object>> parentList = groupByMap.get(id);
        if (CollectionUtil.isNotEmpty(parentList)) {
            List<String> names = parentList.stream().map(bean -> bean.get("name").toString()).collect(Collectors.toList());
            sysDictData.setPathName(Joiner.on(CommonCharConstants.SLASH_MARK).join(names));
        }

        return sysDictData;
    }

    private Map<String, List<Map<String, Object>>> getParentNameMap(List<String> ids) {
        List<Map<String, Object>> parentNames = skyeyeBaseMapper.queryAllParentNodeById(ids);
        if (CollectionUtil.isEmpty(parentNames)) {
            return new HashMap<>();
        }
        Map<String, List<Map<String, Object>>> groupByMap = parentNames.stream()
            .sorted(Comparator.comparing(bean -> bean.get("level").toString(), Comparator.reverseOrder()))
            .collect(Collectors.groupingBy(bean -> bean.get("childId").toString()));
        return groupByMap;
    }

    @Override
    public List<SysDictData> selectByIds(String... ids) {
        List<SysDictData> sysDictDataList = super.selectByIds(ids);
        // 设置路径名称
        Map<String, List<Map<String, Object>>> groupByMap = getParentNameMap(Arrays.asList(ids));
        sysDictDataList.forEach(sysDictData -> {
            List<Map<String, Object>> parentList = groupByMap.get(sysDictData.getId());
            if (CollectionUtil.isNotEmpty(parentList)) {
                List<String> names = parentList.stream().map(bean -> bean.get("name").toString()).collect(Collectors.toList());
                sysDictData.setPathName(Joiner.on(CommonCharConstants.SLASH_MARK).join(names));
            }
        });
        return sysDictDataList;
    }

    /**
     * 根据所属类型Code获取数据字典列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDictDataListByDictTypeCode(InputObject inputObject, OutputObject outputObject) {
        String dictTypeCode = inputObject.getParams().get("dictTypeCode").toString();
        List<SysDictData> dictDataList = skyeyeBaseMapper.queryDictDataListByDictTypeCode(dictTypeCode, EnableEnum.ENABLE_USING.getKey());
        if (CollectionUtil.isEmpty(dictDataList)) {
            return;
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (SysDictData bean : dictDataList) {
            Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(bean), Map.class);
            map.put("name", map.get("dictName"));
            result.add(map);
        }

        List<Map<String, Object>> listTree = ToolUtil.listToTree(result, "id", "parentId", "children");
        SysDictType sysDictType = sysDictTypeDao.selectById(dictDataList.get(0).getDictTypeId());
        resetCheckNode(listTree, 1, sysDictType.getChooseLevel());
        outputObject.setBeans(result);
        outputObject.setCustomBeans("treeRows", listTree);
        outputObject.settotal(result.size());
    }

    private void resetCheckNode(List<Map<String, Object>> listTree, int parentLevel, int chooseLevel) {
        listTree.forEach(node -> {
            if (parentLevel < chooseLevel) {
                node.put("nocheck", true);
                List<Map<String, Object>> child = (List<Map<String, Object>>) node.get("children");
                if (CollectionUtil.isNotEmpty(child)) {
                    resetCheckNode(child, parentLevel + 1, chooseLevel);
                }
            }
        });
    }

    /**
     * 获取指定分类下不等于指定ID的数据字典集合
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDictDataListByDictTypeCodeAndNotId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String dictTypeCode = params.get("dictTypeCode").toString();
        String notId = params.get("notId").toString();
        // 获取所有的数据字典
        List<SysDictData> dictDataList = skyeyeBaseMapper.queryDictDataListByDictTypeCode(dictTypeCode, EnableEnum.ENABLE_USING.getKey());
        if (!ToolUtil.isBlank(notId)) {
            // 移除不需要查询的节点，包含子节点
            List<String> childId = skyeyeBaseMapper.queryAllChildIdsByParentId(Arrays.asList(notId));
            dictDataList = dictDataList.stream().filter(bean -> !childId.contains(bean.getId())).collect(Collectors.toList());
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (SysDictData bean : dictDataList) {
            Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(bean), Map.class);
            map.put("name", map.get("dictName"));
            result.add(map);
        }
        result = ToolUtil.listToTree(result, "id", "parentId", "children");
        outputObject.setBeans(result);
        outputObject.settotal(result.size());
    }

    /**
     * 移动位置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void setDictDataParent(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String id = params.get("id").toString();
        String parentId = params.get("parentId").toString();
        UpdateWrapper<SysDictData> wrapper = new UpdateWrapper<>();
        wrapper.eq(CommonConstants.ID, id);
        wrapper.set(MybatisPlusUtil.toColumns(SysDictData::getParentId), parentId);
        update(null, wrapper);
        refreshCache(id);
    }

}
