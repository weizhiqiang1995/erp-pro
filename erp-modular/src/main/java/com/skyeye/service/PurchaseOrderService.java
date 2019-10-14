package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface PurchaseOrderService {

	public void queryPurchaseOrderToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertPurchaseOrderMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deletePurchaseOrderMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
