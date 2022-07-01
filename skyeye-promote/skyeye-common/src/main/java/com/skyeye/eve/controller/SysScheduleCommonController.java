/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

import com.skyeye.annotation.api.Api;
import com.skyeye.annotation.api.ApiImplicitParam;
import com.skyeye.annotation.api.ApiImplicitParams;
import com.skyeye.annotation.api.ApiOperation;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.service.SysScheduleCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysScheduleCommonController
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 20:17
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "日程管理", tags = "日程管理", modelName = "日程模块")
public class SysScheduleCommonController {

    @Autowired
    private SysScheduleCommonService sysScheduleCommonService;

    /**
     * 判断指定日期是否是节假日
     *
     * @param inputObject
     * @param outputObject
     */
    @ApiOperation(id = "judgeISHoliday", value = "判断指定日期是否是节假日", method = "GET", allUse = "2")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "day", name = "day", value = "日期，格式为yyyy-mm-dd", required = "required")})
    @RequestMapping("/post/SysScheduleCommonController/judgeISHoliday")
    public void judgeISHoliday(InputObject inputObject, OutputObject outputObject) {
        sysScheduleCommonService.judgeISHoliday(inputObject, outputObject);
    }

}
