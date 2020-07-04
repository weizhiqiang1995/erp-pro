/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyChatService {

	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editUserSignByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

}
