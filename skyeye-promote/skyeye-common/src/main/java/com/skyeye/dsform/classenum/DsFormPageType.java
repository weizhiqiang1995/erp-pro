/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: IsDefaultEnum
 * @Description: 表单布局类型
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/5 23:51
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DsFormPageType implements SkyeyeEnumClass {

    CREATE("create", "创建布局", true, false),
    EDIT("edit", "编辑布局", true, false),
    DETAILS("details", "详情布局", true, false),
    SIMPLE_TABLE("simpleTable", "基础表格", true, false),
    PROCESS_ATTR("processAttr", "流程属性布局", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
