/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.entity;

import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: DsFormPageSequenceApiBox
 * @Description: 业务数据关联的表单布局保存的API入参
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/10 17:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
public class DsFormPageSequenceApiBox extends OperatorUserInfo {

    @ApiModelProperty(value = "业务数据id", required = "required")
    private String objectId;

    @ApiModelProperty("表单布局的数据信息")
    private List<DsFormPageSequence> dsFormPageSequenceList;

}
