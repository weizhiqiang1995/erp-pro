/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.quartz;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysQuartzMation
 * @Description: 启动定时任务实体入参
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/31 22:46
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("启动定时任务实体入参")
public class SysQuartzMation implements Serializable {

    @ApiModelProperty(value = "任务唯一值", required = "required")
    private String name;

    @ApiModelProperty(value = "标题", required = "required")
    private String title;

    @ApiModelProperty(value = "启动时间", required = "required")
    private String delayedTime;

    @ApiModelProperty(value = "定时任务分组id", required = "required")
    private String groupId;

}
