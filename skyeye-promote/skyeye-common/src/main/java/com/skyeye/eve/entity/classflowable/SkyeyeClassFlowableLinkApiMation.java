/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.classflowable;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SkyeyeClassFlowableLinkApiMation
 * @Description: 工作流业务对象服务关系的实体类的box
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/20 23:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("工作流业务对象服务关系的实体类的box")
public class SkyeyeClassFlowableLinkApiMation implements Serializable {

    @ApiModelProperty(value = "服务的APPID", required = "required")
    private String appId;

    @ApiModelProperty(value = "工作流业务数据服务关系列表")
    private List<SkyeyeClassFlowableLinkMation> flowableServiceList;

}
