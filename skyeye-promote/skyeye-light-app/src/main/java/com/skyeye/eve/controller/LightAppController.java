/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.LightAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LightAppController {

    @Autowired
    private LightAppService lightAppService;

    /**
     * 获取轻应用列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/queryLightAppList")
    public void queryLightAppList(InputObject inputObject, OutputObject outputObject) {
        lightAppService.queryLightAppList(inputObject, outputObject);
    }

    /**
     * 新增轻应用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/insertLightAppMation")
    public void insertLightAppMation(InputObject inputObject, OutputObject outputObject) {
        lightAppService.insertLightAppMation(inputObject, outputObject);
    }

    /**
     * 编辑轻应用时进行信息回显
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/queryLightAppMationToEditById")
    public void queryLightAppMationToEditById(InputObject inputObject, OutputObject outputObject) {
        lightAppService.queryLightAppMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑轻应用信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/editLightAppMationById")
    public void editLightAppMationById(InputObject inputObject, OutputObject outputObject) {
        lightAppService.editLightAppMationById(inputObject, outputObject);
    }

    /**
     * 删除轻应用
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/deleteLightAppById")
    public void deleteLightAppById(InputObject inputObject, OutputObject outputObject) {
        lightAppService.deleteLightAppById(inputObject, outputObject);
    }

    /**
     * 轻应用上线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/editLightAppUpById")
    public void editLightAppUpTypeById(InputObject inputObject, OutputObject outputObject) {
        lightAppService.editLightAppUpById(inputObject, outputObject);
    }

    /**
     * 轻应用下线
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/editLightAppDownById")
    public void editLightAppDownTypeById(InputObject inputObject, OutputObject outputObject) {
        lightAppService.editLightAppDownById(inputObject, outputObject);
    }

    /**
     * 获取轻应用上线列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/queryLightAppUpList")
    public void queryLightAppUpList(InputObject inputObject, OutputObject outputObject) {
        lightAppService.queryLightAppUpList(inputObject, outputObject);
    }

    /**
     * 添加轻应用到桌面
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/LightAppController/insertLightAppToWin")
    public void insertLightAppToWin(InputObject inputObject, OutputObject outputObject) {
        lightAppService.insertLightAppToWin(inputObject, outputObject);
    }

}
