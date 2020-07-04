/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface RetailOutLetService {

	public void queryRetailOutLetToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertRetailOutLetMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryRetailOutLetMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editRetailOutLetMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
