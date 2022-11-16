/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.team.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ObjectPermissionFromType
 * @Description: 业务对象权限表的权限来源
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/16 17:48
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ObjectPermissionFromType implements SkyeyeEnumClass {

    TEAM_LINK(1, "团队统一赋权", true, true),
    PERSONAL_LINK(2, "私人赋权(例如：用户A赋权给用户B)", true, false);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
