/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.api;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.features.OperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: ApiMation
 * @Description: api接口信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/28 16:35
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "api_mation")
@ApiModel("api接口信息实体类")
public class ApiMation extends OperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("title")
    @ApiModelProperty(value = "标题", required = "required")
    private String title;

    @TableField(value = "request_url", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "请求地址", required = "required")
    private String requestUrl;

    @TableField("request_body")
    @ApiModelProperty(value = "请求入参")
    private String requestBody;

    @TableField("response_body")
    @ApiModelProperty(value = "请求出参")
    private String responseBody;

    @TableField(value = "app_id", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "appId", required = "required")
    private String appId;

}
