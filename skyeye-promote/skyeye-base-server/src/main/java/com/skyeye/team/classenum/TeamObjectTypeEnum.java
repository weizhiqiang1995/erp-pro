/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: TeamObjectTypeEnum
 * @Description: 团队适用对象枚举类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/13 12:46
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TeamObjectTypeEnum implements SkyeyeEnumClass {

    CUSTOMER(1, "客户团队", Arrays.asList("contactsAuthEnum", "crmContractAuthEnum"), true, true),
    SUPPLIER(2, "供应商团队", Arrays.asList("contactsAuthEnum"), true, false),
    PROJECT(3, "项目团队", Arrays.asList(), true, false);

    private Integer key;

    private String value;

    private List<String> pageAuth;

    private Boolean show;

    private Boolean isDefault;
}
