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
import com.skyeye.dsform.entity.DsFormPage;
import com.skyeye.dsform.entity.DsFormPageContentVo;
import com.skyeye.dsform.service.DsFormPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DsFormPageController
 * @Description: 表单布局管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/24 18:59
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本组件仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "表单布局管理", tags = "表单布局管理", modelName = "动态表单模块")
public class DsFormPageController {

    @Autowired
    private DsFormPageService dsFormPageService;

    /**
     * 获取表单布局列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDsFormPageList", value = "获取表单布局列表", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "className", name = "className", value = "service的className", required = "required")})
    @RequestMapping("/post/DsFormPageController/queryDsFormPageList")
    public void queryDsFormPageList(InputObject inputObject, OutputObject outputObject) {
        dsFormPageService.queryDsFormPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑表单布局
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDsFormPage", value = "新增/编辑表单布局", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DsFormPage.class)
    @RequestMapping("/post/DsFormPageController/writeDsFormPage")
    public void writeDsFormPage(InputObject inputObject, OutputObject outputObject) {
        dsFormPageService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除表单布局
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDsFormPage", value = "删除表单布局", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DsFormPageController/deleteDsFormPage")
    public void deleteDsFormPage(InputObject inputObject, OutputObject outputObject) {
        dsFormPageService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id查找表单布局
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsformpage006", value = "根据id查找表单布局", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/DsFormPageController/selectDsFormPageById")
    public void selectDsFormPageById(InputObject inputObject, OutputObject outputObject) {
        dsFormPageService.selectById(inputObject, outputObject);
    }

    /**
     * 保存表单布局关联的组件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDsFormPageContent", value = "保存表单布局关联的组件信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = DsFormPageContentVo.class)
    @RequestMapping("/post/DsFormPageController/writeDsFormPageContent")
    public void writeDsFormPageContent(InputObject inputObject, OutputObject outputObject) {
        dsFormPageService.writeDsFormPageContent(inputObject, outputObject);
    }

}
