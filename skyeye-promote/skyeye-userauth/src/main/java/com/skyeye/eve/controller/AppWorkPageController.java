/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.AppWorkPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppWorkPageController {

    @Autowired
    private AppWorkPageService appWorkPageService;

    /**
     * 获取手机端菜单目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/queryAppWorkPageList")
    public void queryLightAppList(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.queryAppWorkPageList(inputObject, outputObject);
    }

    /**
     * 新增手机端菜单目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/insertAppWorkPageMation")
    public void insertAppWorkPageMation(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.insertAppWorkPageMation(inputObject, outputObject);
    }

    /**
     * 根据目录id获取菜单列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/queryAppWorkPageListById")
    public void queryAppWorkPageListById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.queryAppWorkPageListById(inputObject, outputObject);
    }

    /**
     * 新增手机端菜单
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/insertAppWorkPageMationById")
    public void insertAppWorkPageMationById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.insertAppWorkPageMationById(inputObject, outputObject);
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
     * 保存编辑后的菜单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkPageMationById")
    public void editAppWorkPageMationById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkPageMationById(inputObject, outputObject);
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
     * 菜单上线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkPageUpById")
    public void editAppWorkPageUpById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkPageUpById(inputObject, outputObject);
    }

    /**
     * 菜单下线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkPageDownById")
    public void editAppWorkPageDownById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkPageDownById(inputObject, outputObject);
    }

    /**
     * 编辑菜单目录名称
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkPageTitleById")
    public void editAppWorkPageTitleById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkPageTitleById(inputObject, outputObject);
    }

    /**
     * 删除菜单目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/deleteAppWorkPageById")
    public void deleteAppWorkPageById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.deleteAppWorkPageById(inputObject, outputObject);
    }

    /**
     * 目录上线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkUpById")
    public void editAppWorkUpById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkUpById(inputObject, outputObject);
    }

    /**
     * 目录下线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkDownById")
    public void editAppWorkDownById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkDownById(inputObject, outputObject);
    }

    /**
     * 目录上移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkSortTopById")
    public void editAppWorkSortTopById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkSortTopById(inputObject, outputObject);
    }

    /**
     * 目录下移
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/AppWorkPageController/editAppWorkSortLowerById")
    public void editAppWorkSortLowerById(InputObject inputObject, OutputObject outputObject) {
        appWorkPageService.editAppWorkSortLowerById(inputObject, outputObject);
    }

}
