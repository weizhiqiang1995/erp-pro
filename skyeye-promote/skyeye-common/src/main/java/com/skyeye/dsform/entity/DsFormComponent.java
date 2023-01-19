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
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.IconOrImgInfo;
import lombok.Data;

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
@RedisCacheField(name = "dsForm:component", cacheTime = RedisConstants.ALL_USE_TIME)
@TableName(value = "ds_form_component")
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
    @ApiModelProperty(value = "显示类型，参考#DsFormShowType枚举类", required = "required,num")
    private Integer showType;

    @TableField("html_content")
    @ApiModelProperty(value = "html内容", required = "required")
    private String htmlContent;

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
    @ApiModelProperty(value = "关联数据 1.允许 2.不允许", required = "required,num")
    private Integer linkedData;

    @TableField("display_template_id")
    @ApiModelProperty(value = "数据展示模板id")
    private String displayTemplateId;

    @TableField(exist = false)
    @Property("数据展示模板对象信息")
    private DsFormDisplayTemplate dsFormDisplayTemplate;

    @TableField("default_data")
    @ApiModelProperty(value = "默认数据，需要是json字符串", required = "json")
    private String defaultData;

}
