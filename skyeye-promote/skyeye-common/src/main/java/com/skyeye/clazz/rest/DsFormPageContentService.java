/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.clazz.rest;

import com.skyeye.common.client.ClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName: DsFormPageContentService
 * @Description: 动态表单页面关联组件管理服务接口
 * @author: skyeye云系列--卫志强
 * @date: 2022/9/18 16:08
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@FeignClient(value = "${webroot.skyeye-flowable}", configuration = ClientConfiguration.class)
public interface DsFormPageContentService {

    /**
     * 查看某个动态表单中的表单控件
     *
     * @param pageId
     * @return
     */
    @GetMapping("/dsformpage004")
    String queryFormPageContentByPageId(@RequestParam("pageId") String pageId);

}
