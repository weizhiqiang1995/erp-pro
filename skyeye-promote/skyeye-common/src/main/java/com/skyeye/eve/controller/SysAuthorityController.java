/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.eve.service.SysAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @ClassName: SysAuthorityController
 * @Description: 权限服务控制层
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/2 15:12
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Controller
@Api(value = "角色管理", tags = "角色管理", modelName = "基础模块")
public class SysAuthorityController {

    @Autowired
    private SysAuthorityService sysAuthorityService;

    /**
     * 根据角色ID(逗号隔开的字符串)获取该角色拥有的菜单列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "getRoleHasMenuListByRoleIds", value = "根据角色ID(逗号隔开的字符串)获取该角色拥有的菜单列表", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "roleIds", name = "roleIds", value = "角色id(逗号隔开的字符串)", required = "required"),
        @ApiImplicitParam(id = "token", name = "token", value = "用户token", required = "required")})
    @RequestMapping("/post/roleAuth/getRoleHasMenuListByRoleIds")
    @ResponseBody
    public void getRoleHasMenuListByRoleIds(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysAuthorityService.getRoleHasMenuListByRoleIds(inputObject, outputObject);
    }

    /**
     * 根据角色ID(逗号隔开的字符串)获取该角色拥有的菜单权限点列表
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @ApiOperation(id = "getRoleHasMenuPointListByRoleIds", value = "根据角色ID(逗号隔开的字符串)获取该角色拥有的菜单权限点列表", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "roleIds", name = "roleIds", value = "角色id(逗号隔开的字符串)", required = "required"),
        @ApiImplicitParam(id = "token", name = "token", value = "用户token", required = "required")})
    @RequestMapping("/post/roleAuth/getRoleHasMenuPointListByRoleIds")
    @ResponseBody
    public void getRoleHasMenuPointListByRoleIds(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysAuthorityService.getRoleHasMenuPointListByRoleIds(inputObject, outputObject);
    }

}
