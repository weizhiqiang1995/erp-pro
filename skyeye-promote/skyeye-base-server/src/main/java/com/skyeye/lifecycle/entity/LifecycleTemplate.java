/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.lifecycle.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: LifecycleTemplate
 * @Description: 生命周期状态实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/3 20:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = CacheConstants.LIFECYCLE_TEMPLATE_CACHE_KEY, cacheTime = RedisConstants.HALF_A_YEAR_SECONDS)
@TableName(value = "lifecycle_template")
@ApiModel("生命周期模板实体类")
public class LifecycleTemplate extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("num_code")
    @ApiModelProperty(value = "编码", required = "required")
    private String numCode;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "描述")
    private String remark;

    @TableField("enabled")
    @ApiModelProperty(value = "状态，参考#EnableEnum枚举类", required = "required,num")
    private Integer enabled;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className，适用对象", required = "required")
    private String className;

    /**
     * 是否删除，参考#DeleteFlagEnum枚举类
     */
    @TableField(value = "delete_flag")
    private Integer deleteFlag;

}
