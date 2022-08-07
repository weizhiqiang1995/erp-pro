/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.wages;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: WagesStaffWorkTimeMation
 * @Description: 获取应出勤的班次以及小时的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/17 18:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("获取应出勤的班次以及小时的实体类")
public class WagesStaffWorkTimeMation implements Serializable {

    @ApiModelProperty(value = "员工对应的考勤班次")
    private List<Map<String, Object>> staffWorkTime;

    @ApiModelProperty(value = "指定年月，格式为yyyy-MM", required = "required")
    private String lastMonthDate;

}
