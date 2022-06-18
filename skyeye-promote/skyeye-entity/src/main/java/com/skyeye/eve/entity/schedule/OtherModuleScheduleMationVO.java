/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.schedule;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: OtherModuleScheduleMationVO
 * @Description: 其他模块同步到日程的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 18:59
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("其他模块同步到日程的实体类")
public class OtherModuleScheduleMationVO implements Serializable {

    @ApiModelProperty(value = "标题", required = "required")
    private String title;

    @ApiModelProperty(value = "日程内容", required = "required")
    private String content;

    @ApiModelProperty(value = "开始时间,格式为：yyyy-MM-dd HH:mm:ss", required = "required")
    private String startTime;

    @ApiModelProperty(value = "结束时间,格式为：yyyy-MM-dd HH:mm:ss", required = "required")
    private String endTime;

    @ApiModelProperty(value = "执行人", required = "required")
    private String userId;

    @ApiModelProperty(value = "关联id")
    private String objectId;

    @ApiModelProperty(value = "object类型：1.任务计划id，2.项目任务id", required = "required,num")
    private Integer objectType;

}
