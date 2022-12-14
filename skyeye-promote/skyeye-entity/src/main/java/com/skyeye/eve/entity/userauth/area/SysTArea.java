/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.userauth.area;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: SysTArea
 * @Description: 区域管理实体类，如果这里的缓存key修改，记得修改rest中的IAreaServiceImpl
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/18 23:33
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("区域管理实体类")
@UniqueField
@RedisCacheField(name = CacheConstants.SYS_TAREA_CACHE_KEY)
@TableName(value = "t_area")
public class SysTArea extends OperatorUserInfo {

    @TableId("id")
    @Property("主键id")
    private String id;

    @TableField("code_id")
    @ApiModelProperty(value = "区域编号", required = "required")
    private String codeId;

    @TableField("`name`")
    @ApiModelProperty(value = "区域名称", required = "required")
    private String name;

    @TableField("parent_code_id")
    @ApiModelProperty(value = "所属父区域编号", required = "required")
    private String parentCodeId;

    /**
     * 区划级别，从0开始
     */
    @TableField("`level`")
    private Integer level;

}
