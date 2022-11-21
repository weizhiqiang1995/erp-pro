/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.organization.department;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: Department
 * @Description: 部门信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/20 21:27
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("部门信息实体类")
@UniqueField
@RedisCacheField(name = CacheConstants.ORGANIZATION_DEPARTMENT_CACHE_KEY)
@TableName(value = "company_department")
public class Department extends CommonOperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("company_id")
    @ApiModelProperty(value = "所属公司id", required = "required")
    private String companyId;

    @TableField("`name`")
    @ApiModelProperty(value = "部门名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "部门简介")
    private String remark;

    @TableField("p_id")
    @ApiModelProperty(value = "父部门id，一级公司为0", required = "required")
    private String pId;

    @TableField("overtime_settlement_type")
    @ApiModelProperty(value = "部门加班结算方式  1.单倍薪资结算  2.1.5倍薪资结算  3.双倍薪资结算  6.补休结算", required = "required,num")
    private Integer overtimeSettlementType;

}
