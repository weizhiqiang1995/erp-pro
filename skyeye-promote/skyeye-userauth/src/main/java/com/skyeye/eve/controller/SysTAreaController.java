/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.TableSelectInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysTAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysTAreaController
 * @Description: 行政区划管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/18 23:47
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "行政区划", tags = "行政区划", modelName = "基础模块")
public class SysTAreaController {

    @Autowired
    private SysTAreaService sysTAreaService;

    /**
     * 获取行政区划列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "systarea001", value = "获取行政区划列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = TableSelectInfo.class)
    @RequestMapping("/post/SysTAreaController/querySysTAreaList")
    public void querySysTAreaList(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.queryList(inputObject, outputObject);
    }

    /**
     * 根据父id获取子节点信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAreaListByPId", value = "根据父id获取子节点信息", method = "POST", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "pId", name = "pId", value = "主键id", required = "required")})
    @RequestMapping("/post/SysTAreaController/queryAreaListByPId")
    public void queryAreaListByPId(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.queryAreaListByPId(inputObject, outputObject);
    }

    /**
     * 根据id批量获取节点信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryAreaListByIds", value = "根据id批量获取节点信息", method = "POST", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "ids", name = "ids", value = "主键id，多个用逗号隔开", required = "required")})
    @RequestMapping("/post/SysTAreaController/queryAreaListByIds")
    public void queryAreaListByIds(InputObject inputObject, OutputObject outputObject) {
        sysTAreaService.selectByIds(inputObject, outputObject);
    }

}
