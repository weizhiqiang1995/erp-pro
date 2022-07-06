/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.workplan;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SysWorkPlanQueryDo
 * @Description: 工作计划列表查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/6 22:41
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("工作计划列表查询条件实体类")
public class SysWorkPlanQueryDo extends CommonPageInfo implements Serializable {

    @ApiModelProperty(value = "计划标题")
    private String title;

    @ApiModelProperty(value = "计划下达人")
    private String userName;

    @ApiModelProperty(value = "计划执行人")
    private String executorName;

    @ApiModelProperty(value = "执行状态", required = "num")
    private Integer state;

    @ApiModelProperty(value = "计划类型  1.个人计划 2.部门计划 3.公司计划", required = "num")
    private Integer planType;

    @ApiModelProperty(value = "计划周期类型  1.日计划 2.周计划 3.月计划 4.季度计划 5.半年计划 6.年计划", required = "num")
    private Integer planCycle;

    @ApiModelProperty(value = "开始时间", required = "date")
    private String startTime;

    @ApiModelProperty(value = "结束时间", required = "date")
    private String endTime;

    /**
     * 我下属的用户id
     */
    private List<String> list;

    /**
     * 当前登陆用户id
     */
    private String userId;

}
