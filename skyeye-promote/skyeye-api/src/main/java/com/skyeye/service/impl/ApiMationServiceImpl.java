/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.dao.ApiMationDao;
import com.skyeye.eve.entity.api.ApiMation;
import com.skyeye.service.ApiMationService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @ClassName: ApiMationServiceImpl
 * @Description: api接口服务类
 * @author: skyeye云系列
 * @date: 2021/11/28 12:31
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
public class ApiMationServiceImpl implements ApiMationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMationServiceImpl.class);

    @Autowired
    private ApiMationDao apiMationDao;

    /**
     * 编辑api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeApiMation(InputObject inputObject, OutputObject outputObject) {
        ApiMation apiMation = inputObject.getParams(ApiMation.class);
        // 1.校验
        QueryWrapper<ApiMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ApiMation::getAppId), apiMation.getAppId());
        queryWrapper.eq(MybatisPlusUtil.toColumns(ApiMation::getRequestUrl), apiMation.getRequestUrl());
        queryWrapper.eq(MybatisPlusUtil.toColumns(ApiMation::getTitle), apiMation.getTitle());
        if (StringUtils.isNotEmpty(apiMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, apiMation.getId());
        }
        ApiMation checkDictTypeMation = apiMationDao.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(checkDictTypeMation)) {
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(apiMation.getId())) {
                LOGGER.info("update ApiMation data, id is {}", apiMation.getId());
                apiMationDao.updateById(apiMation);
            } else {
                DataCommonUtil.setId(apiMation);
                LOGGER.info("insert ApiMation data, id is {}", apiMation.getId());
                apiMationDao.insert(apiMation);
            }
            outputObject.setBean(apiMation);
        } else {
            outputObject.setreturnMessage("this data [title] is non-existent.");
        }
    }

    /**
     * 根据appId和urlId获取接口参数信息
     *
     * @param appId
     * @param urlId
     * @return
     */
    @Override
    public List<ApiMation> queryApiMationByAppIdAndUrlId(String appId, String urlId) {
        QueryWrapper<ApiMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(ApiMation::getAppId), appId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(ApiMation::getRequestUrl), urlId);
        List<ApiMation> apiMations = apiMationDao.selectList(queryWrapper);
        return apiMations;
    }

    /**
     * 删除api接口信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteApiMationById(InputObject inputObject, OutputObject outputObject) {
        String id = inputObject.getParams().get("id").toString();
        apiMationDao.deleteById(id);
    }

}

