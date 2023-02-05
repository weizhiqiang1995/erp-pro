/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: TableColumnVo
 * @Description: 表格布局的列属性保存接口的入参
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/5 17:24
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("表格布局的列属性保存接口的入参")
public class TableColumnVo implements Serializable {

    @ApiModelProperty(value = "表单布局id", required = "required")
    private String pageId;

    @ApiModelProperty(value = "表单布局内的组件信息", required = "json")
    private List<TableColumn> tableColumnList;

}
