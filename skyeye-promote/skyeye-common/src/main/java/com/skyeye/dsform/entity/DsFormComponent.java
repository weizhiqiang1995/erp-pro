/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.IconOrImgInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: DsFormComponent
 * @Description: 表单组件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/3 20:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"numCode"})
@RedisCacheField(name = "dsForm:component")
@TableName(value = "ds_form_component", autoResultMap = true)
@ApiModel("表单组件实体类")
public class DsFormComponent extends IconOrImgInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("num_code")
    @ApiModelProperty(value = "组件编码", required = "required")
    private String numCode;

    @TableField("`name`")
    @ApiModelProperty(value = "组件名称", required = "required")
    private String name;

    @TableField("type_id")
    @ApiModelProperty(value = "组件分类", required = "required")
    private String typeId;

    @TableField(exist = false)
    @Property("组件分类名称")
    private String typeName;

    @TableField("show_type")
    @ApiModelProperty(value = "显示类型，参考#DsFormShowType", required = "required,num")
    private Integer showType;

    @TableField("html_content")
    @ApiModelProperty(value = "html内容", required = "required")
    private String htmlContent;

    @TableField("html_data_from")
    @ApiModelProperty(value = "数据展示来源的加载脚本")
    private String htmlDataFrom;

    @TableField("js_content")
    @ApiModelProperty(value = "组件初始化脚本")
    private String jsContent;

    @TableField("js_value")
    @ApiModelProperty(value = "获取值的脚本")
    private String jsValue;

    @TableField("js_display_value")
    @ApiModelProperty(value = "获取显示值的脚本")
    private String jsDisplayValue;

    @TableField("js_fit_value")
    @ApiModelProperty(value = "设置值的脚本")
    private String jsFitValue;

    @TableField("linked_data")
    @ApiModelProperty(value = "关联数据 1.是 2.否", required = "required,num")
    private Integer linkedData;

    @TableField(value = "attr_keys", typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "组件关联的属性，可参考#ComponentAttr", required = "json")
    private List<String> attrKeys;

    @TableField("apply_range")
    @ApiModelProperty(value = "适用范围，参考#ComponentApplyRange", required = "required,num")
    private Integer applyRange;

    @TableField(value = "apply_object", typeHandler = JacksonTypeHandler.class)
    @ApiModelProperty(value = "局部适用对象", required = "json")
    private List<String> applyObject;

    @TableField("value_merg_type")
    @ApiModelProperty(value = "组件获取的值的合入接口入参的方式，参考#ComponentValueMergType", required = "required")
    private String valueMergType;

    @TableField("detail_html_content")
    @ApiModelProperty(value = "详情页面(showType=0)：组件的html内容")
    private String detailHtmlContent;

    @TableField("detail_js_content")
    @ApiModelProperty(value = "详情页面(showType=0)：组件的js脚本")
    private String detailJsContent;

    @TableField("remark")
    @ApiModelProperty(value = "组件备注")
    private String remark;

}
