/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.checkwork;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CheckWorkMation
 * @Description: 考勤打卡实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 21:30
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("考勤打卡实体类")
public class CheckWorkMation implements Serializable {

    @ApiModelProperty(value = "考勤id", required = "required")
    private String id;

    @ApiModelProperty(value = "状态", required = "required")
    private String state;

    @ApiModelProperty(value = "下班打卡状态", required = "required")
    private String clockOutState;

    @ApiModelProperty(value = "考勤工时", required = "required")
    private String workHours;

}
