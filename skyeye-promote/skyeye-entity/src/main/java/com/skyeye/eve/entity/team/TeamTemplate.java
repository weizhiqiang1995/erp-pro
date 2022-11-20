/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.team;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: TeamTemplate
 * @Description: 团队模板实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@RedisCacheField(name = CacheConstants.TEAM_TEMPLATE_CACHE_KEY, cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "team_template")
@ApiModel("团队模板实体类")
public class TeamTemplate extends CommonOperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "描述")
    private String remark;

    @TableField("object_type")
    @ApiModelProperty(value = "该模板适用的对象类型，来源skyeye-pro#TeamObjectTypeEnum", required = "required,num")
    private Integer objectType;

    @TableField("enabled")
    @ApiModelProperty(value = "启用 1-启用  2-禁用", required = "required,num")
    private Integer enabled;

    @TableField(exist = false)
    @ApiModelProperty(value = "团队角色", required = "required,json")
    private List<TeamRole> teamRoleList;

    @TableField(exist = false)
    @ApiModelProperty(value = "团队权限", required = "required,json")
    private List<TeamObjectPermission> teamObjectPermissionList;

    /**
     * 是否使用中 0-未使用  2-已使用
     */
    @TableField("is_used")
    private Integer isUsed;

}
