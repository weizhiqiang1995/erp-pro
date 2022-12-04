/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.entity.classservice;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: SkyeyeClassServiceBean
 * @Description: 服务类注册实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "skyeye_class_service_bean", autoResultMap = true)
@ApiModel("服务类注册实体类")
public class SkyeyeClassServiceBean extends CommonInfo implements Serializable {

    @TableId("id")
    private String id;

    /**
     * 服务名
     */
    @TableField("spring_application_name")
    private String springApplicationName;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className", required = "required")
    private String className;

    @TableField("entity_class_name")
    @ApiModelProperty(value = "实体类的chassName", required = "required")
    private String entityClassName;

}
