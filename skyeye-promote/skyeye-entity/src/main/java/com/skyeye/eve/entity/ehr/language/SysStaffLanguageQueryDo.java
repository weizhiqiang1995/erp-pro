/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.ehr.language;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysStaffLanguageQueryDo
 * @Description: 员工语言能力查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/11 10:41
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("员工语言能力查询条件实体类")
public class SysStaffLanguageQueryDo extends CommonPageInfo implements Serializable {

    @ApiModelProperty(value = "员工姓名")
    private String userName;

    @ApiModelProperty(value = "员工工号")
    private String jobNumber;

    @ApiModelProperty(value = "员工状态")
    private String state;

}
