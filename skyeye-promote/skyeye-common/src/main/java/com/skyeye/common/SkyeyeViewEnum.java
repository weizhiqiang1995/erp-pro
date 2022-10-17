/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SkyeyeViewEnum
 * @Description: 前台视图的枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/17 23:04
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SkyeyeViewEnum implements SkyeyeEnumClass {

    WIN_10(0, "", true, true);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
