/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.classenum;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.base.classenum.dto.SkyeyeEnumDto;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SkyeyeClassEnumApiMation
 * @Description: 具备某个特征的枚举类表对应的实体类BOX
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/11 23:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("具备某个特征的枚举类表对应的实体类BOX")
public class SkyeyeClassEnumApiMation extends CommonOperatorUserInfo implements Serializable {

    /**
     * 服务名
     */
    @ApiModelProperty(value = "服务名", required = "required")
    private String springApplicationName;

    /**
     * 枚举信息
     */
    @ApiModelProperty(value = "枚举信息", required = "required")
    private Map<String, List<SkyeyeEnumDto>> valueList;

}
