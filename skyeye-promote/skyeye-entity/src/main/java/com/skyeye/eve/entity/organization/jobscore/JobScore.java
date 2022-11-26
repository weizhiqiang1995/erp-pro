/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.organization.jobscore;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.cache.RedisCacheField;
import com.skyeye.annotation.unique.UniqueField;
import com.skyeye.common.constans.CacheConstants;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: JobScore
 * @Description: 职位定级信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/25 22:28
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("职位定级信息实体类")
@UniqueField(value = {"name", "jobId"})
@RedisCacheField(name = CacheConstants.ORGANIZATION_JOB_SCORE_CACHE_KEY)
@TableName(value = "company_job_score")
public class JobScore extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("job_id")
    @ApiModelProperty(value = "职位id", required = "required")
    private String jobId;

    @TableField("`name`")
    @ApiModelProperty(value = "名称", required = "required")
    private String name;

    @TableField("enabled")
    @ApiModelProperty(value = "状态（1 启用  2.停用）", required = "required,num")
    private Integer enabled;

    @TableField(exist = false)
    @ApiModelProperty(value = "职位定级薪资字段设计范围实体类", required = "json")
    private List<JobScoreField> scoreFields;

}
