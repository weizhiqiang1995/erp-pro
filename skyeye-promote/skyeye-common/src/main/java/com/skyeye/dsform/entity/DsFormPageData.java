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
 * @ClassName: DsFormPageData
 * @Description: 表单布局数据的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/10 17:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "ds_form_page_data")
@ApiModel("表单布局数据的实体类")
public class DsFormPageData extends OperatorUserInfo {

    @TableId("id")
    private String id;

    @TableField("content_id")
    @ApiModelProperty(value = "表单布局内的组件基本信息id", required = "required")
    private String contentId;

    @TableField(exist = false)
    @Property("表单布局内的组件基本信息")
    private DsFormPageContent dsFormPageContent;

    @TableField("page_id")
    @Property("表单布局id")
    private String pageId;

    @TableField("sequence_id")
    @Property("业务数据关联的表单布局id")
    private String sequenceId;

    @TableField("object_id")
    @Property("业务数据id")
    private String objectId;

    @TableField("value")
    @ApiModelProperty(value = "提交的值")
    private String value;

    @TableField("display_value")
    @ApiModelProperty(value = "提交的显示值")
    private String displayValue;

}
