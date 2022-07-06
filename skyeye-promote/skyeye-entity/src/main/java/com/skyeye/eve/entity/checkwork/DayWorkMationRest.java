/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.checkwork;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: DayWorkMationParams
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 20:45
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("判断节假日信息的实体类")
public class DayWorkMationRest implements Serializable {

    @ApiModelProperty(value = "月份里面的天", required = "required")
    private List<Map<String, Object>> beans;

    @ApiModelProperty(value = "月份", required = "required")
    private List<String> months;

    @ApiModelProperty(value = "考勤班次id", required = "required")
    private String timeId;

}
