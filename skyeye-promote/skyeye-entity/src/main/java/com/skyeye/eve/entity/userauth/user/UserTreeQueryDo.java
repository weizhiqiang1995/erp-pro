/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.user;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.search.TableSelectInfo;
import lombok.Data;

/**
 * @ClassName: UserTreeQueryDo
 * @Description: 查询用户为树结构的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/26 19:03
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("查询用户为树结构的实体类")
public class UserTreeQueryDo extends TableSelectInfo {

    @ApiModelProperty(value = "人员列表中是否包含自己--1.包含；其他参数不包含", required = "required,num")
    private Integer chooseOrNotMy;

    @ApiModelProperty(value = "人员列表中是否必须绑定邮箱--1.必须；其他参数没必要", required = "required,num")
    private Integer chooseOrNotEmail;

    /**
     * 人员列表中是否包含自己--1.包含；其他参数不包含 根据chooseOrNotMy参数计算
     */
    private String userId;

    /**
     * 人员列表中是否必须绑定邮箱--1.必须；其他参数没必要  根据chooseOrNotEmail参数计算
     */
    private Integer hasEmail;

}
