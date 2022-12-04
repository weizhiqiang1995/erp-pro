/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.catalog.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.catalog.entity.Catalog;
import com.skyeye.catalog.service.CatalogService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: CatalogController
 * @Description: 目录管理控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:58
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "目录管理", tags = "目录管理", modelName = "基本服务")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    /**
     * 一次性获取所有的目录为树结构
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCatalogForTree", value = "一次性获取所有的目录为树结构", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "所属第三方业务数据id"),
        @ApiImplicitParam(id = "objectKey", name = "objectKey", value = "所属第三方业务数据的key", required = "required"),
        @ApiImplicitParam(id = "addOrUser", name = "addOrUser", value = "是否根据创建人查询", required = "required", defaultValue = "false")})
    @RequestMapping("/post/CatalogController/queryCatalogForTree")
    public void queryCatalogForTree(InputObject inputObject, OutputObject outputObject) {
        catalogService.queryCatalogForTree(inputObject, outputObject);
    }

    /**
     * 一次性获取所有的目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCatalogList", value = "一次性获取所有的目录", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "objectId", name = "objectId", value = "所属第三方业务数据id"),
        @ApiImplicitParam(id = "objectKey", name = "objectKey", value = "所属第三方业务数据的key", required = "required"),
        @ApiImplicitParam(id = "addOrUser", name = "addOrUser", value = "是否根据创建人查询", required = "required", defaultValue = "false")})
    @RequestMapping("/post/CatalogController/queryCatalogList")
    public void queryCatalogList(InputObject inputObject, OutputObject outputObject) {
        catalogService.queryCatalogList(inputObject, outputObject);
    }

    /**
     * 新增/编辑目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeCatalog", value = "新增/编辑目录", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Catalog.class)
    @RequestMapping("/post/CatalogController/writeCatalog")
    public void writeCatalog(InputObject inputObject, OutputObject outputObject) {
        catalogService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 删除目录
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteCatalogById", value = "删除目录", method = "DELETE", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "目录id", required = "required")})
    @RequestMapping("/post/CatalogController/deleteCatalogById")
    public void deleteCatalogById(InputObject inputObject, OutputObject outputObject) {
        catalogService.deleteById(inputObject, outputObject);
    }

    /**
     * 根据id批量获取目录信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryCatalogByIds", value = "根据id批量获取目录信息", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = Catalog.class)
    @RequestMapping("/post/CatalogController/queryCatalogByIds")
    public void queryCatalogByIds(InputObject inputObject, OutputObject outputObject) {
        catalogService.selectByIds(inputObject, outputObject);
    }

}
