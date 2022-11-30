/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: TeamBusiness
 * @Description: 团队实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "team_business")
@ApiModel("团队实体类")
public class TeamBusiness extends OperatorUserInfo {

    @TableId("id")
    private String id;

    @TableField("name")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("business_id")
    @ApiModelProperty(value = "业务对象id", required = "required")
    private String businessId;

    @TableField("business_key")
    @ApiModelProperty(value = "业务对象的key", required = "required")
    private String businessKey;

    @TableField("team_template_id")
    @ApiModelProperty(value = "团队模板id", required = "required")
    private String teamTemplateId;

    /**
     * 团队角色信息
     */
    @TableField(exist = false)
    private List<TeamRole> teamRoleList;

}
