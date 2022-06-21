/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysEveMenuAuthPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysEveMenuAuthPointController {

    @Autowired
    private SysEveMenuAuthPointService sysEveMenuAuthPointService;

    /**
     * 获取菜单权限点列表根据菜单id
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointListByMenuId")
    public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveMenuAuthPointService.querySysEveMenuAuthPointListByMenuId(inputObject, outputObject);
    }

    /**
     * 添加菜单权限点
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/insertSysEveMenuAuthPointMation")
    public void insertSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveMenuAuthPointService.insertSysEveMenuAuthPointMation(inputObject, outputObject);
    }

    /**
     * 编辑菜单权限点时进行回显
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/querySysEveMenuAuthPointMationToEditById")
    public void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveMenuAuthPointService.querySysEveMenuAuthPointMationToEditById(inputObject, outputObject);
    }

    /**
     * 编辑菜单权限点
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/editSysEveMenuAuthPointMationById")
    public void editSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveMenuAuthPointService.editSysEveMenuAuthPointMationById(inputObject, outputObject);
    }

    /**
     * 删除菜单权限点
     *
     * @param inputObject
     * @param outputObject
     * @throws Exception
     */
    @RequestMapping("/post/SysEveMenuAuthPointController/deleteSysEveMenuAuthPointMationById")
    public void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception {
        sysEveMenuAuthPointService.deleteSysEveMenuAuthPointMationById(inputObject, outputObject);
    }

}