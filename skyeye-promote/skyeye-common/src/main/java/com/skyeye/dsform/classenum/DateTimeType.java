/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: DateTimeType
 * @Description: 日期类型
 * @author: skyeye云系列--卫志强
 * @date: 2023/3/5 18:02
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DateTimeType implements SkyeyeEnumClass {

    YEAR("year", "年", true, false),
    MONTH("month", "年-月", true, false),
    DATE("date", "年-月-日", true, false),
    TIME("time", "时:分:秒", true, false),
    DATETIME("datetime", "年-月-日 时:分:秒", true, true),
    TIMEMINUTE("timeminute", "时:分", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
