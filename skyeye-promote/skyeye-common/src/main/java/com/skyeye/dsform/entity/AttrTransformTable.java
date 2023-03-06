/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AttrTransformTable
 * @Description: 表格模型属性
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("表格模型属性")
public class AttrTransformTable implements Serializable {

    @ApiModelProperty(value = "字段名，相当于表格中的field", required = "required")
    private String attrKey;

    @ApiModelProperty(value = "属性名称")
    private String name;

    @ApiModelProperty(value = "对齐方式，可参考#Alignment枚举类，相当于表格中的align", required = "required")
    private String align;

    @ApiModelProperty(value = "排序，值越大越往后", required = "required,num")
    private Integer orderBy;

    @ApiModelProperty(value = "宽度，相当于表格中的width", required = "required,num")
    private Integer width;

    @ApiModelProperty(value = "列内容展示的脚本，相当于表格中的templet")
    private String templet;

}
