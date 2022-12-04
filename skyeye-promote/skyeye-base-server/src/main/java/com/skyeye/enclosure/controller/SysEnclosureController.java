/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.catalog.entity.CatalogBusinessQueryDo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.enclosure.entity.Enclosure;
import com.skyeye.enclosure.service.SysEnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEnclosureController
 * @Description: 附件管理控制类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:39
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "附件管理", tags = "附件管理", modelName = "基本服务")
public class SysEnclosureController {

    @Autowired
    private SysEnclosureService sysEnclosureService;

    /**
     * 获取指定文件夹下的文件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sysenclosure004", value = "获取指定文件夹下的文件", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = CatalogBusinessQueryDo.class)
    @RequestMapping("/post/SysEnclosureController/queryEnclosureList")
    public void queryEnclosureList(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增附件
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "createEnclosure", value = "新增附件", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Enclosure.class)
    @RequestMapping("/post/SysEnclosureController/createEnclosure")
    public void createEnclosure(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.createEntity(inputObject, outputObject);
    }

    /**
     * 根据ids(逗号隔开)获取多个附件信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnclosureInfoByIds", value = "根据ids(逗号隔开)获取多个附件信息", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "enclosureInfoIds", name = "enclosureInfoIds", value = "附件id(多个用逗号隔开)")})
    @RequestMapping("/post/SysEnclosureController/queryEnclosureInfo")
    public void queryEnclosureInfo(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryEnclosureInfo(inputObject, outputObject);
    }

    /**
     * 获取我的附件树
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryEnclosureTree", value = "获取我的附件树", method = "GET", allUse = "2")
    @RequestMapping("/post/SysEnclosureController/queryEnclosureTree")
    public void queryEnclosureTree(InputObject inputObject, OutputObject outputObject) {
        sysEnclosureService.queryEnclosureTree(inputObject, outputObject);
    }

}
