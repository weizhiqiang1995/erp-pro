/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.document.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.catalog.entity.Catalog;
import com.skyeye.common.base.handler.enclosure.bean.Enclosure;
import com.skyeye.common.base.handler.enclosure.bean.EnclosureFace;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.features.SkyeyeTeamAuth;
import com.skyeye.eve.entity.dict.SysDictData;
import lombok.Data;

/**
 * @ClassName: Document
 * @Description: 文档管理实体类
 * @author: skyeye云系列--卫志强
 * @date: 2023/7/24 8:19
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目
 */
@Data
@RedisCacheField(name = CacheConstants.SKYEYE_DOCUMENT_CACHE_KEY, cacheTime = RedisConstants.THIRTY_DAY_SECONDS)
@TableName(value = "skyeye_document")
@ApiModel("文档信息实体类")
public class Document extends SkyeyeTeamAuth implements EnclosureFace {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "文档名称", required = "required")
    private String name;

    @TableField(value = "type_id")
    @ApiModelProperty(value = "所属分类，数据来源：数据字典", required = "required")
    private String typeId;

    @TableField(exist = false)
    @Property(value = "所属分类信息")
    private SysDictData typeMation;

    @TableField(value = "content")
    @ApiModelProperty(value = "内容", required = "required")
    private String content;

    @TableField(value = "catalog_id")
    @ApiModelProperty(value = "所属目录", required = "required")
    private String catalogId;

    @TableField(exist = false)
    @Property(value = "所属目录信息")
    private Catalog catalogMation;

    @TableField(exist = false)
    @ApiModelProperty(value = "附件")
    private Enclosure enclosureInfo;

}
