/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.enclosure.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.enclosure.entity.Enclosure;

public interface SysEnclosureService extends SkyeyeBusinessService<Enclosure> {

    void queryEnclosureInfo(InputObject inputObject, OutputObject outputObject);

    void queryEnclosureTree(InputObject inputObject, OutputObject outputObject);
}
