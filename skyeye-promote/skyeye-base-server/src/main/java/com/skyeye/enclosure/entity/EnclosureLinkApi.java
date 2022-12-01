/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: EnclosureLinkApi
 * @Description: 附件信息与业务对象关联的实体类BOX
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:00
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("附件信息与业务对象关联的实体类BOX")
public class EnclosureLinkApi implements Serializable {

    @ApiModelProperty(value = "业务对象数据的id", required = "required")
    private String objectId;

    @ApiModelProperty(value = "业务对象服务的className", required = "required")
    private String objectKey;

    /**
     * 格式：{“enclosure”: ["111", "222"]}
     */
    @ApiModelProperty(value = "业务对象的字段列与附件id集合的关系", required = "required,json")
    private Map<String, List<String>> enclosureIds;

}
