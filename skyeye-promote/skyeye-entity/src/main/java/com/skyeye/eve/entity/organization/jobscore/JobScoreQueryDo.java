/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.organization.jobscore;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.search.CommonPageInfo;
import lombok.Data;

/**
 * @ClassName: JobScoreQueryDo
 * @Description: 职位定级信息搜索实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/25 22:49
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("职位定级信息搜索实体类")
public class JobScoreQueryDo extends CommonPageInfo {

    @ApiModelProperty(value = "职位id")
    private String jobId;

}
