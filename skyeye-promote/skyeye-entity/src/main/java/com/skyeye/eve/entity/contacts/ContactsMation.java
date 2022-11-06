/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.contacts;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

/**
 * @ClassName: ContactsMation
 * @Description: 联系人信息实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/10/24 15:58
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "skyeye_contacts")
@ApiModel("联系人信息实体类")
public class ContactsMation extends CommonOperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField(value = "object_id")
    @ApiModelProperty(value = "所属第三方业务数据id", required = "required")
    private String objectId;

    @TableField(value = "object_key")
    @ApiModelProperty(value = "所属第三方业务数据的key", required = "required")
    private String objectKey;

    @TableField(value = "`name`")
    @ApiModelProperty(value = "联系人姓名", required = "required")
    private String name;

    @TableField(value = "department")
    @ApiModelProperty(value = "联系人所属部门")
    private String department;

    @TableField(value = "job")
    @ApiModelProperty(value = "联系人职位")
    private String job;

    @TableField(value = "work_phone")
    @ApiModelProperty(value = "办公电话")
    private String workPhone;

    @TableField(value = "mobile_phone")
    @ApiModelProperty(value = "移动电话", required = "phone")
    private String mobilePhone;

    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱", required = "email")
    private String email;

    @TableField(value = "qq")
    @ApiModelProperty(value = "QQ号")
    private String qq;

    @TableField(value = "wechat")
    @ApiModelProperty(value = "微信")
    private String wechat;

    @TableField(value = "is_default")
    @ApiModelProperty(value = "默认联系人  1.是  2.否", required = "required,num")
    private Integer isDefault;

    /**
     * 删除标记，0未删除，1删除
     */
    @TableField(value = "delete_flag")
    private Integer deleteFlag;

}
