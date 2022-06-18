/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.ApiConstants;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.ObjectConstant;
import com.skyeye.common.object.OutputObject;
import com.skyeye.jedis.JedisClientService;
import com.skyeye.service.ApiService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private JedisClientService jedisClientService;

    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 获取接口列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryAllSysEveReqMapping(InputObject inputObject, OutputObject outputObject) throws Exception {
        String appId = inputObject.getParams().get("appId").toString();
        List<Map<String, Object>> beans = new ArrayList<>();
        // xml方式
        String xmlCacheKey = String.format(Locale.ROOT, "%s:%s:%s", appId, env, Constants.SYS_EVE_MAIN_XML_REQMAPPING_KEY);
        List<String> xmlApiIds = JSONArray.fromObject(jedisClientService.get(xmlCacheKey));
        // 注解方式
        String annoCacheKey = String.format(Locale.ROOT, "%s:%s:%s", appId, env, ApiConstants.SYS_EVE_MAIN_ANNO_REQMAPPING_KEY);
        List<String> annoApiIds = JSONArray.fromObject(jedisClientService.get(annoCacheKey));
        List<String> apiIds = new ArrayList<>();
        apiIds.addAll(xmlApiIds);
        apiIds.addAll(annoApiIds);
        for (String apiId : apiIds) {
            Map<String, Object> apiMation = RedisConstants.getApiMationByUrlId(apiId, appId);
            Map<String, Object> bean = new HashMap<>();
            // url地址作为id
            bean.put("id", apiId);
            // 注释作为标题
            bean.put("title", apiMation.get("val"));
            // 请求方式
            bean.put("method", apiMation.get("method"));
            // 分组
            bean.put("groupName", apiMation.get("groupName"));
            // 工程模块
            bean.put("modelName", apiMation.get("modelName"));
            beans.add(bean);
        }
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 获取接口详情
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryApiDetails(InputObject inputObject, OutputObject outputObject) throws Exception {
        Map<String, Object> map = inputObject.getParams();
        String appId = map.get("appId").toString();
        String apiId = map.get("id").toString();
        Map<String, Object> jMation = RedisConstants.getApiMationByUrlId(apiId, appId);
        if (jMation != null && !jMation.isEmpty()) {
            String allUse = jMation.get("allUse").toString();
            String s = "";
            if ("0".equals(allUse)) {
                s = "无需登录，无需授权即可访问。";
            } else if ("1".equals(allUse)) {
                s = "需要登录，需要授权才能访问。";
            } else if ("2".equals(allUse)) {
                s = "需要登录，但无需授权即可访问。";
            } else {
                s = "错误参数。";
            }
            jMation.put("sq", s);//权限说明
            jMation.put("requestId", apiId);
            List<Map<String, Object>> params = (List<Map<String, Object>>) jMation.get("list");
            params = params.stream().sorted(Comparator.comparing(bean -> bean.get("number").toString())).collect(Collectors.toList());
            jMation.put("list", params);
            outputObject.setBean(jMation);
        } else {
            outputObject.setreturnMessage("该信息不存在。");
        }
    }

    /**
     * 获取限制条件
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryLimitRestrictions(InputObject inputObject, OutputObject outputObject) throws Exception {
        List<Map<String, Object>> beans = ObjectConstant.VerificationParams.getList();
        outputObject.setBeans(beans);
        outputObject.settotal(beans.size());
    }

    /**
     * 获取所有微服务列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @Override
    public void queryApiMicroservices(InputObject inputObject, OutputObject outputObject) throws Exception {
        List<String> keys = jedisClientService.getKeyListByPattern(ApiConstants.API_MICROSERVICES_REDIS_CACHE_KEY + "*");
        List<Map<String, Object>> result = new ArrayList<>();
        keys.forEach(key -> {
            if (key.endsWith(env)) {
                result.add(JSONUtil.toBean(jedisClientService.get(key), null));
            }
        });
        outputObject.setBeans(result);
        outputObject.settotal(result.size());
    }
}
