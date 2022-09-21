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
import com.skyeye.eve.entity.barcode.BarCodeApiMation;
import com.skyeye.eve.service.BarCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: BarCodeController
 * @Description: 条形码管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/28 9:41
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "条形码", tags = "条形码", modelName = "系统公共模块")
public class BarCodeController {

    @Autowired
    private BarCodeService barCodeService;

    /**
     * 批量新增条形码
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeBarCode", value = "批量新增条形码", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = BarCodeApiMation.class)
    @RequestMapping("/post/BarCodeController/writeBarCode")
    public void writeBarCode(InputObject inputObject, OutputObject outputObject) {
        barCodeService.writeBarCode(inputObject, outputObject);
    }

    /**
     * 根据条形码code获取数据信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "getDataByBarCode", value = "根据条形码code获取数据信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "barCode", name = "barCode", value = "条形码code", required = "required")})
    @RequestMapping("/post/BarCodeController/getDataByBarCode")
    public void getDataByBarCode(InputObject inputObject, OutputObject outputObject) {
        barCodeService.getDataByBarCode(inputObject, outputObject);
    }

    /**
     * 根据业务数据id获取条形码数据
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryBarCodeByObjectIds", value = "根据业务数据id获取条形码数据", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "springApplicationName", name = "springApplicationName", value = "服务名", required = "required"),
        @ApiImplicitParam(id = "codeImplClass", name = "codeImplClass", value = "条形码类型，对应的服务层类地址", required = "required"),
        @ApiImplicitParam(id = "objectIds", name = "objectIds", value = "业务数据id集合", required = "required,json")})
    @RequestMapping("/post/BarCodeController/queryBarCodeByObjectIds")
    public void queryBarCodeByObjectIds(InputObject inputObject, OutputObject outputObject) {
        barCodeService.queryBarCodeByObjectIds(inputObject, outputObject);
    }

}
