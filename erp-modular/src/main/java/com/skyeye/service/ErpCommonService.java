/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ErpCommonService {

	public void queryDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteDepotHeadDetailsMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
