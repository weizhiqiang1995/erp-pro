/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.other.IconOrImgInfo;
import lombok.Data;

/**
 * @ClassName: SysMenuMation
 * @Description: 菜单管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/15 21:01
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_eve_menu")
@ApiModel("菜单管理实体类")
public class SysMenuMation extends IconOrImgInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("menu_name")
    @ApiModelProperty(value = "菜单名称", required = "required")
    private String menuName;

    @TableField("menu_name_en")
    @ApiModelProperty(value = "英文名称", required = "required")
    private String menuNameEn;

    @TableField("menu_url")
    @ApiModelProperty(value = "菜单链接", required = "required")
    private String menuUrl;

    @TableField("menu_type")
    @ApiModelProperty(value = "菜单类型", required = "required")
    private String menuType;

    @TableField("menu_sys_type")
    @ApiModelProperty(value = "是否为系统菜单", required = "required,num")
    private Integer menuSysType;

    @TableField("menu_parent_id")
    @ApiModelProperty(value = "菜单父ID", required = "required")
    private String parentId;

    @TableField("menu_level")
    @ApiModelProperty(value = "菜单级别    0：父菜单   1：子菜单", required = "required,num")
    private Integer menuLevel;

    /**
     * 菜单链接打开类型，父菜单默认为1.1：打开iframe，2：打开html。
     */
    @TableField("open_type")
    private Integer openType;

    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    @TableField("sys_win_id")
    @ApiModelProperty(value = "菜单所属系统", required = "required")
    private String sysWinId;

    @TableField("is_share")
    @ApiModelProperty(value = "是否同步共享", required = "required,num")
    private Integer isShare;

    @TableField("desktop_id")
    @ApiModelProperty(value = "菜单所属桌面")
    private String desktopId;

}
