/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.menuapp.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.menuapp.entity.AppWorkPageMation;
import com.skyeye.menuapp.service.AppWorkPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "手机端菜单管理", tags = "手机端菜单管理", modelName = "基础模块")
public class AppWorkPageController {

    @Autowired
    private AppWorkPageService appWorkPageService;

    /**
     * 获取菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAppWorkPageList", value = "获取菜单列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/AppWorkPageController/queryAppWorkPageList")
    public void queryAppWorkPageList(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.queryAppWorkPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑手机端菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeAppWorkPageMation", value = "新增/编辑手机端菜单", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = AppWorkPageMation.class)
    @RequestMapping("/post/AppWorkPageController/writeAppWorkPageMation")
    public void writeAppWorkPageMation(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.writeAppWorkPageMation(inputObject, outputObject);
    }

    /**
     * 获取菜单信息进行回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/queryAppWorkPageMationById")
    public void queryAppWorkPageMationById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.queryAppWorkPageMationById(inputObject, outputObject);
    }

    /**
     * 删除菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/deleteAppWorkPageMationById")
    public void deleteAppWorkPageMationById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.deleteAppWorkPageMationById(inputObject, outputObject);
    }

    /**
     * 菜单上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkPageSortTopById")
    public void editAppWorkPageSortTopById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkPageSortTopById(inputObject, outputObject);
    }

    /**
     * 菜单下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkPageSortLowerById")
    public void editAppWorkPageSortLowerById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkPageSortLowerById(inputObject, outputObject);
    }

    /**
     * 根据父目录id获取子目录集合
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAppWorkPageListByParentId", value = "根据父目录id获取子目录集合", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "parentId", name = "parentId", value = "父目录ID", required = "required"),
        @ApiImplicitParam(id = "desktopId", name = "desktopId", value = "桌面id")})
    @RequestMapping("/post/AppWorkPageController/queryAppWorkPageListByParentId")
    public void queryAppWorkPageListByParentId(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.queryAppWorkPageListByParentId(inputObject, outputObject);
    }

}
