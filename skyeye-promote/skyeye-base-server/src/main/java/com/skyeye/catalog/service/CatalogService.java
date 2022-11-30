/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.catalog.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.catalog.entity.Catalog;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @ClassName: CatalogService
 * @Description: 目录管理服务接口层
 * @author: skyeye云系列--卫志强
 * @date: 2022/11/28 21:59
 * @Copyright: 2022 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CatalogService extends SkyeyeBusinessService<Catalog> {

    void queryCatalogForTree(InputObject inputObject, OutputObject outputObject);

    void queryCatalogList(InputObject inputObject, OutputObject outputObject);
}
