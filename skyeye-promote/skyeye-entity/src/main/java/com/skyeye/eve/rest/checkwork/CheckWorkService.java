/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.rest.checkwork;

import com.skyeye.common.client.ClientConfiguration;
import com.skyeye.eve.entity.checkwork.CheckWorkMationRest;
import com.skyeye.eve.entity.checkwork.DayWorkMationRest;
import com.skyeye.eve.entity.checkwork.UserOtherDayMationRest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName: CheckWorkService
 * @Description: 考勤模块接口信息
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 20:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-flowable}", configuration = ClientConfiguration.class)
public interface CheckWorkService {

    /**
     * 获取节假日信息
     *
     * @param dayWorkMationRest 入参信息
     */
    @PostMapping("/queryDayWorkMation")
    String queryDayWorkMation(DayWorkMationRest dayWorkMationRest);

    /**
     * 获取用户指定班次在指定月份的其他日期信息[审核通过的](例如：请假，出差，加班等)
     *
     * @param userOtherDayMationRest 入参信息
     */
    @PostMapping("/getUserOtherDayMation")
    String getUserOtherDayMation(UserOtherDayMationRest userOtherDayMationRest);

    /**
     * 获取所有昨天没有打卡的用户
     *
     * @param timeId        班次id
     * @param yesterdayTime 日期信息,格式为：yyyy-MM-dd
     * @return 所有的考勤班次的信息以及工作日信息等
     */
    @GetMapping("/queryNotCheckMember")
    String queryNotCheckMember(@RequestParam("timeId") String timeId,
                               @RequestParam("yesterdayTime") String yesterdayTime);

    /**
     * 获取所有昨天没有打下班卡的用户
     *
     * @param timeId        班次id
     * @param yesterdayTime 日期信息,格式为：yyyy-MM-dd
     * @return 所有的考勤班次的信息以及工作日信息等
     */
    @GetMapping("/queryNotCheckEndWorkId")
    String queryNotCheckEndWorkId(@RequestParam("timeId") String timeId,
                                  @RequestParam("yesterdayTime") String yesterdayTime);

    /**
     * 填充下班卡信息
     *
     * @param checkWorkMation 入参信息
     */
    @PostMapping("/editCheckWorkBySystem")
    String editCheckWorkBySystem(CheckWorkMationRest checkWorkMation);

    /**
     * 获取所有待结算的加班数据
     */
    @GetMapping("/queryCheckWorkOvertimeWaitSettlement")
    String queryCheckWorkOvertimeWaitSettlement();

    /**
     * 修改加班电子流的结算状态
     *
     * @param params 入参
     * @return
     */
    @PutMapping("/updateOvertimeSettleState")
    String updateOvertimeSettleState(Map<String, Object> params);

    /**
     * 新增打卡信息(用于新增旷工的考勤信息)
     *
     * @param jsonStr 打卡信息，json字符串
     * @return
     */
    @PostMapping("/insertCheckWorkBySystem")
    String insertCheckWorkBySystem(@RequestParam("jsonStr") String jsonStr);

}
