/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: SysDesktop
 * @Description: 桌面管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = "sys:desktop")
@TableName(value = "sys_eve_desktop")
@ApiModel("桌面管理实体类")
public class SysDesktop extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("`name`")
    @ApiModelProperty(value = "桌面名称", required = "required")
    private String name;

    @TableField("logo")
    @ApiModelProperty(value = "logo", required = "required")
    private String logo;

    @TableField("app_page_url")
    @ApiModelProperty(value = "手机端页面跳转地址")
    private String appPageUrl;

    @TableField("order_by")
    @Property(value = "序号")
    private Integer orderBy;

    /**
     * 状态
     */
    @TableField("enabled")
    @ApiModelProperty(value = "状态，参考#EnableEnum枚举类", required = "required,num")
    private Integer enabled;

    @TableField("code")
    @ApiModelProperty(value = "区分桌面的code，唯一", required = "required")
    private String desktopCode;

    @TableField(value = "delete_flag")
    private Integer deleteFlag;

}
