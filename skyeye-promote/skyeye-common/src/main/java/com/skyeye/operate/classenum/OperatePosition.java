/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: OperatePosition
 * @Description: 操作按钮展示位置枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum OperatePosition implements SkyeyeEnumClass {

    TOOL_BAR("toolBar", "工具栏", true, true),
    ACTION_BAR("actionBar", "操作栏", true, false),
    RIGHT_MENU_BAR("rightMenuBar", "右键菜单栏", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
