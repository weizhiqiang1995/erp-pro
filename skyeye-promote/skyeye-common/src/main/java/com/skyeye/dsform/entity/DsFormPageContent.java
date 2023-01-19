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
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: DsFormPageContent
 * @Description: 表单布局关联的组件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/10 17:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "ds_form_page_content")
@ApiModel("表单布局关联的组件实体类")
public class DsFormPageContent extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("title")
    @ApiModelProperty(value = "左侧提醒，例如：姓名", required = "required")
    private String title;

    @TableField("placeholder")
    @ApiModelProperty(value = "文本提示语")
    private String placeholder;

    @TableField("`require`")
    @ApiModelProperty(value = "组件限制条件")
    private String require;

    @TableField("default_value")
    @ApiModelProperty(value = "默认值")
    private String defaultValue;

    @TableField("width")
    @ApiModelProperty(value = "宽度", required = "required")
    private String width;

    @TableField("form_content_id")
    @ApiModelProperty(value = "动态表单组件id", required = "required")
    private String formContentId;

    @TableField(exist = false)
    @Property("表单布局内的组件信息")
    private DsFormComponent dsFormComponent;

    @TableField("page_id")
    @ApiModelProperty(value = "表单布局id", required = "required")
    private String pageId;

    @TableField(value = "order_by")
    @ApiModelProperty(value = "排序", required = "num")
    private Integer orderBy;

    @TableField(value = "associated_data_types")
    @ApiModelProperty(value = "关联数据类型 1.json串，2.接口", required = "num")
    private Integer associatedDataTypes;

    @TableField("a_data")
    @ApiModelProperty(value = "数据 associated_data_types=1：json串，associated_data_types=2：接口地址")
    private String aData;

    @TableField("attr_key")
    @ApiModelProperty(value = "属性key")
    private String attrKey;

    /**
     * 删除标记，0未删除，1删除
     */
    @TableField(value = "delete_flag")
    private Integer deleteFlag;

}
