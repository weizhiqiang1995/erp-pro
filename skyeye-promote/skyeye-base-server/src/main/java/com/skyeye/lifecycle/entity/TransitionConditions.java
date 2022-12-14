/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.lifecycle.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: TransitionConditions
 * @Description: 生命周期转变条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/3 20:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "lifecycle_transition_conditions")
@ApiModel("生命周期转变条件实体类")
public class TransitionConditions extends OperatorUserInfo {

    @TableId("id")
    private String id;

    @TableId("operation_id")
    @ApiModelProperty(value = "操作id", required = "required")
    private String operationId;

    @TableId("lifecycle_state_a_id")
    @ApiModelProperty(value = "状态id", required = "required")
    private String lifecycleStateAId;

    @TableId("lifecycle_state_b_id")
    @ApiModelProperty(value = "状态id", required = "required")
    private String lifecycleStateBId;

}
