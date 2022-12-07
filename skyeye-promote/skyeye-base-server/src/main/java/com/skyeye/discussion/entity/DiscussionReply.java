/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.base.handler.enclosure.bean.Enclosure;
import com.skyeye.common.base.handler.enclosure.bean.EnclosureFace;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: DiscussionReply
 * @Description: 讨论帖回帖实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 22:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_discussion_reply")
@ApiModel("讨论帖回帖实体类")
public class DiscussionReply extends OperatorUserInfo implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "content")
    @ApiModelProperty(value = "内容", required = "required")
    private String content;

    @TableField(value = "discussion_id")
    @ApiModelProperty(value = "讨论帖id", required = "required")
    private String discussionId;

    @TableField(value = "reply_id")
    @ApiModelProperty(value = "回复id，如果是首次评论帖，那就是0", required = "required")
    private String replyId;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件")
    private Enclosure enclosureInfo;

}
