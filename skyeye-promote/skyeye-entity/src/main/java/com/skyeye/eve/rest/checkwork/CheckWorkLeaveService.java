/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.rest.checkwork;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;

/**
 * @ClassName: CheckWorkLeaveService
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2022/3/26 22:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-flowable}", configuration = ClientConfiguration.class)
public interface CheckWorkLeaveService {

    /**
     * 修改加班电子流的结算状态
     *
     * @param params 入参
     * @return
     */
    @PostMapping("/queryCheckWorkLeaveByMation")
    String queryCheckWorkLeaveByMation(Map<String, Object> params);

}
