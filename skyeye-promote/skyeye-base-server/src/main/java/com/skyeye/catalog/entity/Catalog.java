/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.catalog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: Catalog
 * @Description: 目录信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 22:07
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"name", "parentId", "objectKey", "type"})
@RedisCacheField(name = CacheConstants.SKYEYE_CATALOG_CACHE_KEY, cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "skyeye_catalog")
@ApiModel("目录信息实体类")
public class Catalog extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "目录名称", required = "required")
    private String name;

    @TableField(value = "parent_id")
    @ApiModelProperty(value = "所属父节点id", required = "required")
    private String parentId;

    @TableField(value = "object_key")
    @ApiModelProperty(value = "目录所属业务对象", required = "required")
    private String objectKey;

    @TableField(value = "type")
    @ApiModelProperty(value = "目录类型  1. 公共  2.私有", required = "required,num")
    private Integer type;

}
