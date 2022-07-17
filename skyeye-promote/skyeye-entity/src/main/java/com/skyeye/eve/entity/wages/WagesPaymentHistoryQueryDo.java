/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.wages;

import com.skyeye.annotation.api.ApiModel;
import com.skyeye.common.entity.CommonPageInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: WagesPaymentHistoryQueryDo
 * @Description: 已发放薪资发放历史列表查询条件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2022/7/17 18:21
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@ApiModel("已发放薪资发放历史列表查询条件实体类")
public class WagesPaymentHistoryQueryDo extends CommonPageInfo implements Serializable {

    private Integer state;

}
