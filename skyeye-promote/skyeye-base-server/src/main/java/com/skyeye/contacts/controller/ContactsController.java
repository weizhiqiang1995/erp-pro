/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.contacts.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.contacts.service.ContactsService;
import com.skyeye.contacts.entity.Contacts;
import com.skyeye.eve.entity.object.query.BaseServerQueryDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ContactsController
 * @Description: 联系人管理
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/26 14:51
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "联系人管理", tags = "联系人管理", modelName = "基本服务")
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    /**
     * 获取联系人列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryContactsList", value = "获取联系人列表", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = BaseServerQueryDo.class)
    @RequestMapping("/post/ContactsController/queryContactsList")
    public void queryContactsList(InputObject inputObject, OutputObject outputObject) {
        contactsService.queryPageList(inputObject, outputObject);
    }

    /**
     * 新增/编辑联系人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "writeContactsMation", value = "新增/编辑联系人信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = Contacts.class)
    @RequestMapping("/post/ContactsController/writeContactsMation")
    public void writeContactsMation(InputObject inputObject, OutputObject outputObject) {
        contactsService.saveOrUpdateEntity(inputObject, outputObject);
    }

    /**
     * 根据id获取联系人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "queryContactsMationById", value = "根据id获取联系人信息", method = "GET", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ContactsController/queryContactsMationById")
    public void queryContactsMationById(InputObject inputObject, OutputObject outputObject) {
        contactsService.selectById(inputObject, outputObject);
    }

    /**
     * 根据id删除联系人信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "deleteContactsMationById", value = "根据id删除联系人信息", method = "DELETE", allUse = "2")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(id = "id", name = "id", value = "主键id", required = "required")})
    @RequestMapping("/post/ContactsController/deleteContactsMationById")
    public void deleteContactsMationById(InputObject inputObject, OutputObject outputObject) {
        contactsService.deleteById(inputObject, outputObject);
    }

}
