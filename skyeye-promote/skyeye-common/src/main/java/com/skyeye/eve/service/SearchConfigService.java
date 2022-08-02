/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.search.SearchMation;

import java.util.Map;

public interface SearchConfigService {

    void querySearchParamsConfigToHtml(InputObject inputObject, OutputObject outputObject);

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于前台使用
     *
     * @param urlId urlId
     * @param appId appId
     * @return 高级查询的参数配置信息
     */
    Map<String, Object> querySearchParamsConfigToHtml(String urlId, String appId);

    void querySearchParamsConfig(InputObject inputObject, OutputObject outputObject);

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于后台使用
     *
     * @param urlId urlId
     * @param appId appId
     * @return 高级查询的参数配置信息
     */
    Map<String, Object> querySearchParamsConfig(String urlId, String appId);

    void writeSearchConfigMation(InputObject inputObject, OutputObject outputObject);

    /**
     * 根据urlId以及appId获取高级查询信息
     *
     * @param urlId urlId
     * @param appId appId
     * @return 高级查询信息
     */
    SearchMation querySearchMation(String urlId, String appId);

}
