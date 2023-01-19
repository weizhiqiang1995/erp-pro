/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.dsform.entity.DsFormPageData;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: DsFormPageDataService
 * @Description: 用户提交的动态表单数据服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/1/8 17:42
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface DsFormPageDataService extends SkyeyeBusinessService<DsFormPageData> {

    void deleteByObjectId(String objectId);

    Map<String, List<DsFormPageData>> queryDsFormPageDataBySequenceId(List<String> sequenceIdList);

}
