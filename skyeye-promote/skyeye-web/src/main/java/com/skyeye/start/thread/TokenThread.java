/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.start.thread;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.util.PropertiesUtil;
import com.skyeye.common.util.SpringUtils;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.jedis.JedisClientService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.HttpMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;

/**
 * @author 卫志强
 * @ClassName: TokenThread
 * @Description: 通过线程去读取系统中的请求
 * @date 2018年6月8日
 */
public class TokenThread implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(TokenThread.class);

    private static final String API_PLACEHOLDER = "-";

    public TokenThread() {
    }

    @Override
    public void run() {
        try {
            logger.info("start loading xml [{}]", "线程读取配置信息路径开始");
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resolver.getResources("classpath*:reqmapping/mapping/*.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            String appId = PropertiesUtil.getPropertiesValue("${skyeye.appid}");
            String env = PropertiesUtil.getPropertiesValue("${spring.profiles.active}");
            JedisClientService jedisClientService = SpringUtils.getBean(JedisClientService.class);
            List<String> apiIds = new ArrayList<>();
            int notReadNum = 0;
            boolean loadApi = judgeLoadApiVersion(jedisClientService, appId, env);
            for (Resource resource : resources) {
                logger.info("loading resource name is [{}]", resource.getFilename());
                Document doc = db.parse(resource.getInputStream());
                Element controller = doc.getDocumentElement();
                NodeList controllerNodes = controller.getElementsByTagName("url");
                for (int i = 0; i < controllerNodes.getLength(); i++) {
                    Element action = (Element) controllerNodes.item(i);
                    String urlId = action.getAttribute("id");
                    String notRead = action.getAttribute("notRead");
                    if (!"1".equals(notRead) || loadApi) {
                        String modelName = ToolUtil.isBlank(controller.getAttribute("modelName")) ? resource.getFilename() : controller.getAttribute("modelName");
                        // 1.获取API信息
                        Map<String, Object> apiMation = getRequestPath(action, modelName, controller.getAttribute("id"));
                        // 2.获取API入参信息
                        List<Map<String, Object>> apiProperty = getRequestParams(action.getElementsByTagName("property"));
                        apiMation.put("list", apiProperty);
                        // 3.加入系统API集合中
                        String cacheKey = String.format(Locale.ROOT, "%s:%s:%s", appId, env, urlId);
                        jedisClientService.set(cacheKey, JSONUtil.toJsonStr(apiMation), RedisConstants.ALL_USE_TIME);
                    } else {
                        // 说明不再加载该API接口到缓存
                        notReadNum++;
                    }
                    if (apiIds.contains(urlId)) {
                        logger.warn("urlId {} is Repetitive.", urlId);
                    }
                    apiIds.add(urlId);
                }
            }

            logger.info("end loading xml [{}]", "线程读取配置信息路径结束");
            logger.info("Read request path file completed: [{}] interfaces in total, [{}] api not Re-read", apiIds.size(), notReadNum);
            String xmlCacheKey = String.format(Locale.ROOT, "%s:%s:%s", appId, env, Constants.SYS_EVE_MAIN_XML_REQMAPPING_KEY);
            jedisClientService.set(xmlCacheKey, JSONUtil.toJsonStr(apiIds), RedisConstants.ALL_USE_TIME);
        } catch (Exception e) {
            logger.error("init request mation is wrong {}", e);
        }
    }

    private boolean judgeLoadApiVersion(JedisClientService jedisClientService, String appId, String env) {
        String skyeyeLoadApi = PropertiesUtil.getPropertiesValue("${skyeye.load.api}");
        String key = String.format(Locale.ROOT, "%s_%s_load.api.version", appId, env);
        String value = jedisClientService.get(key);
        if (skyeyeLoadApi.equals(value)) {
            return false;
        }
        // 不相等则更新
        jedisClientService.set(key, skyeyeLoadApi, RedisConstants.ALL_USE_TIME);
        return true;
    }

    /**
     * @param action
     * @param modelName 所属模块名
     * @Title: getRequestPath
     * @Description: 获取接口信息
     * @return: void @throws
     */
    private Map<String, Object> getRequestPath(Element action, String modelName, String modelId) throws Exception {
        Map<String, Object> apiMation = new HashMap<>();
        // 工程模块
        apiMation.put("modelName", modelName);
        // 是否需要登录才能使用 1是 0否 2需要登陆才能访问，但无需授权 默认为否
        if (!ToolUtil.isBlank(action.getAttribute("allUse"))) {
            apiMation.put("allUse", action.getAttribute("allUse"));
        } else {
            apiMation.put("allUse", 0);
        }
        apiMation.put("groupName", action.getAttribute("groupName"));
        // 请求地址
        apiMation.put("path", action.getAttribute("path"));
        // 请求方式
        apiMation.put("method", ToolUtil.isBlank(action.getAttribute("method"))
            ? HttpMethod.POST.toString() : action.getAttribute("method"));
        // API描述
        apiMation.put("val", action.getAttribute("val"));
        // API分组
        apiMation.put("groupName", action.getAttribute("groupName"));
        return apiMation;
    }

    /**
     * @param paramsNodes 接口入参节点集合
     * @Title: getRequestParams
     * @Description: 获取每个接口的入参参数
     * @return: void @throws
     */
    private List<Map<String, Object>> getRequestParams(NodeList paramsNodes) {
        List<Map<String, Object>> apiProperty = new ArrayList<>();
        for (int j = 0; j < paramsNodes.getLength(); j++) {
            Map<String, Object> actionBean = new HashMap<>();
            Element property = (Element) paramsNodes.item(j);
            actionBean.put("number", (j + 1));
            // 前端传递的key
            actionBean.put("id", property.getAttribute("id"));
            // 后端接收的key
            actionBean.put("name", property.getAttribute("name"));
            // 参数要求：require、num等
            actionBean.put("ref",
                ToolUtil.isBlank(property.getAttribute("ref")) ? API_PLACEHOLDER : property.getAttribute("ref"));
            // 参数描述
            actionBean.put("var",
                ToolUtil.isBlank(property.getAttribute("var")) ? API_PLACEHOLDER : property.getAttribute("var"));
            // 示例默认值
            actionBean.put("exampleDefault", ToolUtil.isBlank(property.getAttribute("exampleDefault")) ? API_PLACEHOLDER
                : property.getAttribute("exampleDefault"));
            // 默认值
            actionBean.put("defaultValue", ToolUtil.isBlank(property.getAttribute("defaultValue")) ? StringUtils.EMPTY : property.getAttribute("defaultValue"));
            apiProperty.add(actionBean);
        }
        return apiProperty;
    }

}
