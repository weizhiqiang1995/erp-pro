/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.search;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: SearchMation
 * @Description: 高级查询配置实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/12 11:01
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_search")
@ApiModel("高级查询配置实体类")
public class SearchMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("app_id")
    @ApiModelProperty(value = "APPID", required = "required")
    private String appId;

    @TableField("url_id")
    @ApiModelProperty(value = "接口对应的url的id", required = "required")
    private String urlId;

    @TableField(exist = false)
    @ApiModelProperty(value = "参数配置信息", required = "required")
    private Map<String, SearchParamsConfigMation> paramsConfig;

    /**
     * 参数配置信息json字符串
     */
    @TableField("params_config")
    private String paramsConfigStr;

}
