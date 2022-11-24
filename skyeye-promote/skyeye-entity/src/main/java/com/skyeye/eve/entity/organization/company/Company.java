/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.organization.company;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.entity.features.AreaInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: Company
 * @Description: 企业信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/19 1:09
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("企业信息实体类")
@UniqueField
@RedisCacheField(name = CacheConstants.ORGANIZATION_COMPANY_CACHE_KEY)
@TableName(value = "company_mation")
public class Company extends AreaInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("`name`")
    @ApiModelProperty(value = "企业名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "公司简介")
    private String remark;

    @TableField("p_id")
    @ApiModelProperty(value = "所属公司ID，一级公司为0", required = "required")
    private String pId;

    @TableField(exist = false)
    @ApiModelProperty(value = "公司个人所得税税率信息", required = "json")
    private List<Map<String, Object>> taxRate;

}
