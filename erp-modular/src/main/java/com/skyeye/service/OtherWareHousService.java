/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface OtherWareHousService {

	public void queryOtherWareHousToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertOtherWareHousMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryOtherWareHousToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editOtherWareHousMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
