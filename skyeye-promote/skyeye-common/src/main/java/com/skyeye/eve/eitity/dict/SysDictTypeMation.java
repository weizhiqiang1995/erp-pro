/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.eitity.dict;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysDictTypeMation
 * @Description: 数据字典类型实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/6/30 22:35
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_dict_type")
@ApiModel("数据字典类型实体类")
public class SysDictTypeMation extends CommonOperatorUserInfo implements Serializable {

    @TableField("id")
    private String id;

    @TableField("dict_name")
    @ApiModelProperty(value = "字典分类名称", required = "required")
    private String dictName;

    @TableField("dict_code")
    @ApiModelProperty(value = "字典分类CODE，需要保证唯一", required = "required")
    private String dictCode;

    @TableField("remark")
    @ApiModelProperty(value = "备注信息")
    private String remark;

    @TableField("dict_type")
    @ApiModelProperty(value = "字典类型  1. 一级分类  2. 多级分类", required = "required,num")
    private Integer dictType;

    @TableField("status")
    @ApiModelProperty(value = "状态（0正常 1停用）", required = "required,num")
    private Integer status;

}
