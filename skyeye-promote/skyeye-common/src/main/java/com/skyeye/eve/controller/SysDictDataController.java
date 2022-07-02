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
import com.skyeye.eve.eitity.dict.SysDictDataMation;
import com.skyeye.eve.eitity.dict.SysDictDataQueryDO;
import com.skyeye.eve.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysDictDataController
 * @Description: 数据字典管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:26
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "数据字典管理", tags = "数据字典管理", modelName = "系统公共模块")
public class SysDictDataController {

    @Autowired
    private SysDictDataService sysDictDataService;

    /**
     * 获取数据字典列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryDictDataList", value = "获取数据字典列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysDictDataQueryDO.class)
    @RequestMapping("/post/SysDictDataController/queryDictDataList")
    public void queryDictDataList(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.queryDictDataList(inputObject, outputObject);
    }

    /**
     * 新增/编辑数据字典
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "writeDictDataMation", value = "新增/编辑数据字典", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysDictDataMation.class)
    @RequestMapping("/post/SysDictDataController/writeDictDataMation")
    public void writeDictDataMation(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.writeDictDataMation(inputObject, outputObject);
    }

    /**
     * 根据ID获取数据字典信息
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryDictDataMationById", value = "根据ID获取数据字典信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysDictDataController/queryDictDataMationById")
    public void queryDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.queryDictDataMationById(inputObject, outputObject);
    }

    /**
     * 根据ID删除数据字典
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "deleteDictDataMationById", value = "根据ID删除数据字典", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysDictDataController/deleteDictDataMationById")
    public void deleteDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.deleteDictDataMationById(inputObject, outputObject);
    }

    /**
     * 根据所属类型Code获取数据字典列表
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "queryDictDataListByDictTypeCpde", value = "根据所属类型Code获取数据字典列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "dictTypeCpde", name = "dictTypeCpde", value = "所属类型Code", required = "required")})
    @RequestMapping("/post/SysDictDataController/queryDictDataListByDictTypeCpde")
    public void queryDictDataListByDictTypeCpde(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.queryDictDataListByDictTypeCpde(inputObject, outputObject);
    }

}
