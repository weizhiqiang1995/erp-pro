/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: EnclosureLink
 * @Description: 附件信息与业务对象关联的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:35
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@UniqueField(value = {"objectId", "objectKey", "objectField", "enclosureId"})
@TableName(value = "sys_enclosure_link")
@ApiModel("附件信息与业务对象关联的实体类")
public class EnclosureLink extends OperatorUserInfo {

    @TableId("id")
    private String id;

    /**
     * 业务对象数据的id
     */
    @TableField(value = "object_id")
    private String objectId;

    /**
     * 业务对象服务的className
     */
    @TableField(value = "object_key")
    private String objectKey;

    /**
     * 业务对象的字段列
     */
    @TableField(value = "object_field")
    private String objectField;

    /**
     * 附件id
     */
    @TableField(value = "enclosure_id")
    private String enclosureId;

}
