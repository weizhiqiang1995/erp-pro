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
 * @ClassName: LifecycleTemplateStateLink
 * @Description: 生命周期模板与状态的关系的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/3 20:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "lifecycle_template_state_link")
@ApiModel("生命周期模板与状态的关系的实体类")
public class LifecycleTemplateStateLink extends OperatorUserInfo {

    @TableId("id")
    private String id;

    @TableId("lifecycle_state_id")
    @ApiModelProperty(value = "状态id")
    private String lifecycleStateId;

    @TableId("lifecycle_template_id")
    @ApiModelProperty(value = "生命周期模板id")
    private String lifecycleTemplateId;

}
