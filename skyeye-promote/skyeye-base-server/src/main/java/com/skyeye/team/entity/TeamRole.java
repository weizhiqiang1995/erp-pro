/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: TeamRole
 * @Description: 团队角色实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"teamId", "teamKey", "roleId"})
@TableName(value = "team_role")
@ApiModel("团队角色实体类")
public class TeamRole extends OperatorUserInfo {

    @TableId("id")
    @Property("主键id")
    private String id;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String name;

    /**
     * 角色父id,这里为团队id
     */
    @TableField(exist = false)
    private String pId;

    @TableField("team_id")
    @ApiModelProperty(value = "团队id，单独保存时必填(因为有和团队一起的保存)")
    private String teamId;

    @TableField("team_key")
    @ApiModelProperty(value = "团队类型，单独保存时必填(因为有和团队一起的保存)")
    private String teamKey;

    @TableField("role_id")
    @ApiModelProperty(value = "团队角色id(数据字典中的团队角色类型)", required = "required")
    private String roleId;

    @TableField(exist = false)
    @ApiModelProperty(value = "角色用户", required = "json")
    private List<TeamRoleUser> teamRoleUserList;

}
