/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.sys.quartz;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.QuartzConstants;
import com.skyeye.common.util.DateUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.entity.quartz.SysQuartzRunHistory;
import com.skyeye.eve.entity.shop.store.ShopStoreIntercourseMation;
import com.skyeye.eve.entity.shop.store.ShopStoreIntercourseVO;
import com.skyeye.eve.rest.shop.store.ShopStoreService;
import com.skyeye.eve.service.SysQuartzRunHistoryService;
import com.skyeye.jedis.util.RedisLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ShopStoreIntercourseQuartz
 * @Description: 门店昨日支出/收入往来计算
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/10 21:13
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Component
public class ShopStoreIntercourseQuartz {

    private static Logger log = LoggerFactory.getLogger(ShopStoreIntercourseQuartz.class);

    private static final String QUARTZ_ID = QuartzConstants.SysQuartzMateMationJobType.SHOP_STORE_INTERCOURSE_QUARTZ.getQuartzId();

    @Autowired
    private SysQuartzRunHistoryService sysQuartzRunHistoryService;

    @Autowired
    private ShopStoreService shopStoreService;

    private static final String LOCK_KEY = "calcShopStoreIntercourse";

    /**
     * 定时器计算门店昨日支出/收入往来信息,每天凌晨两点执行一次
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void calcShopStoreIntercourse() throws Exception {
        String historyId = sysQuartzRunHistoryService.startSysQuartzRun(QUARTZ_ID);
        log.info("定时器计算门店昨日支出/收入往来信息执行 start");
        RedisLock lock = new RedisLock(LOCK_KEY);
        try {
            if (!lock.lock()) {
                // 加锁失败
                return;
            }
            // 得到昨天的时间
            String yesterdayTime = DateUtil.getSpecifiedDayMation(DateUtil.getTimeAndToString(), "yyyy-MM-dd", 0, 1, 7);
            log.info("yesterdayTime is {}.", yesterdayTime);
            // 判断昨天的数据是否已经统计过并入库,如果已经统计过，则不会进行下一次的统计
            List<Map<String, Object>> yesterdayData = ExecuteFeignClient.get(() -> shopStoreService.queryStoreIntercourseListByDay(yesterdayTime)).getRows();
            if (!CollectionUtils.isEmpty(yesterdayData)) {
                log.info("已统计过昨日数据，不再进行统计");
                return;
            }
            log.info("开始统计");
            // 获取昨天的往来数据信息
            yesterdayData = ExecuteFeignClient.get(() -> shopStoreService.queryStoreIntercourseByDay(yesterdayTime)).getRows();
            List<ShopStoreIntercourseMation> shopStoreIntercourse = JSONUtil.toList(JSON.toJSONString(yesterdayData), ShopStoreIntercourseMation.class);
            shopStoreIntercourse.forEach(bean -> {
                if (ToolUtil.isBlank(bean.getMealByStoreId())) {
                    bean.setState(2);
                } else {
                    bean.setState(1);
                }
            });
            log.info("解析数据为 {}.", JSON.toJSONString(shopStoreIntercourse));
            ShopStoreIntercourseVO shopStoreIntercourseVO = new ShopStoreIntercourseVO();
            shopStoreIntercourseVO.setShopStoreIntercourseMationList(shopStoreIntercourse);
            shopStoreService.insertStoreIntercourse(shopStoreIntercourseVO);
            log.info("保存数据完成");
        } catch (Exception e) {
            sysQuartzRunHistoryService.endSysQuartzRun(historyId, SysQuartzRunHistory.State.START_ERROR.getState());
            log.warn("calcShopStoreIntercourse error.", e);
        } finally {
            lock.unlock();
        }
        log.info("定时器计算门店昨日支出/收入往来信息 end");
        sysQuartzRunHistoryService.endSysQuartzRun(historyId, SysQuartzRunHistory.State.START_SUCCESS.getState());
    }

}
