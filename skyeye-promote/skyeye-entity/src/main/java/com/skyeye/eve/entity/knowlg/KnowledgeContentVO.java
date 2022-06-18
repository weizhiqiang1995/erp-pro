/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.knowlg;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: KnowledgeContentVO
 * @Description: 知识库实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/21 15:46
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("知识库实体类")
public class KnowledgeContentVO implements Serializable {

    @ApiModelProperty(value = "知识库标题", required = "required")
    private String title;

    @ApiModelProperty(value = "知识库简介", required = "required")
    private String desc;

    @ApiModelProperty(value = "知识库内容", required = "required")
    private String content;

    @ApiModelProperty(value = "所属类型", required = "required")
    private String typeId;

    @ApiModelProperty(value = "知识库标签")
    private String label;

}
