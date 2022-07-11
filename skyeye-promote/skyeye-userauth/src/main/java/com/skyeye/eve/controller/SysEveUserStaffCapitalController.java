/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.controller;

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
public class SysEveUserStaffCapitalController {

    @Autowired
    private SysEveUserStaffCapitalService sysEveUserStaffCapitalService;

    /**
     * 根据月份以及部门查询未结算的额外资金
     *
     * @param inputObject
     * @param outputObject
     */
    @RequestMapping("/post/SysEveUserStaffCapitalController/queryStaffCapitalWaitPayMonthList")
    public void queryStaffCapitalWaitPayMonthList(InputObject inputObject, OutputObject outputObject) {
        sysEveUserStaffCapitalService.queryStaffCapitalWaitPayMonthList(inputObject, outputObject);
    }

}
