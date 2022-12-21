/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: WidthScale
 * @Description: 宽度比例枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum WidthScale implements SkyeyeEnumClass {
    ONE("layui-col-xs12", "整行铺满", true, true),
    THREE_FOURTHS("layui-col-xs9", "四分之三", true, false),
    A_HALF("layui-col-xs6", "二分之一", true, false),
    ONE_THIRD("layui-col-xs4", "三分之一", true, false),
    QUARTER("layui-col-xs3", "四分之一", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
