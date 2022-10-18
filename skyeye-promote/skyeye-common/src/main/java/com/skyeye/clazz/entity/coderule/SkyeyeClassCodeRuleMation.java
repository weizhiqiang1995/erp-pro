/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.entity.coderule;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: SkyeyeClassCodeRuleMation
 * @Description: 需要获取编码的服务类的集合实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "skyeye_class_code_rule", autoResultMap = true)
@ApiModel("需要获取编码的服务类的集合实体类")
public class SkyeyeClassCodeRuleMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    private String id;

    /**
     * 服务的APPID
     */
    @TableField("app_id")
    private String appId;

    /**
     * 服务的appName
     */
    @TableField("app_name")
    private String appName;

    /**
     * 需要获取编码的服务类的className
     */
    @TableField("class_name")
    @ApiModelProperty(value = "需要获取编码的服务类的className", required = "required")
    private String className;

    /**
     * className对应的服务名称
     */
    @TableField("service_name")
    @ApiModelProperty(value = "className对应的服务名称", required = "required")
    private String serviceName;

    /**
     * className对应的服务名称
     */
    @TableField("group_name")
    @ApiModelProperty(value = "className对应的分组名称", required = "required")
    private String groupName;

    /**
     * 编码规则id
     */
    @TableField("code_rule_id")
    private String codeRuleId;

}
