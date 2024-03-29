/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysDataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysDataBaseController {

    @Autowired
    private SysDataBaseService sysDataBaseService;

    /**
     * 获取数据库表名信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysDataBaseController/querySysDataBaseSelectList")
    public void querySysDataBaseSelectList(InputObject inputObject, OutputObject outputObject) {
        sysDataBaseService.querySysDataBaseSelectList(inputObject, outputObject);
    }

    /**
     * 获取数据库表备注信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysDataBaseController/querySysDataBaseDescSelectList")
    public void querySysDataBaseDescSelectList(InputObject inputObject, OutputObject outputObject) {
        sysDataBaseService.querySysDataBaseDescSelectList(inputObject, outputObject);
    }

}
