/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dsform.service;

import com.skyeye.base.business.service.SkyeyeBusinessService;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.dsform.entity.DsFormDisplayTemplate;

public interface DsFormDisplayTemplateService extends SkyeyeBusinessService<DsFormDisplayTemplate> {

    void queryDisplayTemplateListToShow(InputObject inputObject, OutputObject outputObject);

}
