/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.knowlg;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: KnowledgeTypeVO
 * @Description: 知识库类型实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/21 10:43
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("知识库类型实体类")
public class KnowledgeTypeMation implements Serializable {

    @ApiModelProperty(value = "知识库类型名称", required = "required")
    private String name;

    @ApiModelProperty(value = "所属类型ID")
    private String parentId;

}
