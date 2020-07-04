/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SalesOutLetService {

	public void querySalesOutLetToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSalesOutLetMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySalesOutLetMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSalesOutLetMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
