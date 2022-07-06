/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.rest.shop.store;

import com.skyeye.common.client.ClientConfiguration;
import com.skyeye.eve.entity.shop.store.ShopStoreIntercourseMationRest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: ShopStoreService
 * @Description: 商城门店
 * @author: skyeye云系列--卫志强
 * @date: 2022/2/2 15:24
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-shop}", configuration = ClientConfiguration.class)
public interface ShopStoreService {

    /**
     * 获取指定日期的支出/收入往来的数据
     *
     * @param day 日期，格式为yyyy-MM-dd
     * @return 指定日期的支出/收入往来的数据
     */
    @GetMapping("/queryStoreIntercourseByDay")
    String queryStoreIntercourseByDay(@RequestParam("day") String day);

    /**
     * 获取指定日期的支出/收入往来的数据---用于查指定日期的数据是否入库
     *
     * @param day 日期，格式为yyyy-MM-dd
     * @return 指定日期的支出/收入往来的数据
     */
    @GetMapping("/queryStoreIntercourseListByDay")
    String queryStoreIntercourseListByDay(@RequestParam("day") String day);

    /**
     * 新增支出/收入往来的数据
     *
     * @param shopStoreIntercourseMationRest 支出/收入往来的数据
     */
    @PostMapping("/insertStoreIntercourse")
    String insertStoreIntercourse(ShopStoreIntercourseMationRest shopStoreIntercourseMationRest);

}
