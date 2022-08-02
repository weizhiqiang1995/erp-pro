/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.dict;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonOperatorUserInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: SysDictDataMation
 * @Description: 数据字典实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/2 13:14
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "sys_dict_data")
@ApiModel("数据字典实体类")
public class SysDictDataMation extends CommonOperatorUserInfo implements Serializable {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("dict_name")
    @ApiModelProperty(value = "字典分类名称", required = "required")
    private String dictName;

    @TableField("dict_type_id")
    @ApiModelProperty(value = "数据字典类型id", required = "required")
    private String dictTypeId;

    /**
     * 数据字典类型名称
     */
    @TableField(exist = false)
    private String dictTypeName;

    @TableField("dict_sort")
    @ApiModelProperty(value = "字典排序", required = "required,num")
    private Integer dictSort;

    @TableField("is_default")
    @ApiModelProperty(value = "是否默认（Y是 N否）", required = "required")
    private String isDefault;

    @TableField("remark")
    @ApiModelProperty(value = "备注信息")
    private String remark;

    @TableField("status")
    @ApiModelProperty(value = "状态（0正常 1停用）", required = "required,num")
    private Integer status;

}
