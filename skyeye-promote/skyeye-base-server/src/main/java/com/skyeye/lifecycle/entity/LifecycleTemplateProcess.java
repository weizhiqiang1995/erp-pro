/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.lifecycle.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: LifecycleTemplateProcess
 * @Description: 生命周期模板与流程的关系的实体类，表示通过该流程可以将状态A变成状态B
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/3 20:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"lifecycleStateAId", "lifecycleStateBId", "lifecycleTemplateId"})
@TableName(value = "lifecycle_template_process")
@ApiModel("生命周期模板与流程的关系的实体类")
public class LifecycleTemplateProcess extends OperatorUserInfo {

    @TableId("id")
    private String id;

    @TableId("lifecycle_state_a_id")
    @ApiModelProperty(value = "状态id", required = "required")
    private String lifecycleStateAId;

    @TableId("lifecycle_state_b_id")
    @ApiModelProperty(value = "状态id", required = "required")
    private String lifecycleStateBId;

    /**
     * 生命周期模板id
     */
    @TableId("lifecycle_template_id")
    private String lifecycleTemplateId;

    @TableId("act_flow_id")
    @ApiModelProperty(value = "流程配置id", required = "required")
    private String actFlowId;

}
