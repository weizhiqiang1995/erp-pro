/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.menu;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: SysMenuAuthPointMation
 * @Description: 菜单权限点实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/23 19:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_eve_menu_auth_point")
@ApiModel("菜单权限点实体类")
public class SysMenuAuthPointMation extends CommonOperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "menu_id", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "所属菜单id【不可修改】", required = "required")
    private String menuId;

    @TableField("auth_menu_name")
    @ApiModelProperty(value = "权限点名称/分组名称/数据权限名称", required = "required")
    private String authMenuName;

    @TableField("auth_menu")
    @ApiModelProperty(value = "接口id/分组标识/数据权限表达式", required = "required")
    private String authMenu;

    /**
     * 菜单数字码
     */
    @TableField("menu_num")
    private String menuNum;

    @TableField(value = "parent_id", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "所属父id，层级结构参考type字段【不可修改】", required = "required")
    private String parentId;

    @TableField(value = "type", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "类型  1.权限点  2.数据分组(父级为1)  3.数据分组下的数据类型(父级为2)【不可修改】", required = "required,num")
    private String type;

}
