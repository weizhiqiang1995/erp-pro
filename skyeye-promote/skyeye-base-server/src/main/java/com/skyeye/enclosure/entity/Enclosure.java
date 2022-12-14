/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: Enclosure
 * @Description: 附件信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:35
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@RedisCacheField(name = CacheConstants.SKYEYE_ENCLOSURE_CACHE_KEY, cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "sys_enclosure")
@ApiModel("附件信息实体类")
public class Enclosure extends OperatorUserInfo {

    @TableId("id")
    @Property("主键id")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "附件原始名称", required = "required")
    private String name;

    @TableField(value = "path")
    @ApiModelProperty(value = "文件地址", required = "required")
    private String path;

    @TableField(value = "type")
    @ApiModelProperty(value = "文件类型", required = "required")
    private String type;

    @TableField(value = "size")
    @ApiModelProperty(value = "文件大小", required = "required")
    private String size;

    @TableField(value = "size_type")
    @ApiModelProperty(value = "文件大小单位  bytes", required = "required")
    private String sizeType;

    @TableField(value = "catalog")
    @ApiModelProperty(value = "文件所属目录", required = "required")
    private String catalog;

}
