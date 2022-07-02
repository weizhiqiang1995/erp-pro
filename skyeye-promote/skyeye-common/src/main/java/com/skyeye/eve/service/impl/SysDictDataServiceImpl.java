/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonLetterConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SysDictDataDao;
import com.skyeye.eve.eitity.dict.SysDictDataMation;
import com.skyeye.eve.eitity.dict.SysDictDataQueryDO;
import com.skyeye.eve.service.SysDictDataService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

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

    /**
     * 获取数据字典列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryDictDataList(InputObject inputObject, OutputObject outputObject) {
        SysDictDataQueryDO sysDictDataQuery = inputObject.getParams(SysDictDataQueryDO.class);
        Page pages = PageHelper.startPage(sysDictDataQuery.getPage(), sysDictDataQuery.getLimit());
        List<SysDictDataMation> beans = sysDictDataDao.queryDictDataList(sysDictDataQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增/编辑数据字典
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void writeDictDataMation(InputObject inputObject, OutputObject outputObject) {
        SysDictDataMation sysDictDataMation = inputObject.getParams(SysDictDataMation.class);
        // 1.根据dictName和dictTypeId进行校验
        QueryWrapper<SysDictDataMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysDictDataMation.class, "dictName"), sysDictDataMation.getDictName());
        queryWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysDictDataMation.class, "dictTypeId"), sysDictDataMation.getDictTypeId());
        if (StringUtils.isNotEmpty(sysDictDataMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, sysDictDataMation.getId());
        }
        SysDictDataMation checkDictDataMation = sysDictDataDao.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(checkDictDataMation)) {
            String userId = inputObject.getLogParams().get("id").toString();
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(sysDictDataMation.getId())) {
                LOGGER.info("update dictData data, id is {}", sysDictDataMation.getId());
                DataCommonUtil.setCommonLastUpdateDataByGenericity(sysDictDataMation, userId);
                sysDictDataDao.updateById(sysDictDataMation);
            } else {
                DataCommonUtil.setCommonDataByGenericity(sysDictDataMation, userId);
                LOGGER.info("insert dictData data, id is {}", sysDictDataMation.getId());
                sysDictDataDao.insert(sysDictDataMation);
            }
            // 3.设置默认值
            setIsDefault(sysDictDataMation);
        } else {
            outputObject.setreturnMessage("this data is non-existent.");
        }
    }

    private void setIsDefault(SysDictDataMation sysDictDataMation) {
        if (CommonLetterConstants.LETTER_BIG_Y.equals(sysDictDataMation.getIsDefault())) {
            UpdateWrapper<SysDictDataMation> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysDictDataMation.class, "dictTypeId"), sysDictDataMation.getDictTypeId());
            updateWrapper.ne(CommonConstants.ID, sysDictDataMation.getId());
            updateWrapper.set(MybatisPlusUtil.getDeclaredFieldsInfo2(SysDictDataMation.class, "isDefault"), CommonLetterConstants.LETTER_BIG_N);
            sysDictDataDao.update(null, updateWrapper);
        }
    }

    /**
     * 根据ID获取数据字典信息
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        SysDictDataMation sysDictDataMation = sysDictDataDao.selectById(id);
        outputObject.setBean(sysDictDataMation);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据ID删除数据字典
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void deleteDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        LOGGER.info("delete dictData data, id is {}", id);
        sysDictDataDao.deleteById(id);
    }

    /**
     * 根据所属类型Code获取数据字典列表
     *
     * @param inputObject
     * @param outputObject
     */
    @Override
    public void queryDictDataListByDictTypeCpde(InputObject inputObject, OutputObject outputObject) {
        String dictTypeCpde = inputObject.getParams().get("dictTypeCpde").toString();
        List<SysDictDataMation> dictDataList = sysDictDataDao.queryDictDataListByDictTypeCpde(dictTypeCpde);
        outputObject.setBeans(dictDataList);
        outputObject.settotal(dictDataList.size());
    }
}
