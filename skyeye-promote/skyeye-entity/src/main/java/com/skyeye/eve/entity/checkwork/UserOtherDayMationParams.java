/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.checkwork;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: UserOtherDayMationParams
 * @Description: 获取用户指定班次在指定月份的其他日期信息[审核通过的](例如 ： 请假 ， 出差 ， 加班等)的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 21:00
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("获取用户指定班次在指定月份的其他日期信息[审核通过的](例如：请假，出差，加班等)的实体类")
public class UserOtherDayMationParams implements Serializable {

    @ApiModelProperty(value = "用户id", required = "required")
    private String userId;

    @ApiModelProperty(value = "考勤班次id", required = "required")
    private String timeId;

    @ApiModelProperty(value = "月份", required = "required")
    private List<String> months;

}
