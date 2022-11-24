/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.organization.job;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: CompanyJob
 * @Description: 部门职位信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/23 23:17
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("部门职位信息实体类")
@UniqueField(value = {"departmentId", "name"})
@RedisCacheField(name = CacheConstants.ORGANIZATION_JOB_CACHE_KEY)
@TableName(value = "company_job")
public class CompanyJob extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("company_id")
    @ApiModelProperty(value = "所属公司id", required = "required")
    private String companyId;

    @TableField("department_id")
    @ApiModelProperty(value = "所属部门id", required = "required")
    private String departmentId;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "简介")
    private String remark;

    @TableField("parent_id")
    @ApiModelProperty(value = "父职位id，一级职位为0", required = "required")
    private String parentId;

}
