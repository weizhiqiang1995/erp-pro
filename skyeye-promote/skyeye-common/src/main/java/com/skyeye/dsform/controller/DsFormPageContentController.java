/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.entity.DsFormPageContent;
import com.skyeye.dsform.service.DsFormPageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DsFormPageContentController
 * @Description: 动态表单页面内容项控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/8 16:47
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "表单布局组件管理", tags = "表单布局组件管理", modelName = "动态表单模块")
public class DsFormPageContentController {

    @Autowired
    private DsFormPageContentService dsFormPageContentService;

    /**
     * 新增表单布局里的组件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformpage003", value = "新增表单布局里的组件", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DsFormPageContent.class)
    @RequestMapping("/post/DsFormPageContentController/insertDsFormPageContent")
    public void insertDsFormPageContent(InputObject inputObject, OutputObject outputObject) {
        dsFormPageContentService.createEntity(inputObject, outputObject);
    }

    /**
     * 编辑表单内容中的组件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/DsFormPageContentController/editDsFormPageContentByPageId")
    public void editDsFormPageContentByPageId(InputObject inputObject, OutputObject outputObject) {
        dsFormPageContentService.editDsFormPageContentByPageId(inputObject, outputObject);
    }

    /**
     * 根据表单布局id获取组件列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformpage004", value = "根据表单布局id获取组件列表", method = "GET", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "pageId", name = "pageId", value = "表单布局id", required = "required")})
    @RequestMapping("/post/DsFormPageContentController/queryFormPageContentByPageId")
    public void queryFormPageContentByPageId(InputObject inputObject, OutputObject outputObject) {
        dsFormPageContentService.queryFormPageContentByPageId(inputObject, outputObject);
    }

}
