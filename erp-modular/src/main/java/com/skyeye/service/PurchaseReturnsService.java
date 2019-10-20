package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface PurchaseReturnsService {

	public void queryPurchaseReturnsToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertPurchaseReturnsMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryPurchaseReturnsMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editPurchaseReturnsMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
