/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.mq.consumer;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.constans.MqConstants;
import com.skyeye.common.util.SpringUtils;
import com.skyeye.exception.CustomException;
import com.skyeye.mq.job.JobMateService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 卫志强
 * @ClassName: Consumer
 * @Description: 消费者
 * @date 2019年3月3日
 */
@Component
public class Consumer {

    /**
     * activeMq监听监听接收消息队列
     *
     * @param data 从消息队列获得到的参数
     */
    @JmsListener(destination = "${queue}")
    public void receive(String data) {
        Map<String, Object> map = JSONUtil.toBean(data, null);
        String type = map.get("type").toString();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            throw new CustomException(ex);
        }
        JobMateService jobMateService = SpringUtils.getBean(MqConstants.JobMateMationJobType.getServiceNameByJobType(type));
        jobMateService.call(data);
    }

}
