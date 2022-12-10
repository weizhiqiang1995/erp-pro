/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: AbstractTeam
 * @Description: 团队抽象实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
public class AbstractTeam extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(exist = false)
    @ApiModelProperty(value = "团队角色", required = "required,json")
    private List<TeamRole> teamRoleList;

    @TableField(exist = false)
    @ApiModelProperty(value = "团队权限", required = "json")
    private List<TeamObjectPermission> teamObjectPermissionList;

    @TableField(value = "charge_user")
    @ApiModelProperty(value = "团队经理", required = "required")
    private String chargeUser;

    /**
     * 团队经理信息
     */
    @TableField(exist = false)
    private Map<String, Object> chargeUserMation;

}
