/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import com.skyeye.eve.entity.quartz.SysQuartz;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysQuartzDao
 * @Description: 定时任务管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 23:31
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface SysQuartzDao {

    int deleteByPrimaryKey(String id);

    int insert(SysQuartz record);

    List<SysQuartz> selectAll(Map<String, Object> map);

    int deleteByName(String name);

    int updateByName(SysQuartz record);

    // 问卷管理
    Map<String, Object> querySurveyMationById(Map<String, Object> map);

    int editSurveyStateToEndNumZdById(Map<String, Object> map);

    // 日程
    Map<String, Object> queryMationByScheduleId(Map<String, Object> bean);

    int insertNoticeMation(Map<String, Object> notice);

    int editMationByScheduleId(Map<String, Object> bean);

    Map<String, Object> queryScheduleMationByScheduleId(Map<String, Object> bean);

    List<Map<String, Object>> queryAllUserAndEmailISNotNull(Map<String, Object> bean);

    int insertNoticeListMation(List<Map<String, Object>> notices);

    // 工作计划
    Map<String, Object> queryWorkPlanMationToQuartzById(Map<String, Object> map);

    Map<String, Object> queryUserMationByWorkPlanId(Map<String, Object> map);

    int updateNotifyStateByPlanId(Map<String, Object> map);

    List<Map<String, Object>> queryUserMationsByWorkPlanId(Map<String, Object> map);

    void editNoticeStateById(@Param("noticeId") String noticeId, @Param("state") String state);

    List<Map<String, Object>> querySystemQuartzList(Map<String, Object> map);

    List<Map<String, Object>> queryMyTaskQuartzList(Map<String, Object> map);

    /**
     * 根据定时任务id以及类型查到任务信息
     *
     * @param quartzId 定时任务id
     * @param type     类型
     * @return
     */
    Map<String, Object> querySystemQuartzByIdAndType(@Param("quartzId") String quartzId, @Param("type") int type);
}
