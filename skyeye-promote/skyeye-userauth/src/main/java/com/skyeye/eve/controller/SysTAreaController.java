/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysTAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysTAreaController {

    @Autowired
    private SysTAreaService sysTAreaService;

    /**
     * 获取行政区划信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysTAreaController/querySysTAreaList")
    public void querySysTAreaList(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.querySysTAreaList(inputObject, outputObject);
    }

    /**
     * 获取一级省行政区划信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysTAreaController/querySysTAreaProvinceList")
    public void querySysTAreaProvinceList(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.querySysTAreaProvinceList(inputObject, outputObject);
    }

    /**
     * 获取二级市行政区划信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysTAreaController/querySysTAreaCityList")
    public void querySysTAreaCityList(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.querySysTAreaCityList(inputObject, outputObject);
    }

    /**
     * 获取三级县行政区划信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysTAreaController/querySysTAreaChildAreaList")
    public void querySysTAreaChildAreaList(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.querySysTAreaChildAreaList(inputObject, outputObject);
    }

    /**
     * 获取四级镇行政区划信息
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysTAreaController/querySysTAreaTownShipList")
    public void querySysTAreaTownShipList(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.querySysTAreaTownShipList(inputObject, outputObject);
    }

}
