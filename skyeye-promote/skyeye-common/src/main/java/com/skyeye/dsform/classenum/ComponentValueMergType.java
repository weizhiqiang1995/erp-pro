/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ComponentValueMergType
 * @Description: 组件获取的值的合入接口入参的方式
 * @author: skyeye云系列--卫志强
 * @date: 2023/2/25 12:26
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ComponentValueMergType implements SkyeyeEnumClass {

    SIMPLE("simple", "普通", true, true),
    EXTEND("extend", "扩展", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
