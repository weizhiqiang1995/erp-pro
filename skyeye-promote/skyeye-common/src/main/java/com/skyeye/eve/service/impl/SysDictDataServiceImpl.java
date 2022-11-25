/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.base.Joiner;
import com.skyeye.common.constans.CommonCharConstants;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.enumeration.EnableEnum;
import com.skyeye.common.enumeration.IsDefaultEnum;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SysDictDataDao;
import com.skyeye.eve.dao.SysDictTypeDao;
import com.skyeye.eve.entity.dict.SysDictDataMation;
import com.skyeye.eve.entity.dict.SysDictTypeMation;
import com.skyeye.eve.service.IAuthUserService;
import com.skyeye.eve.service.ISysDictDataService;
import com.skyeye.eve.service.SysDictDataService;
import com.skyeye.jedis.JedisClientService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
public class SysDictDataServiceImpl implements SysDictDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysDictDataServiceImpl.class);

    @Autowired
    private SysDictDataDao sysDictDataDao;

    @Autowired
    private SysDictTypeDao sysDictTypeDao;

    @Autowired
    private JedisClientService jedisClientService;

    @Autowired
    private ISysDictDataService iSysDictDataService;

    @Autowired
    private IAuthUserService iAuthUserService;

    /**
     * 获取数据字典列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDictDataList(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        List<Map<String, Object>> beans = sysDictDataDao.queryDictDataList(map);
        iAuthUserService.setUserNameStr(beans, "createId", "createName");
        iAuthUserService.setUserNameStr(beans, "lastUpdateId", "lastUpdateName");
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 新增/编辑数据字典
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeDictDataMation(InputObject inputObject, OutputObject outputObject) {
        SysDictDataMation sysDictDataMation = inputObject.getParams(SysDictDataMation.class);
        // 1.根据dictName和dictTypeId进行校验
        QueryWrapper<SysDictDataMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysDictDataMation::getDictName), sysDictDataMation.getDictName());
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysDictDataMation::getDictTypeId), sysDictDataMation.getDictTypeId());
        if (StringUtils.isNotEmpty(sysDictDataMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, sysDictDataMation.getId());
        }
        SysDictDataMation checkDictDataMation = sysDictDataDao.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(checkDictDataMation)) {
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(sysDictDataMation.getId())) {
                LOGGER.info("update dictData data, id is {}", sysDictDataMation.getId());
                sysDictDataDao.updateById(sysDictDataMation);
            } else {
                DataCommonUtil.setId(sysDictDataMation);
                LOGGER.info("insert dictData data, id is {}", sysDictDataMation.getId());
                sysDictDataDao.insert(sysDictDataMation);
            }
            // 删除字典缓存
            String cacheKey = iSysDictDataService.queryCacheKeyById(sysDictDataMation.getId());
            jedisClientService.del(cacheKey);
            // 3.设置默认值
            setIsDefault(sysDictDataMation);
        } else {
            outputObject.setreturnMessage("this data is non-existent.");
        }
    }

    private void setIsDefault(SysDictDataMation sysDictDataMation) {
        if (sysDictDataMation.getIsDefault() == IsDefaultEnum.IS_DEFAULT.getKey()) {
            UpdateWrapper<SysDictDataMation> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(MybatisPlusUtil.toColumns(SysDictDataMation::getDictTypeId), sysDictDataMation.getDictTypeId());
            updateWrapper.ne(CommonConstants.ID, sysDictDataMation.getId());
            updateWrapper.set(MybatisPlusUtil.toColumns(SysDictDataMation::getIsDefault), IsDefaultEnum.NOT_DEFAULT.getKey());
            sysDictDataDao.update(null, updateWrapper);
        }
    }

    /**
     * 根据ID获取数据字典信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        SysDictDataMation sysDictDataMation = sysDictDataDao.selectById(id);

        // 设置路径名称
        List<Map<String, Object>> parentNames = sysDictDataDao.queryAllParentNodeById(Arrays.asList(id));
        if (CollectionUtil.isEmpty(parentNames)) {
            return;
        }
        Map<String, List<Map<String, Object>>> groupByMap = parentNames.stream()
            .sorted(Comparator.comparing(bean -> bean.get("level").toString(), Comparator.reverseOrder()))
            .collect(Collectors.groupingBy(bean -> bean.get("childId").toString()));
        List<Map<String, Object>> parentList = groupByMap.get(sysDictDataMation.getId());
        if (CollectionUtil.isNotEmpty(parentList)) {
            List<String> names = parentList.stream().map(bean -> bean.get("name").toString()).collect(Collectors.toList());
            sysDictDataMation.setPathName(Joiner.on(CommonCharConstants.SLASH_MARK).join(names));
        }

        outputObject.setBean(sysDictDataMation);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据IDs批量获取数据字典信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryDictDataMationByIds(InputObject inputObject, OutputObject outputObject) {
        String ids = inputObject.getParams().get("ids").toString();
        // 转成集合并过滤掉空值
        List<String> idList = Arrays.asList(ids.split(CommonCharConstants.COMMA_MARK))
            .stream().filter(str -> !ToolUtil.isBlank(str)).collect(Collectors.toList());
        // 批量查询数据字典
        QueryWrapper<SysDictDataMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(CommonConstants.ID, idList);
        List<SysDictDataMation> sysDictDataMationList = sysDictDataDao.selectList(queryWrapper);

        // 设置路径名称
        List<Map<String, Object>> parentNames = sysDictDataDao.queryAllParentNodeById(idList);
        Map<String, List<Map<String, Object>>> groupByMap = parentNames.stream()
            .sorted(Comparator.comparing(bean -> bean.get("level").toString(), Comparator.reverseOrder()))
            .collect(Collectors.groupingBy(bean -> bean.get("childId").toString()));
        sysDictDataMationList.forEach(sysDictDataMation -> {
            List<Map<String, Object>> parentList = groupByMap.get(sysDictDataMation.getId());
            if (CollectionUtil.isNotEmpty(parentList)) {
                List<String> names = parentList.stream().map(bean -> bean.get("name").toString()).collect(Collectors.toList());
                sysDictDataMation.setPathName(Joiner.on(CommonCharConstants.SLASH_MARK).join(names));
            }
        });

        outputObject.setBeans(sysDictDataMationList);
        outputObject.settotal(sysDictDataMationList.size());
    }

    /**
     * 根据ID删除数据字典
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        LOGGER.info("delete dictData data, id is {}", id);
        sysDictDataDao.deleteById(id);
        // 删除字典缓存
        String cacheKey = iSysDictDataService.queryCacheKeyById(id);
        jedisClientService.del(cacheKey);
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
        List<SysDictDataMation> dictDataList = sysDictDataDao.queryDictDataListByDictTypeCode(dictTypeCode, EnableEnum.ENABLE_USING.getKey());
        if (CollectionUtil.isEmpty(dictDataList)) {
            return;
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (SysDictDataMation bean : dictDataList) {
            Map<String, Object> map = JSONObject.parseObject(JSONObject.toJSONString(bean), Map.class);
            map.put("name", map.get("dictName"));
            result.add(map);
        }

        List<Map<String, Object>> listTree = ToolUtil.listToTree(result, "id", "parentId", "children");
        SysDictTypeMation sysDictTypeMation = sysDictTypeDao.selectById(dictDataList.get(0).getDictTypeId());
        resetCheckNode(listTree, 1, sysDictTypeMation.getChooseLevel());
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
        List<SysDictDataMation> dictDataList = sysDictDataDao.queryDictDataListByDictTypeCode(dictTypeCode, EnableEnum.ENABLE_USING.getKey());
        if (!ToolUtil.isBlank(notId)) {
            // 移除不需要查询的节点，包含子节点
            List<String> childId = sysDictDataDao.queryAllChildIdsByParentId(Arrays.asList(notId));
            dictDataList = dictDataList.stream().filter(bean -> !childId.contains(bean.getId())).collect(Collectors.toList());
        }
        List<Map<String, Object>> result = new ArrayList<>();
        for (SysDictDataMation bean : dictDataList) {
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
        UpdateWrapper<SysDictDataMation> wrapper = new UpdateWrapper<>();
        wrapper.eq(CommonConstants.ID, id);
        wrapper.set(MybatisPlusUtil.toColumns(SysDictDataMation::getParentId), parentId);
        sysDictDataDao.update(null, wrapper);
    }

}
