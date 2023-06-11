/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: OperateShowCondition
 * @Description: 操作按钮显示的条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/20 23:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("操作按钮显示的条件实体类")
public class OperateShowCondition {

    @ApiModelProperty(value = "属性名", required = "required")
    private String attrKey;

    @ApiModelProperty(value = "比较符号", required = "required")
    private String symbols;

    @Property(value = "比较符号标识")
    private String symbolsMark;

    @ApiModelProperty(value = "值")
    private String value;

    @ApiModelProperty(value = "显示值")
    private String displayValue;

}
