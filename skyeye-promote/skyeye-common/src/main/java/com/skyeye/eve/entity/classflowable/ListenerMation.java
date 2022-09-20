/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.classflowable;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ListenerMation
 * @Description: 工作流业务对象服务对应的监听类的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/20 23:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("工作流业务对象服务对应的监听类的实体类")
public class ListenerMation {

    @ApiModelProperty(value = "监听器名称", required = "required")
    private String value;

    @ApiModelProperty(value = "监听器的className", required = "required")
    private String className;

}
