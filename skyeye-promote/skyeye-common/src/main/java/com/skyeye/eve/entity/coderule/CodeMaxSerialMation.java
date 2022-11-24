/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.coderule;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CodeMaxSerialMation
 * @Description: 编码最大序列值实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_code_max_serial", autoResultMap = true)
@ApiModel("编码最大序列值实体类")
public class CodeMaxSerialMation extends OperatorUserInfo implements Serializable {

    @TableId("id")
    private String id;

    @TableField("code_rule_id")
    @ApiModelProperty("编码规则实体类ID")
    private String codeRuleId;

    @TableField("feature_code")
    @ApiModelProperty("特征码")
    private String featureCode;

    @TableField("serial_code")
    @ApiModelProperty("流水码")
    private String serialCode;

}
