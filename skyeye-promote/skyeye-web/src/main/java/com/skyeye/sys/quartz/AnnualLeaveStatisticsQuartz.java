/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sys.quartz;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.util.CalculationUtil;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.personnel.dao.SysEveUserStaffDao;
import com.skyeye.eve.service.ISystemFoundationSettingsService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: AnnualLeaveStatisticsQuartz
 * @Description: 定时计算员工年假
 * @author: skyeye云系列--卫志强
 * @date: 2021/4/5 19:17
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Component
public class AnnualLeaveStatisticsQuartz {

    private static Logger LOGGER = LoggerFactory.getLogger(AnnualLeaveStatisticsQuartz.class);

    @Autowired
    private ISystemFoundationSettingsService iSystemFoundationSettingsService;

    @Autowired
    private SysEveUserStaffDao sysEveUserStaffDao;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private enum YearLimits {
        ONE_YEAR(0, 1, "1", "1年以下"),
        ONE_THREE_YEAR(1, 3, "2", "1年 ~ 3年"),
        THREE_FIVE_YEAR(3, 5, "3", "3年 ~ 5年"),
        FIVE_EIGHT_YEAR(5, 8, "4", "5年 ~ 8年"),
        EIGHT_YEAR(8, 100, "5", "8年以上");

        private int min;
        private int max;
        private String yearType;
        private String title;
    }

    /**
     * 每个季度的第一天零点开始执行员工年假计算任务
     */
    @XxlJob("annualLeaveStatisticsQuartz")
    public void annualLeaveStatistics() {
        LOGGER.info("annualLeaveStatistics start.");
        try {
            // 1.获取年假信息
            List<Map<String, Object>> yearHolidaysMation = getSystemYearHolidaysMation();
            // 2.获取所有在职状态的员工列表, 见习，试用，退休，离职员工不计入计算
            List<Map<String, Object>> userStaff = sysEveUserStaffDao.queryAllSysUserStaffListByState(Arrays.asList(new Integer[]{1}));
            // 获取当前年月日
            String nowDate = DateUtil.getYmdTimeAndToString();
            for (Map<String, Object> staff : userStaff) {
                String staffId = staff.get("id").toString();
                // 员工当前剩余年假
                String annualLeave = staff.get("annualLeave").toString();
                // 开始工作时间
                String workTime = staff.get("workTime").toString();
                // 获取到相差的天数
                int differDays = DateUtil.getDistanceDay(workTime, nowDate);
                String differYear = getDifferYear(differDays);
                Map<String, Object> yearMation = getConcertWithYearMation(Integer.parseInt(differYear), yearHolidaysMation);
                if (!ObjectUtils.isEmpty(yearMation)) {
                    String yearHour = yearMation.get("yearHour").toString();
                    // 获取每个季度应该相加的年假小时
                    String quarterYearHour = CalculationUtil.divide(yearHour, "4", 2);
                    annualLeave = CalculationUtil.add(annualLeave, quarterYearHour, 2);
                    LOGGER.info("annualLeaveStatistics calc staffId is: {} quarterYearHour is: {}", staffId, annualLeave);
                    // 更新员工年假信息
                    sysEveUserStaffDao.editSysUserStaffAnnualLeaveById(staffId, annualLeave, DateUtil.getTimeAndToString());
                }
            }
        } catch (Exception e) {
            LOGGER.warn("AnnualLeaveStatisticsQuartz error.", e);
        }
        LOGGER.info("annualLeaveStatistics end.");
    }

    /**
     * 筛选已工作年份应该获取的年假信息
     *
     * @param differYear         已工作年份
     * @param yearHolidaysMation 年假信息
     * @return
     */
    private Map<String, Object> getConcertWithYearMation(int differYear, List<Map<String, Object>> yearHolidaysMation) {
        for (YearLimits q : YearLimits.values()) {
            if (q.getMin() <= differYear && differYear < q.getMax()) {
                List<Map<String, Object>> fillterMation = yearHolidaysMation.stream()
                    .filter(bean -> bean.get("yearType").toString().equals(q.getYearType()))
                    .collect(Collectors.toList());
                if (fillterMation == null || fillterMation.isEmpty()) {
                    return null;
                }
                return fillterMation.get(0);
            }
        }
        return null;
    }

    /**
     * 计算多少年，小数点后面的全部舍去
     *
     * @param differDays 相差的天数
     * @return
     */
    private String getDifferYear(int differDays) {
        String year = CalculationUtil.divide(String.valueOf(differDays), "365", 2);
        if (ToolUtil.isBlank(year)) {
            return "0";
        }
        return year.split("\\.")[0];
    }

    /**
     * 获取年假信息
     *
     * @return
     */
    private List<Map<String, Object>> getSystemYearHolidaysMation() {
        Map<String, Object> sysSetting = iSystemFoundationSettingsService.querySystemFoundationSettingsList();
        String yearHolidaysMationStr = sysSetting.get("yearHolidaysMation").toString();
        return JSONUtil.toList(yearHolidaysMationStr, null);
    }

}
