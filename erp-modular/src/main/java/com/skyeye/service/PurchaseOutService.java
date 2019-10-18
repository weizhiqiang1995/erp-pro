package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface PurchaseOutService {

	public void queryPurchaseOutToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertPurchaseOutMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryPurchaseOutMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editPurchaseOutMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
