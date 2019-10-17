package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface PurchasePutService {

	public void queryPurchasePutToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertPurchasePutMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryPurchasePutMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editPurchasePutMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
