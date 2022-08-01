/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.QuartzConstants;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.*;
import com.skyeye.eve.dao.ScheduleDayDao;
import com.skyeye.eve.entity.checkwork.DayWorkMationRest;
import com.skyeye.eve.entity.checkwork.UserOtherDayMationRest;
import com.skyeye.eve.entity.schedule.ScheduleDayQueryDo;
import com.skyeye.eve.rest.checkwork.CheckWorkService;
import com.skyeye.eve.rest.quartz.SysQuartzMation;
import com.skyeye.eve.rest.schedule.OtherModuleScheduleMation;
import com.skyeye.eve.rest.schedule.ScheduleMation;
import com.skyeye.eve.service.IQuartzService;
import com.skyeye.eve.service.ScheduleDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName: ScheduleDayServiceImpl
 * @Description: 日程相关
 * @author: skyeye云系列--卫志强
 * @date: 2021/4/24 11:43
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Service
public class ScheduleDayServiceImpl implements ScheduleDayService {

    private static Logger LOGGER = LoggerFactory.getLogger(ScheduleDayServiceImpl.class);

    @Autowired
    private ScheduleDayDao scheduleDayDao;

    @Autowired
    private IQuartzService iQuartzService;

    @Autowired
    private CheckWorkService checkWorkService;

    /**
     * 添加日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void insertScheduleDayMation(InputObject inputObject, OutputObject outputObject) {
        ScheduleMation scheduleMation = inputObject.getParams(ScheduleMation.class);
        String scheduleStartTime = scheduleMation.getScheduleStartTime();
        String remindTime = DateAfterSpacePointTime.getSpecifiedTime(scheduleMation.getRemindType(), scheduleStartTime,
            DateUtil.YYYY_MM_DD_HH_MM_SS, DateAfterSpacePointTime.AroundType.BEFORE);

        if (!ToolUtil.isBlank(remindTime)) {
            if (DateUtil.compare(remindTime, DateUtil.getTimeAndToString())) {
                // 日程提醒时间早于当前时间
                outputObject.setreturnMessage("日程提醒时间不能早于当前时间");
            } else {
                if (DateUtil.compare(scheduleMation.getScheduleEndTime(), scheduleStartTime)) {
                    // 结束时间早于开始时间
                    outputObject.setreturnMessage("日程结束时间不能早于开始时间");
                } else {
                    scheduleMation.setRemindTime(remindTime);
                    DataCommonUtil.setCommonDataByGenericity(remindTime, inputObject.getLogParams().get("id").toString());
                    scheduleDayDao.insert(scheduleMation);
                    // 定时任务
                    startUpTaskQuartz(scheduleMation.getId(), scheduleMation.getScheduleTitle(), remindTime);
                    outputObject.setBean(scheduleMation);
                }
            }
        } else {
            outputObject.setreturnMessage("参数错误");
        }
    }

    private void startUpTaskQuartz(String name, String title, String delayedTime) {
        SysQuartzMation sysQuartzMation = new SysQuartzMation();
        sysQuartzMation.setName(name);
        sysQuartzMation.setTitle(title);
        sysQuartzMation.setDelayedTime(delayedTime);
        sysQuartzMation.setGroupId(QuartzConstants.QuartzMateMationJobType.MY_SCHEDULEDAY_MATION.getTaskType());
        iQuartzService.startUpTaskQuartz(sysQuartzMation);
    }

    /**
     * 获取当前用户的日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryScheduleDayMationByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        String userId = inputObject.getLogParams().get("id").toString();
        String yearMonth = map.get("yearMonth").toString();
        String timeId = map.get("checkWorkId").toString();
        List<String> months = DateUtil.getPointMonthAfterMonthList(yearMonth, 2);
        // 1.获取当前登录人指定月份的日程信息
        List<Map<String, Object>> beans = scheduleDayDao.queryScheduleDayMationByUserId(userId, months);
        DayWorkMationRest dayWorkMationParams = new DayWorkMationRest();
        dayWorkMationParams.setBeans(beans);
        dayWorkMationParams.setMonths(months);
        dayWorkMationParams.setTimeId(timeId);
        beans = ExecuteFeignClient.get(() -> checkWorkService.queryDayWorkMation(dayWorkMationParams)).getRows();
        // 2.获取用户指定班次在指定月份的其他日期信息[审核通过的](例如：请假，出差，加班等)
        UserOtherDayMationRest userOtherDayMationParams = new UserOtherDayMationRest();
        userOtherDayMationParams.setTimeId(timeId);
        userOtherDayMationParams.setUserId(userId);
        userOtherDayMationParams.setMonths(months);
        beans.addAll(ExecuteFeignClient.get(() -> checkWorkService.getUserOtherDayMation(userOtherDayMationParams)).getRows());
        outputObject.setBeans(beans);
    }

    /**
     * 根据用户获取今日日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryScheduleDayMationTodayByUserId(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> user = inputObject.getLogParams();
        map.put("userId", user.get("id"));
        List<Map<String, Object>> beans = scheduleDayDao.queryScheduleDayMationTodayByUserId(map);
        outputObject.setBeans(beans);
        if (beans != null && !beans.isEmpty()) {
            outputObject.settotal(beans.size());
        }
    }

    /**
     * 修改日程日期信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void editScheduleDayMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = scheduleDayDao.queryScheduleDayMationById(map);
        int remindTimeType = Integer.parseInt(bean.get("remindType").toString());
        String scheduleStartTime = map.get("scheduleStartTime").toString();
        String remindTime = DateAfterSpacePointTime.getSpecifiedTime(remindTimeType, scheduleStartTime, DateUtil.YYYY_MM_DD_HH_MM_SS, DateAfterSpacePointTime.AroundType.BEFORE);
        if (!ToolUtil.isBlank(remindTime)) {
            if (DateUtil.compare(remindTime, DateUtil.getTimeAndToString())) {
                // 日程提醒时间早于当前时间,提醒时间则变为当前时间+2分钟
                long minute = DateUtil.getDistanceMinute(remindTime, scheduleStartTime);//获取当前时间和开始时间相差几分钟
                if (minute >= 2) {
                    // 两分钟后
                    remindTime = DateAfterSpacePointTime.getSpecifiedTime(remindTimeType, DateUtil.getTimeAndToString(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateAfterSpacePointTime.AroundType.AFTER, 2);
                } else if (minute < 2 && minute > 0) {
                    // minute分钟后
                    remindTime = DateAfterSpacePointTime.getSpecifiedTime(remindTimeType, DateUtil.getTimeAndToString(), DateUtil.YYYY_MM_DD_HH_MM_SS, DateAfterSpacePointTime.AroundType.AFTER, (int) minute);
                } else {
                    remindTime = scheduleStartTime;//日程开始时
                }
            } else if (DateUtil.compare(map.get("scheduleEndTime").toString(), scheduleStartTime)) {
                // 结束时间早于开始时间
                outputObject.setreturnMessage("日程结束时间不能早于开始时间");
                return;
            }
            map.put("remindTime", remindTime);
            scheduleDayDao.editScheduleDayMationById(map);
            // 删除缓存中的日程信息
            Map<String, Object> user = inputObject.getLogParams();
            // 修改定时任务
            startUpTaskQuartz(map.get("id").toString(), map.get("scheduleTitle").toString(), remindTime);
        } else {
            outputObject.setreturnMessage("参数错误");
        }
    }

    /**
     * 获取日程详细信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryScheduleDayMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        Map<String, Object> bean = scheduleDayDao.queryScheduleDayDetailsMationById(map);
        outputObject.setBean(bean);
        outputObject.settotal(1);
    }

    /**
     * 删除日程信息
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void deleteScheduleDayMationById(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        // 删除日程信息
        scheduleDayDao.deleteScheduleDayMationById(map);
        // 删除定时任务
        iQuartzService.stopAndDeleteTaskQuartz(map.get("id").toString(), QuartzConstants.QuartzMateMationJobType.MY_SCHEDULEDAY_MATION.getTaskType());
    }

    /**
     * 获取我的日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryMyScheduleList(InputObject inputObject, OutputObject outputObject) {
        ScheduleDayQueryDo scheduleDayQuery = inputObject.getParams(ScheduleDayQueryDo.class);
        scheduleDayQuery.setUserId(inputObject.getLogParams().get("id").toString());
        Page pages = PageHelper.startPage(scheduleDayQuery.getPage(), scheduleDayQuery.getLimit());
        List<Map<String, Object>> beans = scheduleDayDao.queryMyScheduleList(scheduleDayQuery);
        outputObject.setBeans(beans);
        outputObject.settotal(pages.getTotal());
    }

    /**
     * 其他模块同步到日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void insertScheduleMationByOtherModule(InputObject inputObject, OutputObject outputObject) {
        Map<String, Object> map = inputObject.getParams();
        OtherModuleScheduleMation scheduleMation = JSONUtil.toBean(JSON.toJSONString(map),
            OtherModuleScheduleMation.class);
        this.synchronizationSchedule(scheduleMation.getTitle(), scheduleMation.getContent(), scheduleMation.getStartTime(),
            scheduleMation.getEndTime(), scheduleMation.getUserId(), scheduleMation.getObjectId(), scheduleMation.getObjectType());
    }

    /**
     * 将其他模块同步到日程
     * 不启动日程定时任务，如需启动，在该接口外面自行启动
     *
     * @param title      标题
     * @param content    日程内容
     * @param startTime  开始时间,格式为：yyyy-MM-dd HH:mm:ss
     * @param endTime    结束时间,格式为：yyyy-MM-dd HH:mm:ss
     * @param userId     执行人
     * @param objectId   关联id
     * @param objectType object类型：1.任务计划id，2.项目任务id
     */
    @Override
    public String synchronizationSchedule(String title, String content, String startTime, String endTime, String userId, String objectId, int objectType) {
        ScheduleMation scheduleMation = new ScheduleMation();
        scheduleMation.setScheduleTitle(title);
        int length = content.length();
        scheduleMation.setRemarks(length > 1000 ? content.substring(0, 1000) : content);
        scheduleMation.setScheduleStartTime(startTime);
        scheduleMation.setScheduleEndTime(endTime);
        scheduleMation.setIsRemind(0);
        // 是否全天 0否 1是
        scheduleMation.setAllDay(1);
        // 日程类型2工作
        scheduleMation.setType(2);
        scheduleMation.setTypeName("工作");
        // 提醒时间所属类型  0.无需提醒
        scheduleMation.setRemindType(0);
        scheduleMation.setImported(1);
        scheduleMation.setObjectId(objectId);
        scheduleMation.setObjectType(2);
        DataCommonUtil.setCommonDataByGenericity(scheduleMation, userId);
        scheduleDayDao.insert(scheduleMation);
        return scheduleMation.getId();
    }

    /**
     * 根据ObjectId删除日程
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void deleteScheduleMationByObjectId(InputObject inputObject, OutputObject outputObject) {
        String objectId = inputObject.getParams().get("objectId").toString();
        scheduleDayDao.deleteScheduleMationByObjectId(objectId);
    }

}
