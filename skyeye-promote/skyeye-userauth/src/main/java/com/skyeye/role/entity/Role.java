/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.role.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: Role
 * @Description: 角色管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/12 21:16
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField
@RedisCacheField(name = "sys:role", cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "sys_eve_role")
@ApiModel("角色管理实体类")
public class Role extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "角色名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "角色描述")
    private String remark;

    @TableField("parent_id")
    @ApiModelProperty(value = "所属父节点id")
    private String parentId;

    @TableField(exist = false)
    @ApiModelProperty(value = "PC端菜单权限")
    private List<String> menuIds;

    @TableField(exist = false)
    @Property(value = "手机端菜单权限")
    private List<String> appMenuIds;

}
