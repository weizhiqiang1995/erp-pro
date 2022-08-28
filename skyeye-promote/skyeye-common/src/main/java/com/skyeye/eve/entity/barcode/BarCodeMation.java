/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.barcode;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: BarCodeMation
 * @Description: 条形码实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/28 9:45
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "bar_code")
@ApiModel("条形码实体类")
public class BarCodeMation extends CommonInfo implements Serializable {

    @TableId("id")
    @ApiModelProperty(value = "主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("object_id")
    @ApiModelProperty(value = "关联的业务数据id,例如资产明细id", required = "required")
    private String objectId;

    @TableField("code_num")
    @ApiModelProperty(value = "条形码编号", required = "required")
    private String codeNum;

    /**
     * 服务名
     */
    @TableField("spring_application_name")
    private String springApplicationName;

    /**
     * 条形码类型，对应的服务层类地址
     */
    @TableField("code_impl_class")
    private String codeImplClass;

    @TableField("image_path")
    @ApiModelProperty(value = "条形码路径", required = "required")
    private String imagePath;

    /**
     * 录入日期
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private String createTime;

}
