/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.LightAppTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LightAppTypeController {

    @Autowired
    private LightAppTypeService lightAppTypeService;

    /**
     * 获取轻应用类型列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/queryLightAppTypeList")
    public void queryLightAppTypeList(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.queryLightAppTypeList(inputObject, outputObject);
    }

    /**
     * 新增轻应用类型
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/insertLightAppTypeMation")
    public void insertLightAppTypeMation(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.insertLightAppTypeMation(inputObject, outputObject);
    }

    /**
     * 编辑轻应用类型时进行信息回显
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/queryLightAppTypeMationToEditById")
    public void queryLightAppTypeMationToEditById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.queryLightAppTypeMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑轻应用类型信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/editLightAppTypeMationById")
    public void editLightAppTypeMationById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.editLightAppTypeMationById(inputObject, outputObject);
    }

    /**
     * 轻应用类型展示顺序上移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/editLightAppTypeSortTopById")
    public void editLightAppTypeSortTopById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.editLightAppTypeSortTopById(inputObject, outputObject);
    }

    /**
     * 轻应用类型展示顺序下移
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/editLightAppTypeSortLowerById")
    public void editLightAppTypeSortLowerById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.editLightAppTypeSortLowerById(inputObject, outputObject);
    }

    /**
     * 删除轻应用类型
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/deleteLightAppTypeById")
    public void deleteLightAppTypeById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.deleteLightAppTypeById(inputObject, outputObject);
    }

    /**
     * 轻应用类型上线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/editLightAppTypeUpTypeById")
    public void editLightAppTypeUpTypeById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.editLightAppTypeUpTypeById(inputObject, outputObject);
    }

    /**
     * 轻应用类型下线
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/editLightAppTypeDownTypeById")
    public void editLightAppTypeDownTypeById(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.editLightAppTypeDownTypeById(inputObject, outputObject);
    }

    /**
     * 获取轻应用上线类型列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/LightAppTypeController/queryLightAppTypeUpList")
    public void queryLightAppTypeUpList(InputObject inputObject, OutputObject outputObject) {
        lightAppTypeService.queryLightAppTypeUpList(inputObject, outputObject);
    }

}
