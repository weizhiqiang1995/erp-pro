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
public class SysDictDataMation extends CommonOperatorUserInfo {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("dict_name")
    @ApiModelProperty(value = "字典名称", required = "required")
    private String dictName;

    /**
     * 多级节点时，节点路径名称
     */
    @TableField(exist = false)
    private String pathName;

    @TableField("dict_type_id")
    @ApiModelProperty(value = "数据字典类型id", required = "required")
    private String dictTypeId;

    @TableField("dict_sort")
    @ApiModelProperty(value = "字典排序", required = "required,num")
    private Integer dictSort;

    @TableField("parent_id")
    @ApiModelProperty(value = "父节点id", required = "required")
    private String parentId;

    @TableField("level")
    @ApiModelProperty(value = "级别", required = "required,num")
    private Integer level;

    @TableField("is_default")
    @ApiModelProperty(value = "是否默认 1.是  2.否", required = "required,num")
    private Integer isDefault;

    @TableField("remark")
    @ApiModelProperty(value = "备注信息")
    private String remark;

    @TableField("enabled")
    @ApiModelProperty(value = "状态（1 启用  2.停用）", required = "required,num")
    private Integer enabled;

}
