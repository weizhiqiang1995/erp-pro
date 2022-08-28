/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.barcode;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.ApiModelProperty;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: BarCodeApiMation
 * @Description: 条形码实体类外面的box
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/28 9:57
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("条形码实体类外面的box")
public class BarCodeApiMation extends CommonInfo implements Serializable {

    @ApiModelProperty(value = "服务名", required = "required")
    private String springApplicationName;

    @ApiModelProperty(value = "条形码类型，对应的服务层类地址", required = "required")
    private String codeImplClass;

    @ApiModelProperty(value = "条形码列表", required = "required")
    private List<BarCodeMation> barCodeList;

}
