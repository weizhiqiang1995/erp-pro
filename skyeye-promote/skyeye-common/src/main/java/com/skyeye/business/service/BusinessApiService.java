/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.business.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.business.entity.BusinessApi;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BusinessApiService
 * @Description: 业务对象对应的接口信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:07
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface BusinessApiService extends SkyeyeBusinessService<BusinessApi> {

    void deleteByObjectId(String objectId);

    BusinessApi selectByObjectId(String objectId);

    Map<String, BusinessApi> selectByObjectIds(List<String> objectIds);

}
