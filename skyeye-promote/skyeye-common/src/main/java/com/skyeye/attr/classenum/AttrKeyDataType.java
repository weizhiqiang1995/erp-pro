/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: AttrKeyDataType
 * @Description: 属性关联的数据类型
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/23 15:07
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum AttrKeyDataType implements SkyeyeEnumClass {

    CUSTOM(1, "自定义JSON串", true, false),
    ENUM_DATA(2, "枚举类型", true, false),
    DICT_DATA(3, "数据字典类型", true, false),
    CUSTOM_API(4, "自定义API", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
