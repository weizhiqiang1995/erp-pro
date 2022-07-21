/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service.impl;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.eve.dao.ErpPageDao;
import com.skyeye.eve.service.ErpPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErpPageServiceImpl implements ErpPageService {

    @Autowired
    private ErpPageDao erpPageDao;

    /**
     * 获取本月累计销售，本月累计零售，本月累计采购，本月利润（已审核通过）
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryFourTypeMoneyList(InputObject inputObject, OutputObject outputObject) {
        // 1.获取本月累计销售，当前月已审核通过的销售订单金额
        String salesMoney = erpPageDao.queryThisMonthSales();
        // 2.获取本月累计零售，当前月已审核通过的零售订单金额
        String retailMoney = erpPageDao.queryThisMonthRetail();
        // 3.获取本月累计采购，当前月已审核通过的采购订单金额
        String purchaseMoney = erpPageDao.queryThisMonthPurchase();
        // 4.本月利润（已审核通过），零售订单金额 + 销售订单金额 - 采购订单金额
        String profitMoney = erpPageDao.queryThisMonthProfit();
        Map<String, Object> map = new HashMap<>();
        map.put("salesMoney", salesMoney);
        map.put("retailMoney", retailMoney);
        map.put("purchaseMoney", purchaseMoney);
        map.put("profitMoney", profitMoney);
        outputObject.setBean(map);
    }

    /**
     * 获取近半年的采购统计
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySixMonthPurchaseMoneyList(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> beans = erpPageDao.querySixMonthPurchaseMoneyList();
        outputObject.setBeans(beans);
    }

    /**
     * 获取近半年的销售统计
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySixMonthSealsMoneyList(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> beans = erpPageDao.querySixMonthSealsMoneyList();
        outputObject.setBeans(beans);
    }

    /**
     * 获取近十二个月的利润统计
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void queryTwelveMonthProfitMoneyList(InputObject inputObject, OutputObject outputObject) {
        List<Map<String, Object>> beans = erpPageDao.queryTwelveMonthProfitMoneyList();
        outputObject.setBeans(beans);
    }

}
