/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysQuartzRunHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysQuartzRunHistoryController
 * @Description: 系统定时任务启动历史控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/29 11:53
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
public class SysQuartzRunHistoryController {

    @Autowired
    private SysQuartzRunHistoryService sysQuartzRunHistoryService;

    /**
     * 获取系统定时任务启动历史列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysQuartzRunHistoryController/querySysQuartzRunHistoryByQuartzId")
    public void querySysQuartzRunHistoryByQuartzId(InputObject inputObject, OutputObject outputObject) {
        sysQuartzRunHistoryService.querySysQuartzRunHistoryByQuartzId(inputObject, outputObject);
    }

}
