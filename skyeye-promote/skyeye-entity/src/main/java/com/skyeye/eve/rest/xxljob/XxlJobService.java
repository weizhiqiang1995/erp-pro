/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.rest.xxljob;

import com.skyeye.common.client.ClientConfiguration;
import com.skyeye.eve.entity.xxljob.XxlJobInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: XxlJobService
 * @Description: xxl-job任务服务接口
 * @author: skyeye云系列--卫志强
 * @date: 2022/8/6 21:54
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.xxl-job}", configuration = ClientConfiguration.class)
public interface XxlJobService {

    /**
     * 根据执行器的AppName获取执行器ID
     *
     * @param appname 执行器AppName
     */
    @PostMapping("/xxl-job-admin/jobgroup/getGroupId")
    String getGroupId(@RequestParam("appname") String appname);

    /**
     * 启动定时任务
     *
     * @param xxlJobInfo 任务信息
     */
    @PostMapping("/xxl-job-admin/jobinfo/addAndStart")
    String addAndStart(XxlJobInfo xxlJobInfo);

    /**
     * 删除定时任务
     *
     * @param objectId 日程id,工作计划id等
     */
    @PostMapping("/xxl-job-admin/jobinfo/removeJob")
    String removeJob(@RequestParam("objectId") String objectId);

}
