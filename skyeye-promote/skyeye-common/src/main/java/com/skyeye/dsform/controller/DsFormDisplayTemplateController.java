/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.entity.DsFormDisplayTemplate;
import com.skyeye.dsform.service.DsFormDisplayTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DsFormDisplayTemplateController
 * @Description: 表单数据展示模板管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/6 9:48
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "表单数据展示模板管理", tags = "表单数据展示模板管理", modelName = "动态表单模块")
public class DsFormDisplayTemplateController {

    @Autowired
    private DsFormDisplayTemplateService dsFormDisplayTemplateService;

    /**
     * 获取动态表单数据展示模板列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformdisplaytemplate001", value = "获取动态表单数据展示模板列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/DsFormDisplayTemplateController/queryDsFormDisplayTemplateList")
    public void queryDsFormDisplayTemplateList(InputObject inputObject, OutputObject outputObject) {
        dsFormDisplayTemplateService.queryPageList(inputObject, outputObject);
    }

    /**
     * 添加/编辑表单数据展示模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDsFormDisplayTemplate", value = "添加/编辑表单数据展示模板", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = DsFormDisplayTemplate.class)
    @RequestMapping("/post/DsFormDisplayTemplateController/writeDsFormDisplayTemplate")
    public void writeDsFormDisplayTemplate(InputObject inputObject, OutputObject outputObject) {
        dsFormDisplayTemplateService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除动态表单数据展示模板信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformdisplaytemplate003", value = "删除动态表单数据展示模板信息", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键ID", required = "required")})
    @RequestMapping("/post/DsFormDisplayTemplateController/deleteDsFormDisplayTemplateMationById")
    public void deleteDsFormDisplayTemplateMationById(InputObject inputObject, OutputObject outputObject) {
        dsFormDisplayTemplateService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id获取数据展示模板信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformdisplaytemplate004", value = "根据id获取数据展示模板信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键ID", required = "required")})
    @RequestMapping("/post/DsFormDisplayTemplateController/queryDsFormDisplayTemplateMationToEditById")
    public void queryDsFormDisplayTemplateMationToEditById(InputObject inputObject, OutputObject outputObject) {
        dsFormDisplayTemplateService.selectById(inputObject, outputObject);
    }

    /**
     * 获取动态表单数据展示模板
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformdisplaytemplate006", value = "获取动态表单数据展示模板", method = "GET", allUse = "2")
    @RequestMapping("/post/DsFormDisplayTemplateController/queryDisplayTemplateListToShow")
    public void queryDisplayTemplateListToShow(InputObject inputObject, OutputObject outputObject) {
        dsFormDisplayTemplateService.queryDisplayTemplateListToShow(inputObject, outputObject);
    }

}
