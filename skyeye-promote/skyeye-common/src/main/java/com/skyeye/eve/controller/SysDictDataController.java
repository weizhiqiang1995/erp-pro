/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.dict.SysDictData;
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
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictDataList", value = "获取数据字典列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class, value = {
        @ApiImplicitParam(id = "dictTypeId", name = "dictTypeId", value = "数据字典分类id")})
    @RequestMapping("/post/SysDictDataController/queryDictDataList")
    public void queryDictDataList(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑数据字典
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDictDataMation", value = "新增/编辑数据字典", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = SysDictData.class)
    @RequestMapping("/post/SysDictDataController/writeDictDataMation")
    public void writeDictDataMation(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据ID获取数据字典信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictDataMationById", value = "根据ID获取数据字典信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysDictDataController/queryDictDataMationById")
    public void queryDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.selectById(inputObject, outputObject);
    }

    /**
     * 根据IDs批量获取数据字典信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictDataMationByIds", value = "根据IDs批量获取数据字典信息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "ids", name = "ids", value = "主键id，多个用逗号隔开", required = "required")})
    @RequestMapping("/post/SysDictDataController/queryDictDataMationByIds")
    public void queryDictDataMationByIds(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.selectByIds(inputObject, outputObject);
    }

    /**
     * 根据ID删除数据字典
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteDictDataMationById", value = "根据ID删除数据字典", method = "DELETE", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SysDictDataController/deleteDictDataMationById")
    public void deleteDictDataMationById(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据所属类型Code获取数据字典列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictDataListByDictTypeCode", value = "根据所属类型Code获取数据字典列表", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "dictTypeCode", name = "dictTypeCode", value = "所属类型Code", required = "required")})
    @RequestMapping("/post/SysDictDataController/queryDictDataListByDictTypeCode")
    public void queryDictDataListByDictTypeCode(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.queryDictDataListByDictTypeCode(inputObject, outputObject);
    }

    /**
     * 获取指定分类下不等于指定ID的数据字典集合
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryDictDataListByDictTypeCodeAndNotId", value = "获取指定分类下不等于指定ID的数据字典集合", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "dictTypeCode", name = "dictTypeCode", value = "所属类型Code", required = "required"),
        @ApiImplicitParam(id = "notId", name = "notId", value = "不等于这个ID")})
    @RequestMapping("/post/SysDictDataController/queryDictDataListByDictTypeCodeAndNotId")
    public void queryDictDataListByDictTypeCodeAndNotId(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.queryDictDataListByDictTypeCodeAndNotId(inputObject, outputObject);
    }

    /**
     * 移动位置
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "setDictDataParent", value = "移动位置", method = "PUT", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "数据字典id", required = "required"),
        @ApiImplicitParam(id = "parentId", name = "parentId", value = "父节点id", required = "required")})
    @RequestMapping("/post/SysDictDataController/setDictDataParent")
    public void setDictDataParent(InputObject inputObject, OutputObject outputObject) {
        sysDictDataService.setDictDataParent(inputObject, outputObject);
    }

}
