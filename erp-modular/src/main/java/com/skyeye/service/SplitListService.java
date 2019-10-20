package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SplitListService {

	public void querySplitListToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSplitListMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySplitListMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSplitListMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
