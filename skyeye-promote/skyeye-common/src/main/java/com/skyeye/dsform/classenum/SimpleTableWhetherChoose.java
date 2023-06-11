/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: SimpleTableWhetherChoose
 * @Description: 表格类型的布局是否开启选择功能
 * @author: skyeye云系列--卫志强
 * @date: 2023/6/11 21:51
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum SimpleTableWhetherChoose implements SkyeyeEnumClass {

    CLOSE("close", "关闭", true, false),
    RADIO("radio", "单选", true, false),
    CHECKBOX("checkbox", "多选", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
