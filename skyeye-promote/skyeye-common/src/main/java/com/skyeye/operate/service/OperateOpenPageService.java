/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.operate.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.operate.entity.OperateOpenPage;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: OperateOpenPageService
 * @Description: 操作信息对应的新开页面信息服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2023/1/29 18:07
 * @Copyright: 2023 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface OperateOpenPageService extends SkyeyeBusinessService<OperateOpenPage> {

    void deleteByOperateId(String operateId);

    OperateOpenPage selectByOperateId(String operateId);

    Map<String, OperateOpenPage> selectByOperateIds(List<String> operateIds);

}
