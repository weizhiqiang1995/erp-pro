/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.auth;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: RoleMation
 * @Description: 角色管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/12 21:16
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("角色管理实体类")
public class RoleMation extends CommonInfo {

    private String id;

    @ApiModelProperty(value = "角色名称", required = "required")
    private String roleName;

    @ApiModelProperty(value = "角色描述")
    private String roleDesc;

    @ApiModelProperty(value = "菜单权限")
    private List<String> menuIds;

    /**
     * 录入人
     */
    private String createId;

    /**
     * 录入时间
     */
    private String createTime;

}
