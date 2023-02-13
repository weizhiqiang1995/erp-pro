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

import java.util.List;

/**
 * @ClassName: AttrTransform
 * @Description: 业务对象提交到工作流的模型属性
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"className", "attrKey", "actFlowId"})
@TableName(value = "skyeye_attr_transform", autoResultMap = true)
@ApiModel("业务对象提交到工作流的模型属性")
public class AttrTransform extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "order_by")
    @ApiModelProperty(value = "排序，值越大越往后", required = "required,num")
    private Integer orderBy;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className", required = "required")
    private String className;

    @TableField("attr_key")
    @ApiModelProperty(value = "字段名", required = "required")
    private String attrKey;

    @TableField(exist = false)
    @Property("属性信息")
    private AttrDefinition attrDefinition;

    @TableField("proportion")
    @ApiModelProperty(value = "显示宽度，可参考#WidthScale", required = "required")
    private String proportion;

    @TableField("act_flow_id")
    @ApiModelProperty(value = "工作流模型id", required = "required")
    private String actFlowId;

    @TableField(exist = false)
    @ApiModelProperty(value = "展示类型为表格展示时的表格数据", required = "json")
    private List<AttrTransformTable> attrTransformTableList;

}
