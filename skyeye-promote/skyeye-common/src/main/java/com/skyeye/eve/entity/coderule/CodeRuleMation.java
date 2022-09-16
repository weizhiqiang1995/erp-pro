/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.coderule;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: CodeRuleMation
 * @Description: 编码规则实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/16 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_code_rule", autoResultMap = true)
@ApiModel("编码规则实体类")
public class CodeRuleMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    private String id;

    @TableField("`name`")
    @ApiModelProperty(value = "名称")
    private String name;

    @TableField("code_num")
    @ApiModelProperty(value = "编码")
    private String codeNum;

    @TableField("remark")
    @ApiModelProperty(value = "描述")
    private String remark;

    @TableField("pattern")
    @ApiModelProperty(value = "命名模式")
    private String pattern;

    @TableField("ignore_chars")
    @ApiModelProperty(value = "忽略字符")
    private String ignoreChars;

    @TableField("feature_script")
    @ApiModelProperty(value = "特征码变量扩展规则", exampleDefault="{\"sex\":{\"type\":\"map\",\"content\":{\"1\":\"man\",\"2\":\"women\"}}}" +
        "或  {\"sex\":{\"type\":\"javascript\",\"content\":\"if(value == '1'){\nreturn 'man';\n}else{\nreturn 'women';\n}\"}}")
    private String featureScript;

    @TableField("alarm")
    @ApiModelProperty(value = "是否告警：0不告警1告警 默认0，编码使用超90%告警")
    private Boolean alarm;

}
