/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.user;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysUserStaffMation
 * @Description: 员工管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/12 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("员工管理实体类")
public class SysUserStaffMation implements Serializable {

    private String id;

    @ApiModelProperty(value = "员工性别  0保密   1男  2女", required = "required,num")
    private Integer userSex;

    @ApiModelProperty(value = "身份证", required = "idcard")
    private String userIdCard;

    @ApiModelProperty(value = "员工姓名", required = "required")
    private String userName;

    @ApiModelProperty(value = "头像", required = "required")
    private String userPhoto;

    @ApiModelProperty(value = "邮箱", required = "email")
    private String email;

    @ApiModelProperty(value = "QQ")
    private String qq;

    @ApiModelProperty(value = "手机号", required = "phone")
    private String phone;

    @ApiModelProperty(value = "家庭电话")
    private String homePhone;

    @ApiModelProperty(value = "所属公司", required = "required")
    private String companyId;

    @ApiModelProperty(value = "所属部门", required = "required")
    private String departmentId;

    @ApiModelProperty(value = "所属职位", required = "required")
    private String jobId;

    @ApiModelProperty(value = "职位定级")
    private String jobScoreId;

    @ApiModelProperty(value = "个性签名")
    private String userSign;

    @ApiModelProperty(value = "参加工作时间，格式为yyyy-MM-dd", required = "required")
    private String workTime;

    @ApiModelProperty(value = "入职时间，格式为yyyy-MM-dd", required = "required")
    private String entryTime;

    @ApiModelProperty(value = "员工考勤时间段，逗号隔开")
    private String checkTimeStr;

    @ApiModelProperty(value = "员工类型   1.普通员工  2.教师", required = "required,num", defaultValue = "1")
    private Integer type;

    @ApiModelProperty(value = "员工在职状态", required = "required,num")
    private Integer state;

    @ApiModelProperty(value = "如果有试用期，则为试用期到期时间。当state=4时，该字段必填")
    private String trialTime;

    @ApiModelProperty(value = "关联的面试安排信息id")
    private String interviewArrangementId;

    /**
     * 如果有试用期，则为转正日期
     */
    private String becomeWorkerTime;

    /**
     * 员工工号
     */
    private String jobNumber;

    /**
     * 录入人
     */
    private String createId;

    /**
     * 录入时间
     */
    private String createTime;

}
