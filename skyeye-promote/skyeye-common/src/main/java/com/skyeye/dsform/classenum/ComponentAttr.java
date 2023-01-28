/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ComponentAttr
 * @Description: 组件关联的属性
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/28 11:05
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ComponentAttr implements SkyeyeEnumClass {

    ATTR_KEY("attrKeyBox", "关联属性", true, true, true),
    TITLE("titleBox", "名称", true, true, true),
    LINK_DATA("linkDataBox", "关联数据", false, true, false),
    PLACEHOLDER("placeholderBox", "提示语", false, true, false),
    REQUIRE("requireBox", "限制条件", false, true, false),
    WIDTH("widthBox", "宽度", true, true, true),
    DEFAULT_VALUE("defaultValueBox", "默认值", false, true, false);

    private String key;

    private String value;

    /**
     * 必选(默认选中，不可取消)
     */
    private Boolean disabled;

    private Boolean show;

    private Boolean isDefault;

}