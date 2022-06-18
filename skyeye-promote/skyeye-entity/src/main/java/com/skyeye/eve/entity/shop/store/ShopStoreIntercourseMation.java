/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity.shop.store;

import lombok.Data;

/**
 * @ClassName: ShopStoreIntercourseEntity
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/10 21:51
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
public class ShopStoreIntercourseMation {

    /**
     * 保养门店id
     */
    private String keepfitStoreId;

    /**
     * 套餐购买门店
     */
    private String mealByStoreId;

    /**
     * 总的耗材费用
     */
    private String mealAllSinglePrice;

    /**
     * 创建日期，格式为：yyyy-MM-dd
     */
    private String createTime;

    /**
     * 状态  1.待套餐购买门店确认  2.待保养门店确认  3.已确认
     */
    private Integer state;

}
