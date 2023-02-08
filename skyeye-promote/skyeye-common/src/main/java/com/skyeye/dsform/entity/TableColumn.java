/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: TableColumn
 * @Description: 表格布局的列属性
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/5 17:24
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"pageId", "attrKey"})
@TableName(value = "ds_form_table_column")
@ApiModel("表格布局的列属性")
public class TableColumn extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("attr_key")
    @ApiModelProperty(value = "属性key", required = "required")
    private String attrKey;

    @TableField(exist = false)
    @Property("属性信息")
    private AttrDefinition attrDefinition;

    @TableField("width")
    @ApiModelProperty(value = "宽度", required = "required")
    private String width;

    @TableField("align")
    @ApiModelProperty(value = "对其方式，参考#Alignment枚举类", required = "required")
    private String align;

    @TableField("fixed")
    @ApiModelProperty(value = "固定位置，参考#FixedType枚举类")
    private String fixed;

    @TableField("templet")
    @ApiModelProperty(value = "列内容展示的脚本，相当于表格中的templet")
    private String templet;

    @TableField(value = "order_by")
    @ApiModelProperty(value = "排序", required = "required,num")
    private Integer orderBy;

    @TableField("page_id")
    @Property("表单布局id")
    private String pageId;

}
