/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: AttrSymbols
 * @Description: 属性与值的对比符号枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AttrSymbols implements SkyeyeEnumClass {
    LESS_THAN("lessThan", "小于", "<", true, true),
    GREATER_THAN("greaterThan", "大于", ">", true, false),
    EQUAL_TO("equalTo", "等于", "==", true, false),
    NOT_EQUAL("notEqual", "不等于", "!=", true, false),
    LESS_THAN_OR_EQUAL("lessThanOrEqual", "小于等于", "<=", true, false),
    GREATER_THAN_OR_EQUAL("greaterThanOrEqual", "大于等于", ">=", true, false);

    private String key;

    private String value;

    private String symbols;

    private Boolean show;

    private Boolean isDefault;

    public static String getSymbols(String key) {
        for (AttrSymbols q : AttrSymbols.values()) {
            if (q.getKey().equals(key)) {
                return q.getSymbols();
            }
        }
        return StringUtils.EMPTY;
    }

}
