/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.rest.checkwork;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: CheckWorkTimeService
 * @Description: 考勤班次模块接口类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 21:10
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-flowable}", configuration= ClientConfiguration.class)
public interface CheckWorkTimeService {

    /**
     * 根据指定年月获取所有的考勤班次的信息以及工作日信息等
     *
     * @param pointMonthDate 指定年月，格式为yyyy-MM
     * @return 所有的考勤班次的信息以及工作日信息等
     */
    @GetMapping("/getAllCheckWorkTime")
    String getAllCheckWorkTime(@RequestParam("pointMonthDate") String pointMonthDate);

    /**
     * 获取所有未删除的考勤班次
     *
     * @return 所有未删除的考勤班次
     */
    @GetMapping("/queryAllStaffCheckWorkTime")
    String queryAllStaffCheckWorkTime();

    /**
     * 获取指定考勤班次的工作日信息
     *
     * @param timeId 考勤班次id
     * @return 指定考勤班次的工作日信息
     */
    @GetMapping("/queryWeekDayByTimeId")
    String queryWeekDayByTimeId(@RequestParam("timeId") String timeId);

}
