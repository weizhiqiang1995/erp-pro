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

import java.util.List;

/**
 * @ClassName: DsFormPageSequence
 * @Description: 业务数据关联的表单布局实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/10 17:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "ds_form_page_sequence")
@ApiModel("业务数据关联的表单布局实体类")
public class DsFormPageSequence extends OperatorUserInfo {

    @TableId("id")
    private String id;

    @TableField("page_id")
    @ApiModelProperty(value = "表单布局id", required = "required")
    private String pageId;

    @TableField("object_id")
    @Property("业务数据id")
    private String objectId;

    @TableField(exist = false)
    @Property("表单布局信息")
    private DsFormPage dsFormPage;

    @TableField(exist = false)
    @ApiModelProperty(value = "表单布局的数据信息", required = "required,json")
    private List<DsFormPageData> dsFormPageDataList;

}
