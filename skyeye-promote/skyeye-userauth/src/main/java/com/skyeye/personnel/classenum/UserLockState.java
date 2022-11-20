/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.personnel.classenum;

import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: UserLockState
 * @Description: 用户锁定状态枚举
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/20 21:33
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserLockState implements SkyeyeEnumClass {

    SYS_USER_LOCK_STATE_ISUNLOCK(0, "未锁定", true, true),
    SYS_USER_LOCK_STATE_ISLOCK(1, "锁定", false, true);

    private Integer key;

    private String value;

    private Boolean show;

    private Boolean isDefault;

}
