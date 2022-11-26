/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.organization.jobscore;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: JobScoreField
 * @Description: 职位定级薪资字段设计范围实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/25 22:28
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("职位定级薪资字段设计范围实体类")
@UniqueField(value = {"jobScoreId", "fieldKey"})
@TableName(value = "company_job_score_field")
public class JobScoreField extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    /**
     * 职位定级id
     */
    @TableField("job_score_id")
    private String jobScoreId;

    @TableField("field_key")
    @ApiModelProperty(value = "薪资字段key", required = "required")
    private String fieldKey;

    /**
     * 薪资字段名称
     */
    @TableField(exist = false)
    private String fieldName;

    @TableField("min_money")
    @ApiModelProperty(value = "该级别的职位在该薪资字段中的薪资最小值", required = "required,double")
    private Integer minMoney;

    @TableField("max_money")
    @ApiModelProperty(value = "该级别的职位在该薪资字段中的薪资最大值", required = "required,double")
    private Integer maxMoney;

    @TableField("sort_no")
    @ApiModelProperty(value = "排序，值越大越往后", required = "required,num")
    private Integer sortNo;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private Integer remark;

}
