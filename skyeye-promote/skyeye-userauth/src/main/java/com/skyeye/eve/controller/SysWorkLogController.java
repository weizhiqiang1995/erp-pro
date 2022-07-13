/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysWorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysWorkLogController {

    @Autowired
    private SysWorkLogService sysWorkLogService;

    /**
     * 获取日志列表
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysWorkLogController/querySysWorkLogList")
    public void querySysWorkLogList(InputObject inputObject, OutputObject outputObject) {
        sysWorkLogService.querySysWorkLogList(inputObject, outputObject);
    }
}
