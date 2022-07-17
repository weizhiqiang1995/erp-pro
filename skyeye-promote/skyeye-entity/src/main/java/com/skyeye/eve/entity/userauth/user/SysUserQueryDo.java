/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.user;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysUserQueryDo
 * @Description: 用户列表查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/16 22:42
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("用户列表查询条件实体类")
public class SysUserQueryDo extends CommonPageInfo implements Serializable {

    @ApiModelProperty(value = "用户账号")
    private Integer userCode;

    @ApiModelProperty(value = "员工姓名")
    private Integer userName;

    @ApiModelProperty(value = "公司")
    private Integer companyName;

    @ApiModelProperty(value = "部门")
    private Integer departmentName;

    @ApiModelProperty(value = "职位")
    private Integer jobName;

}
