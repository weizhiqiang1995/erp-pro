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
import com.skyeye.eve.service.SysEveUserStaffCapitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysEveUserStaffCapitalController
 * @Description: 员工非工资型的额外资金结算池控制类
 * @author: skyeye云系列--卫志强
 * @date: 2021/9/4 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@RestController
@Api(value = "未结算资金池相关接口", tags = "未结算资金池相关接口", modelName = "基础模块")
public class SysEveUserStaffCapitalController {

    @Autowired
    private SysEveUserStaffCapitalService sysEveUserStaffCapitalService;

    /**
     * 新增员工待结算资金池信息(用于定时任务)
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @ApiOperation(id = "addMonthMoney2StaffCapital", value = "新增员工待结算资金池信息(用于定时任务)", method = "POST", allUse = "0")
    @ApiImplicitParams({
        @ApiImplicitParam(id = "staffId", name = "staffId", value = "员工id", required = "required"),
        @ApiImplicitParam(id = "companyId", name = "companyId", value = "企业id", required = "required"),
        @ApiImplicitParam(id = "departmentId", name = "departmentId", value = "部门id", required = "required"),
        @ApiImplicitParam(id = "monthTime", name = "monthTime", value = "指定年月，格式为：yyyy-MM", required = "required"),
        @ApiImplicitParam(id = "type", name = "type", value = "该资金来源类型", required = "required,num"),
        @ApiImplicitParam(id = "money", name = "money", value = "金额", required = "required")})
    @RequestMapping("/post/SysEveUserStaffCapitalController/addMonthMoney2StaffCapital")
    public void addMonthMoney2StaffCapital(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffCapitalService.addMonthMoney2StaffCapital(inputObject, outputObject);
    }

    /**
     * 根据月份以及部门查询未结算的额外资金
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @RequestMapping("/post/SysEveUserStaffCapitalController/queryStaffCapitalWaitPayMonthList")
    public void queryStaffCapitalWaitPayMonthList(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffCapitalService.queryStaffCapitalWaitPayMonthList(inputObject, outputObject);
    }

}
