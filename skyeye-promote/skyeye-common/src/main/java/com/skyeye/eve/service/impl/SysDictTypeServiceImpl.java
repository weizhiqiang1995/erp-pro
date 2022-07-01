/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SysDictTypeDao;
import com.skyeye.eve.eitity.dict.SysDictTypeMation;
import com.skyeye.eve.eitity.dict.SysDictTypeQueryDO;
import com.skyeye.eve.service.SysDictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysDictTypeServiceImpl
 * @Description: 数据字典类型管理服务层
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:31
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class SysDictTypeServiceImpl implements SysDictTypeService {

    @Autowired
    private SysDictTypeDao sysDictTypeDao;

    /**
     * 获取数据字典类型列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryDictTypeList(InputObject inputObject, OutputObject outputObject) {
        SysDictTypeQueryDO sysDictTypeQuery = inputObject.getParams(SysDictTypeQueryDO.class);
        Page pages = PageHelper.startPage(sysDictTypeQuery.getPage(), sysDictTypeQuery.getLimit());
        List<SysDictTypeMation> beans = sysDictTypeDao.queryDictTypeList(sysDictTypeQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 新增/编辑数据字典类型
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void writeDictTypeMation(InputObject inputObject, OutputObject outputObject) {
        SysDictTypeMation sysDictTypeMation = inputObject.getParams(SysDictTypeMation.class);
        // 1.根据dictName和dictCode进行校验
        QueryWrapper<SysDictTypeMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(wrapper ->
            wrapper.eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysDictTypeMation.class, "dictName"), sysDictTypeMation.getDictName())
                .or().eq(MybatisPlusUtil.getDeclaredFieldsInfo2(SysDictTypeMation.class, "dictCode"), sysDictTypeMation.getDictCode()));
        if (StringUtils.isNotEmpty(sysDictTypeMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, sysDictTypeMation.getId());
        }
        SysDictTypeMation checkDictTypeMation = sysDictTypeDao.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(checkDictTypeMation)) {
            String userId = inputObject.getLogParams().get("id").toString();
            if (StringUtils.isNotEmpty(sysDictTypeMation.getId())) {
                DataCommonUtil.setCommonLastUpdateDataByGenericity(sysDictTypeMation, userId);
                sysDictTypeDao.updateById(sysDictTypeMation);
            } else {
                DataCommonUtil.setCommonDataByGenericity(sysDictTypeMation, userId);
                sysDictTypeDao.insert(sysDictTypeMation);
            }
        } else {
            outputObject.setreturnMessage("this data is non-existent.");
        }
    }


}
