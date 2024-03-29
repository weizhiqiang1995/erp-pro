/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.constans.CommonNumConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.eve.dao.SearchDao;
import com.skyeye.eve.entity.search.SearchMation;
import com.skyeye.eve.search.entity.SearchOperatorMation;
import com.skyeye.eve.search.entity.SearchParamsConfigMation;
import com.skyeye.eve.search.service.ISearchConfigService;
import com.skyeye.eve.service.SearchConfigService;
import com.skyeye.jedis.JedisClientService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class SearchConfigServiceImpl implements SearchConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchConfigServiceImpl.class);

    @Autowired
    private SearchDao searchDao;

    @Autowired
    private ISearchConfigService iSearchConfigService;

    @Autowired
    private JedisClientService jedisService;

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于前台使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySearchParamsConfigToHtml(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String urlId = params.get("urlId").toString();
        String appId = params.get("appId").toString();
        Map<String, Object> result = this.querySearchParamsConfigToHtml(urlId, appId);
        outputObject.setBean(result);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于前台使用
     *
     * @param urlId urlId
     * @param appId appId
     * @return 高级查询的参数配置信息
     */
    @Override
    public Map<String, Object> querySearchParamsConfigToHtml(String urlId, String appId) {
        Map<String, Object> result = querySearchParamsConfig(urlId, appId);
        if (result != null) {
            result.forEach((key, value) -> {
                String valueStr = JSONUtil.toJsonStr(value);
                SearchParamsConfigMation searchParamsConfigMation = JSONUtil.toBean(valueStr, SearchParamsConfigMation.class);
                // 获取筛选条件
                List<SearchOperatorMation> searchCondition = searchParamsConfigMation.getSearchCondition();
                if (CollectionUtils.isNotEmpty(searchCondition)) {
                    searchCondition.forEach(bean -> {
                        bean.setMySql(null);
                        bean.setOracle(null);
                        bean.setPgSql(null);
                        bean.setSqlServer(null);
                    });
                }
                result.put(key, searchParamsConfigMation);
            });
            return result;
        }
        return null;
    }

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于后台使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySearchParamsConfig(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> params = inputObject.getParams();
        String urlId = params.get("urlId").toString();
        String appId = params.get("appId").toString();
        Map<String, Object> result = this.querySearchParamsConfig(urlId, appId);
        outputObject.setBean(result);
        outputObject.settotal(CommonNumConstants.NUM_ONE);
    }

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于后台使用
     *
     * @param urlId urlId
     * @param appId appId
     * @return 高级查询的参数配置信息
     */
    @Override
    public Map<String, Object> querySearchParamsConfig(String urlId, String appId) {
        SearchMation searchMation = querySearchMation(urlId, appId);
        if (searchMation != null) {
            Map<String, Object> result = JSONUtil.toBean(searchMation.getParamsConfigStr(), null);
            return result;
        }
        return null;
    }

    /**
     * 新增/编辑高级查询配置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void writeSearchConfigMation(InputObject inputObject, OutputObject outputObject) {
        SearchMation searchMation = inputObject.getParams(SearchMation.class);
        // 1.根据appId和urlId进行校验
        QueryWrapper<SearchMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SearchMation::getAppId), searchMation.getAppId());
        queryWrapper.eq(MybatisPlusUtil.toColumns(SearchMation::getUrlId), searchMation.getUrlId());
        if (StringUtils.isNotEmpty(searchMation.getId())) {
            queryWrapper.ne(CommonConstants.ID, searchMation.getId());
        }
        SearchMation checkSearchMation = searchDao.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(checkSearchMation)) {
            searchMation.setParamsConfigStr(JSONUtil.toJsonStr(searchMation.getParamsConfig()));
            // 2.新增/编辑数据
            if (StringUtils.isNotEmpty(searchMation.getId())) {
                LOGGER.info("update searchConfig data, id is {}", searchMation.getId());
                searchDao.updateById(searchMation);
            } else {
                DataCommonUtil.setId(searchMation);
                LOGGER.info("insert searchConfig data, id is {}", searchMation.getId());
                searchDao.insert(searchMation);
            }
            // 删除缓存
            String searchParamsConfigCacheKey = iSearchConfigService.querySearchParamsConfigCacheKeyById(searchMation.getUrlId(), searchMation.getAppId());
            String searchParamsConfigToHtmlCacheKey = iSearchConfigService.querySearchParamsConfigToHtmlCacheKeyById(searchMation.getUrlId(), searchMation.getAppId());
            jedisService.del(searchParamsConfigCacheKey);
            jedisService.del(searchParamsConfigToHtmlCacheKey);
            outputObject.setBean(searchMation);
        } else {
            outputObject.setreturnMessage("this data is non-existent.");
        }
    }

    @Override
    public SearchMation querySearchMation(String urlId, String appId) {
        QueryWrapper<SearchMation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SearchMation::getUrlId), urlId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(SearchMation::getAppId), appId);
        return searchDao.selectOne(queryWrapper);
    }

}
