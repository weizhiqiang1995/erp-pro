/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.entity;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.search.CommonPageInfo;
import lombok.Data;

/**
 * @ClassName: DiscussionReplyQueryDo
 * @Description: 论坛贴回帖查询实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/5 23:36
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("论坛贴回帖查询实体类")
public class DiscussionReplyQueryDo extends CommonPageInfo {

    @ApiModelProperty(value = "论坛贴id", required = "required")
    private String discussionId;

}
