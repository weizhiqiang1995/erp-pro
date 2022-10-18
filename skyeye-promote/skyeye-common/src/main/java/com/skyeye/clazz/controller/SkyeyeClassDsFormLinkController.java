/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.clazz.entity.dsformlink.SkyeyeClassDsFormLinkApiMation;
import com.skyeye.clazz.service.SkyeyeClassDsFormLinkService;
import com.skyeye.common.entity.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SkyeyeClassDsFormLinkController
 * @Description: 动态表单的服务类注册控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "动态表单的服务类注册", tags = "动态表单的服务类注册", modelName = "系统公共模块")
public class SkyeyeClassDsFormLinkController {

    @Autowired
    private SkyeyeClassDsFormLinkService skyeyeClassDsFormLinkService;

    /**
     * 动态表单的服务类注册
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeDsFormLink", value = "动态表单的服务类注册", method = "POST", allUse = "0")
    @ApiImplicitParams(classBean = SkyeyeClassDsFormLinkApiMation.class)
    @RequestMapping("/post/SkyeyeClassDsFormLinkController/writeDsFormLink")
    public void writeDsFormLink(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassDsFormLinkService.writeDsFormLink(inputObject, outputObject);
    }

    /**
     * 获取业务逻辑表单关联列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsFormObjectRelation001", value = "获取业务逻辑表单关联列表", method = "POST", allUse = "1")
    @ApiImplicitParams(classBean = CommonPageInfo.class)
    @RequestMapping("/post/SkyeyeClassDsFormLinkController/queryDsFormLinkList")
    public void queryDsFormLinkList(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassDsFormLinkService.queryDsFormLinkList(inputObject, outputObject);
    }

    /**
     * 获取业务逻辑表单关联信息详情
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsFormObjectRelation004", value = "获取业务逻辑表单关联信息详情", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/SkyeyeClassDsFormLinkController/queryDsFormLinkMationById")
    public void queryDsFormLinkMationById(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassDsFormLinkService.queryDsFormLinkMationById(inputObject, outputObject);
    }

    /**
     * 编辑业务逻辑表单关联信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsFormObjectRelation005", value = "编辑业务逻辑表单关联信息", method = "PUT", allUse = "1")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required"),
        @ApiImplicitParam(id = "dsFormPageIds", name = "dsFormPageIds", value = "动态表单的id，多个用逗号隔开")})
    @RequestMapping("/post/SkyeyeClassDsFormLinkController/editDsFormLinkMationById")
    public void editDsFormLinkMationById(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassDsFormLinkService.editDsFormLinkMationById(inputObject, outputObject);
    }

    /**
     * 根据code/id获取关联的动态表单信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "dsFormObjectRelation006", value = "根据code/id获取关联的动态表单信息", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "dsFormObjectRelationCode", name = "dsFormObjectRelationCode", value = "动态表单-----业务逻辑表单关联表的code，与id至少有一个必填"),
        @ApiImplicitParam(id = "dsFormObjectRelationId", name = "dsFormObjectRelationId", value = "动态表单-----业务逻辑表单关联表的id，与code至少有一个必填")})
    @RequestMapping("/post/SkyeyeClassDsFormLinkController/queryDsFormLinkListByCode")
    public void queryDsFormLinkListByCode(InputObject inputObject, OutputObject outputObject) {
        skyeyeClassDsFormLinkService.queryDsFormLinkListByCode(inputObject, outputObject);
    }

}
