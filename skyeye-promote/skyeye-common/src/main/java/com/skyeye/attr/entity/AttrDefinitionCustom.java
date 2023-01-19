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
import com.skyeye.dsform.entity.DsFormComponent;
import lombok.Data;

/**
 * @ClassName: AttrDefinitionCustom
 * @Description: 用户自定义服务类属性实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"className", "attrKey"})
@TableName(value = "skyeye_attr_definition_custom", autoResultMap = true)
@ApiModel("用户自定义服务类属性实体类")
public class AttrDefinitionCustom extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className", required = "required")
    private String className;

    @TableField("attr_key")
    @ApiModelProperty(value = "字段名", required = "required")
    private String attrKey;

    @TableField("`name`")
    @ApiModelProperty(value = "属性名称", required = "required")
    private String name;

    @TableField("component_id")
    @ApiModelProperty(value = "组件id")
    private String componentId;

    @TableField(exist = false)
    @Property(value = "自定义属性关联的组件")
    private DsFormComponent dsFormComponent;

    @TableField("min_length")
    @ApiModelProperty(value = "最小长度")
    private Integer minLength;

    @TableField("max_length")
    @ApiModelProperty(value = "最大长度")
    private Integer maxLength;

    @TableField("remark")
    @ApiModelProperty(value = "属性描述")
    private String remark;

    @TableField(exist = false)
    @Property(value = "属性的原始信息")
    private AttrDefinition attrDefinition;

}
