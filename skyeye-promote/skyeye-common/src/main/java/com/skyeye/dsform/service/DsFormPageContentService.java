/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.dsform.entity.DsFormPageContent;

import java.util.List;

/**
 * @ClassName: DsFormPageContentService
 * @Description: 动态表单页面内容项服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/8 16:48
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface DsFormPageContentService extends SkyeyeBusinessService<DsFormPageContent> {

    /**
     * 根据动态表单pageId获取动态表单的内容项
     *
     * @param pageId 动态表单pageId
     * @return 动态表单的内容项
     */
    List<DsFormPageContent> getDsFormPageContentByPageId(String pageId);

    void deleteDsFormContentByPageId(String pageId);

}
