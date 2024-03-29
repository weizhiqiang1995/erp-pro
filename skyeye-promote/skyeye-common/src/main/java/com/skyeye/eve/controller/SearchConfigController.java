/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.search.SearchMation;
import com.skyeye.eve.service.SearchConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SearchConfigController
 * @Description: 高级查询配置管理类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/16 16:41
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "高级查询配置", tags = "高级查询配置", modelName = "系统公共模块")
public class SearchConfigController {

    @Autowired
    private SearchConfigService searchConfigService;

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于前台使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySearchParamsConfigToHtml", value = "根据urlId以及appId获取高级查询的参数配置信息----用于前台使用", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "urlId", name = "urlId", value = "接口的urlId", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "微服务的APPID", required = "required")})
    @RequestMapping("/post/SearchConfigController/querySearchParamsConfigToHtml")
    public void querySearchParamsConfigToHtml(InputObject inputObject, OutputObject outputObject) {
        searchConfigService.querySearchParamsConfigToHtml(inputObject, outputObject);
    }

    /**
     * 根据urlId以及appId获取高级查询的参数配置信息----用于后台使用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "querySearchParamsConfig", value = "根据urlId以及appId获取高级查询的参数配置信息----用于后台使用", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "urlId", name = "urlId", value = "接口的urlId", required = "required"),
        @ApiImplicitParam(id = "appId", name = "appId", value = "微服务的APPID", required = "required")})
    @RequestMapping("/post/SearchConfigController/querySearchParamsConfig")
    public void querySearchParamsConfig(InputObject inputObject, OutputObject outputObject) {
        searchConfigService.querySearchParamsConfig(inputObject, outputObject);
    }

    /**
     * 新增/编辑高级查询配置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeSearchConfigMation", value = "新增/编辑高级查询配置", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SearchMation.class)
    @RequestMapping("/post/SearchConfigController/writeSearchConfigMation")
    public void writeSearchConfigMation(InputObject inputObject, OutputObject outputObject) {
        searchConfigService.writeSearchConfigMation(inputObject, outputObject);
    }

}
