/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.schedule;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ScheduleMation
 * @Description: 日程的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 18:59
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "schedule_day")
@ApiModel("日程的实体类")
public class ScheduleMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("title")
    @ApiModelProperty(value = "标题", required = "required")
    private String scheduleTitle;

    @TableField(value = "remarks", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "日程内容/备注", required = "required")
    private String remarks;

    @TableField(value = "all_day", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "是否全天  0否 1是", required = "required,num")
    private Integer allDay;

    @TableField("start_time")
    @ApiModelProperty(value = "开始时间,格式为：yyyy-MM-dd HH:mm:ss", required = "required")
    private String scheduleStartTime;

    @TableField("end_time")
    @ApiModelProperty(value = "结束时间,格式为：yyyy-MM-dd HH:mm:ss", required = "required")
    private String scheduleEndTime;

    @TableField(value = "remind_type", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "提醒时间所属类型  0.无需提醒，不要提醒时间 1.日程开始时 2.5分钟前 3.15分钟前 4.30分钟前 5.1小时前 6.2小时前 7.1天前 8.2天前 9.一周前", required = "required,num")
    private Integer remindType;

    /**
     * 提醒时间，系统计算
     */
    @TableField("remind_time")
    private String remindTime;

    @TableField(value = "type", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "日程类型 1个人 2工作 3节假日 4生日 5自定义 6上班日(表中不体现,根据考勤班次计算出来的)", required = "required,num")
    private Integer type;

    @TableField(value = "type_name", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "类型名称", required = "required")
    private String typeName;

    /**
     * 日程状态 0.新建日程 1.已提醒结束
     */
    @TableField("state")
    private Integer state = 0;

    @TableField(value = "import", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "日程重要性  1普通  2重要", required = "required,num")
    private Integer imported;

    /**
     * 是否需要提醒  1需要  0不需要
     */
    @TableField(value = "is_remind", fill = FieldFill.INSERT)
    private Integer isRemind = 1;

    @TableField(value = "object_id", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "关联id")
    private String objectId;

    @TableField(value = "object_type", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "object类型：1.任务计划id，2.项目任务id", required = "num")
    private Integer objectType;

}
