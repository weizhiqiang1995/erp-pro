/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.annotation.api.Property;
import com.skyeye.attr.entity.AttrDefinition;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

import java.util.List;

/**
 * @ClassName: ServiceBean
 * @Description: 服务类注册实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 13:11
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_class_service_bean", autoResultMap = true)
@ApiModel("服务类注册实体类")
public class ServiceBean extends CommonInfo {

    @TableId("id")
    @Property("主键id")
    private String id;

    /**
     * 服务名
     */
    @TableField(value = "spring_application_name")
    private String springApplicationName;

    @TableField(exist = false)
    @Property("服务名(中文名称)")
    private String applicationName;

    /**
     * 应用的appId
     */
    @TableField(value = "app_id")
    private String appId;

    @TableField("class_name")
    @ApiModelProperty(value = "服务类的className", required = "required")
    private String className;

    @TableField("`name`")
    @ApiModelProperty(value = "服务名称", required = "required")
    private String name;

    @TableField("remark")
    @ApiModelProperty(value = "备注")
    private String remark;

    @TableField("group_name")
    @ApiModelProperty(value = "所属分组")
    private String groupName;

    @TableField("tenant")
    @ApiModelProperty(value = "租户类型")
    private String tenant;

    @TableField("manage_show")
    @ApiModelProperty(value = "是否可以在界面上进行管理")
    private Boolean manageShow;

    @TableField("entity_class_name")
    @ApiModelProperty(value = "实体类的className", required = "required")
    private String entityClassName;

    @TableField(exist = false)
    @ApiModelProperty(value = "实体类的属性集合", required = "json")
    private List<AttrDefinition> attrDefinitionList;

    @TableField("flowable")
    @ApiModelProperty(value = "是否开启流程", required = "required")
    private Boolean flowable;

    @TableField("team_auth")
    @ApiModelProperty(value = "是否开启团队权限管理", required = "required")
    private Boolean teamAuth;

}
