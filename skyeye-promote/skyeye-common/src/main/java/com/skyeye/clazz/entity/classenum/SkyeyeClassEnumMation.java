/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.entity.classenum;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SkyeyeClassEnumMation
 * @Description: 具备某个特征的枚举类表对应的实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/11 23:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "skyeye_class_enum", autoResultMap = true)
@ApiModel("具备某个特征的枚举类表对应的实体类")
public class SkyeyeClassEnumMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    private String id;

    /**
     * 服务的APPID
     */
    @TableField("app_id")
    private String appId;

    /**
     * 枚举类的className
     */
    @TableField("class_name")
    private String className;

    /**
     * 枚举对应的值
     */
    @TableField(value = "value_list", typeHandler = SkyeyeClassEnumListTypeHandler.class)
    private List<Map<String, Object>> valueList;

}
