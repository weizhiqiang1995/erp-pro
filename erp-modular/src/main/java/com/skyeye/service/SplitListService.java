/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SplitListService {

	public void querySplitListToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSplitListMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySplitListMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSplitListMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
