/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.catalog.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CatalogAuthEnum
 * @Description: 目录权限控制枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/16 22:10
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CatalogAuthEnum implements SkyeyeEnumClass {
    ADD_CATALOG("addCatalog", "添加目录", true, false),
    DELETE_CATALOG("deleteCatalog", "删除目录", true, false),
    RENAME_CATALOG("renameCatalog", "重命名目录", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;
}
