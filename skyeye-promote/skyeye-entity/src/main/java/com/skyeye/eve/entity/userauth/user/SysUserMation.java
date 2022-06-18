/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.user;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysUserMation
 * @Description: 用户管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/13 10:01
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("用户管理实体类")
public class SysUserMation implements Serializable {

    private String id;

    @ApiModelProperty(value = "员工id", required = "required")
    private String staffId;

    @ApiModelProperty(value = "用户账号", required = "required")
    private String userCode;

    @ApiModelProperty(value = "密码", required = "required")
    private String password;

    @ApiModelProperty(value = "有效期  1.长期有效", required = "required,num")
    private Integer isTermOfValidity;

    /**
     * 录入人
     */
    private String createId;

    /**
     * 录入时间
     */
    private String createTime;

}
