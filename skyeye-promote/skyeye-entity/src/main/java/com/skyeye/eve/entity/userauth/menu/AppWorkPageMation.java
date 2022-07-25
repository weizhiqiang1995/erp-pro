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

import java.io.Serializable;

/**
 * @ClassName: AppWorkPageMation
 * @Description: APP菜单实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/24 21:33
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "app_workbench_page")
@ApiModel("APP菜单实体类")
public class AppWorkPageMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("title")
    @ApiModelProperty(value = "菜单/目录名称", required = "required")
    private String title;

    /**
     * 和title含义一样，用做前台下拉框加载
     */
    @TableField(exist = false)
    private String name;

    @TableField("logo")
    @ApiModelProperty(value = "菜单logo")
    private String logo;

    @TableField("url")
    @ApiModelProperty(value = "菜单路径")
    private String url;

    @TableField("url_type")
    @ApiModelProperty(value = "菜单类型  1.外部系统菜单  2.自身系统菜单", required = "required,num", defaultValue = "2")
    private Integer urlType;

    /**
     * 状态  1.新建  2.上线  3.下线  4.删除
     */
    @TableField("state")
    private Integer state;

    /**
     * 排序，值越大越往后
     */
    @TableField("order_by")
    private Integer orderBy;

    @TableField(value = "type", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "页面类型  1.目录  2.页面【不可修改】", required = "required,num")
    private Integer type;

    @TableField(value = "parent_id")
    @ApiModelProperty(value = "所属目录id", required = "required")
    private String parentId;

    @TableField(value = "desktop_id")
    @ApiModelProperty(value = "所属桌面id", required = "required")
    private String desktopId;

}
