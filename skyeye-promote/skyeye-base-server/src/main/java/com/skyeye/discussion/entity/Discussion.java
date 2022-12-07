/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.discussion.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.base.handler.enclosure.bean.Enclosure;
import com.skyeye.common.base.handler.enclosure.bean.EnclosureFace;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeTeamAuth;
import lombok.Data;

/**
 * @ClassName: Discussion
 * @Description: 讨论帖实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 22:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = CacheConstants.SKYEYE_DISCUSSION_CACHE_KEY, cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "skyeye_discussion")
@ApiModel("讨论帖实体类")
public class Discussion extends SkyeyeTeamAuth implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "title")
    @ApiModelProperty(value = "标题", required = "required")
    private String title;

    @TableField(value = "content")
    @ApiModelProperty(value = "内容", required = "required")
    private String content;

    @TableField(value = "link_id")
    @ApiModelProperty(value = "关联的业务数据id，例如：商机id等")
    private String linkId;

    @TableField(value = "link_key")
    @ApiModelProperty(value = "关联的业务数据key，例如：商机的serviceClassName等")
    private String linkKey;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件")
    private Enclosure enclosureInfo;

}
