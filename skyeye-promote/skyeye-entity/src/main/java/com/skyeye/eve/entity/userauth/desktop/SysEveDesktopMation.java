/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.desktop;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysEveDesktopMation
 * @Description: 桌面管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/30 0:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_eve_desktop")
@ApiModel("桌面管理实体类")
public class SysEveDesktopMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("desktop_name")
    @ApiModelProperty(value = "桌面名称(中文)", required = "required")
    private String desktopName;

    @TableField("desktop_name_cn")
    @ApiModelProperty(value = "桌面名称(英文)", required = "required")
    private String desktopCnName;

    @TableField("logo")
    @ApiModelProperty(value = "logo", required = "required")
    private String logo;

    @TableField("app_page_url")
    @ApiModelProperty(value = "手机端页面跳转地址")
    private String appPageUrl;

    /**
     * 序号
     */
    @TableField("order_by")
    private Integer orderBy;

    /**
     * 状态
     */
    @TableField("state")
    @ApiModelProperty(value = "状态", required = "required,num")
    private Integer state;

    /**
     * 序号
     */
    @TableField("code")
    @ApiModelProperty(value = "区分桌面的code，唯一", required = "required")
    private String desktopCode;

}
