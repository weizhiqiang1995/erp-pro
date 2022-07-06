/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.workplan;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysWorkPlanQueryDo
 * @Description: 工作计划列表查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:41
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("工作计划列表查询条件实体类")
public class SysWorkPlanQueryDo extends CommonPageInfo implements Serializable {

    @ApiModelProperty(value = "当前选择的计划分类，个人计划，部门计划，公司计划", required = "required,num")
    private Integer checkPlan;

    @ApiModelProperty(value = "计划类型，日计划，周计划等", required = "required")
    private String nowCheckType;

    /**
     * 计划类型对应的数字
     */
    private String nowCheckTypeNum;

    @ApiModelProperty(value = "计划标题")
    private String title;

    @ApiModelProperty(value = "开始时间", required = "required,date")
    private String startTime;

    @ApiModelProperty(value = "结束时间", required = "required,date")
    private String endTime;

    /**
     * 用户id
     */
    private String userId;

}
