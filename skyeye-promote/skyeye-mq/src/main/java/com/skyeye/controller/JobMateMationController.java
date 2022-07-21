/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.entity.mq.JobMateQueryDO;
import com.skyeye.service.JobMateMationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "任务管理", tags = "任务管理", modelName = "任务模块")
public class JobMateMationController {

    @Autowired
    private JobMateMationService jobMateMationService;

    /**
     * 根据大类获取任务信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "jobmatemation001", value = "根据大类获取任务信息", method = "POST", allUse = "2")
    @ApiImplicitParams(classBean = JobMateQueryDO.class)
    @RequestMapping("/post/JobMateMationController/queryJobMateMationByBigTypeList")
    public void queryJobMateMationByBigTypeList(InputObject inputObject, OutputObject outputObject) {
        jobMateMationService.queryJobMateMationByBigTypeList(inputObject, outputObject);
    }

    /**
     * 其他模块同步生产消息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "sendMQProducer", value = "其他模块同步生产消息", method = "POST", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "jsonStr", name = "jsonStr", value = "消费者消费的json字符串", required = "required"),
        @ApiImplicitParam(id = "userId", name = "userId", value = "用户id", required = "required")})
    @RequestMapping("/post/JobMateMationController/sendMQProducer")
    public void sendMQProducer(InputObject inputObject, OutputObject outputObject) {
        jobMateMationService.sendMQProducer(inputObject, outputObject);
    }


}
