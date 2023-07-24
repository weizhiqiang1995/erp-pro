/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.document.classenum;

import com.skyeye.catalog.classenum.CatalogAuthEnum;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: DocumentAuthEnum
 * @Description: 文档权限控制枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/16 22:10
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum DocumentAuthEnum implements SkyeyeEnumClass {
    ADD_DOCUMENT("addDocument", "上传文档", true, false),
    EDIT_DOCUMENT("editDocument", "编辑文档", true, false),
    DELETE_DOCUMENT("deleteDocument", "删除文档", true, false);

    private String key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

    public static List<Class> dependOnEnum() {
        return Arrays.asList(CatalogAuthEnum.class);
    }
}
