/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: SysEveUserStaff
 * @Description: 员工管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/8/18 11:24
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
//@RedisCacheField(name = CacheConstants.SYS_USER_STAFF_CACHE_KEY, cacheTime = RedisConstants.A_YEAR_SECONDS)
@TableName(value = "sys_eve_user_staff")
@ApiModel("员工管理实体类")
public class SysEveUserStaff extends CommonInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("job_number")
    @Property(value = "员工工号")
    private String jobNumber;

    @TableField("user_name")
    @ApiModelProperty(value = "员工姓名", required = "required")
    private String userName;

    @TableField("user_photo")
    @ApiModelProperty(value = "员工头像", required = "required")
    private String userPhoto;

    @TableField("user_idcard")
    @ApiModelProperty(value = "员工身份证", required = "idcard")
    private String userIdcard;

    @TableField("user_sex")
    @ApiModelProperty(value = "员工性别，参考#SexEnum", required = "required,num")
    private Integer userSex;

    @TableField("email")
    @ApiModelProperty(value = "邮箱", required = "email")
    private String email;

    @TableField("phone")
    @ApiModelProperty(value = "手机号", required = "phone")
    private String phone;

    @TableField("home_phone")
    @ApiModelProperty(value = "家庭电话")
    private String homePhone;

    @TableField("qq")
    @ApiModelProperty(value = "QQ")
    private String qq;

    @TableField("user_sign")
    @ApiModelProperty(value = "个性签名")
    private String userSign;

    @TableField("state")
    @ApiModelProperty(value = "员工在职状态，参考#UserStaffState", required = "required,num")
    private Integer state;

    @TableField("user_id")
    @ApiModelProperty(value = "用户id")
    private String userId;

    @TableField("company_id")
    @ApiModelProperty(value = "所属公司id", required = "required")
    private String companyId;

    @TableField("department_id")
    @ApiModelProperty(value = "所属部门id", required = "required")
    private String departmentId;

    @TableField("job_id")
    @ApiModelProperty(value = "所属职位id", required = "required")
    private String jobId;

    @TableField("job_score_id")
    @ApiModelProperty(value = "职位定级id")
    private String jobScoreId;

    @TableField("quit_time")
    @ApiModelProperty(value = "离职时间")
    private String quitTime;

    @TableField("quit_reason")
    @ApiModelProperty(value = "离职原因 最多50字")
    private String quitReason;

    @TableField("work_time")
    @ApiModelProperty(value = "参加工作时间")
    private String workTime;

    @TableField("entry_time")
    @ApiModelProperty(value = "入职时间，也是到岗时间")
    private String entryTime;

    @TableField("trial_time")
    @ApiModelProperty(value = "如果有试用期，则为试用期到期时间。当state=4时，该字段必填")
    private String trialTime;

    @TableField("become_worker_time")
    @ApiModelProperty(value = "如果有试用期，则为转正日期")
    private String becomeWorkerTime;

    @TableField("type")
    @ApiModelProperty(value = "员工类型，参考#UserStaffType", required = "required,num", defaultValue = "1")
    private Integer type;

    @TableField("native_place")
    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @TableField("marital_status")
    @ApiModelProperty(value = "婚姻状况  1.已婚  2.未婚", required = "required,num")
    private Integer maritalStatus;

    @TableField("politic_id")
    @ApiModelProperty(value = "政治面貌id")
    private String politicId;

    @TableField("highest_education")
    @ApiModelProperty(value = "最高学历id")
    private String highestEducation;

    @TableField("design_wages")
    @ApiModelProperty(value = "薪资设定情况  1.待设定2.已设定", required = "required,num", defaultValue = "1")
    private Integer designWages;

    @TableField("act_wages")
    @ApiModelProperty(value = "员工的月标准薪资")
    private String actWages;

    @TableField("annual_leave")
    @ApiModelProperty(value = "员工剩余年假")
    private String annualLeave;

    @TableField("annual_leave_statis_time")
    @ApiModelProperty(value = "员工剩余年假最近的刷新日期")
    private String annualLeaveStatisTime;

    @TableField("holiday_number")
    @ApiModelProperty(value = "当前员工剩余补休天数")
    private String holidayNumber;

    @TableField("holiday_statis_time")
    @ApiModelProperty(value = "补休池剩余补休天数数据刷新时间")
    private String holidayStatisTime;

    @TableField("retired_holiday_number")
    @ApiModelProperty(value = "当前员工已休补休天数")
    private String retiredHolidayNumber;

    @TableField("retired_holiday_statis_time")
    @ApiModelProperty(value = "补休池已休补休天数数据刷新时间")
    private String retiredHolidayStatisTime;

    @TableField("interview_arrangement_id")
    @ApiModelProperty(value = "关联的面试安排信息id")
    private String interviewArrangementId;

}
