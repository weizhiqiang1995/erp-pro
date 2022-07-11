/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface ErpPageDao {

    String queryThisMonthSales();

    String queryThisMonthRetail();

    String queryThisMonthPurchase();

    String queryThisMonthProfit();

    List<Map<String, Object>> querySixMonthPurchaseMoneyList();

    List<Map<String, Object>> querySixMonthSealsMoneyList();

    List<Map<String, Object>> queryTwelveMonthProfitMoneyList();

}
