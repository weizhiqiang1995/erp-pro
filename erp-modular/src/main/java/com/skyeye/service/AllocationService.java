/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface AllocationService {

	public void queryAllocationToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertAllocationMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryAllocationMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editAllocationMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
