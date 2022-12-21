/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.attr.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DsFormShowType
 * @Description: 组件展示类型枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/18 23:29
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DsFormShowType implements SkyeyeEnumClass {
    TEXT(1, "文本展示", true, false),
    ENCLOSURE(2, "附件展示", true, false),
    RICH_TEXT(3, "富文本展示", true, false),
    PICTURE(4, "图片展示", true, false),
    TABLE(5, "表格展示", true, false),
    VOUCHER(6, "凭证展示", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
