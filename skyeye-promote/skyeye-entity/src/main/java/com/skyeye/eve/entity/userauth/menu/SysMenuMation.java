/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.menu;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonIconOrImgInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysMenuMation
 * @Description: 菜单管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/5/15 21:01
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("菜单管理实体类")
public class SysMenuMation extends CommonIconOrImgInfo implements Serializable {

    @ApiModelProperty(value = "菜单名称", required = "required")
    private String menuName;

    @ApiModelProperty(value = "英文名称", required = "required")
    private String menuNameEn;

    @ApiModelProperty(value = "菜单链接", required = "required")
    private String menuUrl;

    @ApiModelProperty(value = "菜单类型", required = "required")
    private String menuType;

    @ApiModelProperty(value = "是否为系统菜单", required = "required,num")
    private String menuSysType;

    @ApiModelProperty(value = "菜单父ID", required = "required")
    private String parentId;

    @ApiModelProperty(value = "菜单所属系统", required = "required")
    private String sysWinId;

    @ApiModelProperty(value = "菜单所属桌面")
    private String desktopId;

    @ApiModelProperty(value = "是否同步共享", required = "required,num")
    private String isShare;

}
