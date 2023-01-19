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
import com.skyeye.common.entity.CommonInfo;
import com.skyeye.dsform.entity.DsFormComponent;
import lombok.Data;

/**
 * @ClassName: AttrDefinition
 * @Description: 服务类属性实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_attr_definition", autoResultMap = true)
@ApiModel("服务类属性实体类")
public class AttrDefinition extends CommonInfo {

    @TableId("id")
    @Property("主键id")
    private String id;

    @TableField(exist = false)
    @Property("自定义属性的id")
    private String attrDefinitionCustomId;

    @TableField(exist = false)
    @Property(value = "自定义属性关联的组件")
    private DsFormComponent dsFormComponent;

    /**
     * 应用的APPID
     */
    @TableField("app_id")
    private String appId;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className", required = "required")
    private String className;

    @TableField("attr_key")
    @ApiModelProperty(value = "字段名", required = "required")
    private String attrKey;

    @TableField("attr_type")
    @ApiModelProperty(value = "字段类型", required = "required")
    private String attrType;

    @TableField("`name`")
    @ApiModelProperty(value = "属性名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "属性描述")
    private String remark;

    @TableField("whether_input_params")
    @ApiModelProperty(value = "是否可以作为入参", required = "required")
    private Boolean whetherInputParams;

    @TableField("required")
    @ApiModelProperty(value = "属性限制条件")
    private String required;

    @TableField("model_attribute")
    @ApiModelProperty(value = "是否是模型属性", required = "required")
    private Boolean modelAttribute;

}
