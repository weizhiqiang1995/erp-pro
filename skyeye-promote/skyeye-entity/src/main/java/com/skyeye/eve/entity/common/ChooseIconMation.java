/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.common;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ChooseIconMation
 * @Description: 部分功能选择图标的信息
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/15 21:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("部分功能选择图标的信息")
public class ChooseIconMation implements Serializable {

    @ApiModelProperty(value = "菜单图标类型", required = "required,num")
    private String iconType;

    @ApiModelProperty(value = "菜单图片地址")
    private String iconPic;

    @ApiModelProperty(value = "菜单logo")
    private String icon;

    @ApiModelProperty(value = "菜单logo颜色")
    private String iconColor;

    @ApiModelProperty(value = "菜单logo背景")
    private String iconBg;

}
