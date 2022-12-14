/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: AttrTransformTable
 * @Description: 表格模型属性
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"className", "attrKey"})
@TableName(value = "skyeye_attr_transform_table", autoResultMap = true)
@ApiModel("表格模型属性")
public class AttrTransformTable extends OperatorUserInfo {

    @TableId("id")
    @Property("主键id")
    private String id;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className", required = "required")
    private String className;

    @TableField("attr_key")
    @ApiModelProperty(value = "字段名，相当于表格中的field", required = "required")
    private String attrKey;

    @TableField("parent_attr_key")
    @ApiModelProperty(value = "所属父节点的字段名", required = "required")
    private String parentAttrKey;

    @TableField("`name`")
    @ApiModelProperty(value = "名称，相当于表格中的title", required = "required")
    private String name;

    @TableField("align")
    @ApiModelProperty(value = "对齐方式，可参考#Alignment枚举类，相当于表格中的align", required = "required")
    private String align;

    @TableField(value = "order_by")
    @ApiModelProperty(value = "排序，值越大越往后", required = "required,num")
    private Integer orderBy;

    @TableField("width")
    @ApiModelProperty(value = "宽度，相当于表格中的width", required = "required,num")
    private Integer width;

    @TableField("templet")
    @ApiModelProperty(value = "列内容展示的脚本，相当于表格中的templet")
    private String templet;

}
