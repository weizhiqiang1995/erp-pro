/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysQuartzController {

    @Autowired
    private SysQuartzService sysQuartzService;

    /**
     * 获取系统定时任务列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysQuartzController/querySystemQuartzList")
    public void querySystemQuartzList(InputObject inputObject, OutputObject outputObject) {
        sysQuartzService.querySystemQuartzList(inputObject, outputObject);
    }

    /**
     * 获取我的定时任务列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysQuartzController/queryMyTaskQuartzList")
    public void queryMyTaskQuartzList(InputObject inputObject, OutputObject outputObject) {
        sysQuartzService.queryMyTaskQuartzList(inputObject, outputObject);
    }

    /**
     * 启动系统定时任务
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysQuartzController/runSystemQuartz")
    public void runSystemQuartz(InputObject inputObject, OutputObject outputObject) {
        sysQuartzService.runSystemQuartz(inputObject, outputObject);
    }

}
