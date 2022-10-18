/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.entity.coderule;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: SkyeyeClassCodeRuleApiMation
 * @Description: 需要获取编码的服务类对应的实体类BOX
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:00
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("需要获取编码的服务类对应的实体类BOX")
public class SkyeyeClassCodeRuleApiMation implements Serializable {

    /**
     * 服务的APPID
     */
    @ApiModelProperty(value = "服务的APPID", required = "required")
    private String appId;

    /**
     * 服务的appName
     */
    @ApiModelProperty(value = "服务的appName", required = "required")
    private String appName;

    /**
     * 服务类信息
     */
    @ApiModelProperty(value = "服务类信息")
    private List<SkyeyeClassCodeRuleMation> classNameList;

}
