/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.quartz.consumer.impl;

import cn.hutool.json.JSONUtil;
import com.skyeye.common.client.ExecuteFeignClient;
import com.skyeye.common.constans.MqConstants;
import com.skyeye.common.util.DataCommonUtil;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.eve.dao.SysQuartzDao;
import com.skyeye.eve.rest.xxljob.XxlJobService;
import com.skyeye.service.JobMateMationService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的日程提醒
 */
@Component
public class MyScheduleDayMationService {

    @Autowired
    private SysQuartzDao sysQuartzDao;

    @Autowired
    private JobMateMationService jobMateMationService;

    @Autowired
    private XxlJobService xxlJobService;

    @XxlJob("myScheduleDayMationService")
    public void call() {
        String param = XxlJobHelper.getJobParam();
        Map<String, String> paramMap = JSONUtil.toBean(param, null);
        Map<String, Object> bean = new HashMap<>();
        bean.put("id", paramMap.get("objectId"));
        bean = sysQuartzDao.queryMationByScheduleId(bean);
        //发送消息
        String content = "尊敬的" + bean.get("userName").toString() + ",您好：<br/>您于" + bean.get("createTime").toString() + "设定的日程《" + bean.get("title").toString() + "》即将于" + bean.get("startTime").toString() + "开始，请做好准备哦。";
        if (!ToolUtil.isBlank(bean.get("remarks").toString())) {
            content += "<br>备注信息：" + bean.get("remarks").toString();
        }

        //调用消息系统添加通知
        Map<String, Object> notice = new HashMap<>();
        notice.put("title", "日程提醒");
        notice.put("noticeDesc", "您有一条新的日程信息，请及时阅读。");
        notice.put("content", content);
        notice.put("state", "1");//未读消息
        notice.put("userId", bean.get("userId"));
        notice.put("type", "1");//消息类型
        DataCommonUtil.setCommonData(notice, "0dc9dd4cd4d446ae9455215fe753c44e");
        sysQuartzDao.insertNoticeMation(notice);

        //发送邮件
        if (!ToolUtil.isBlank(bean.get("email").toString()) && bean.containsKey("email")) {
            Map<String, Object> emailNotice = new HashMap<>();
            emailNotice.put("title", "日程提醒");
            emailNotice.put("content", content);
            emailNotice.put("email", bean.get("email").toString());
            emailNotice.put("type", MqConstants.JobMateMationJobType.ORDINARY_MAIL_DELIVERY.getJobType());
            jobMateMationService.sendMQProducer(JSONUtil.toJsonStr(emailNotice), paramMap.get("userId"));
        }
        //修改日程状态
        sysQuartzDao.editMationByScheduleId(bean);
        ExecuteFeignClient.get(() -> xxlJobService.removeJob(paramMap.get("objectId")));
    }

}
