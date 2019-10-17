package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface PurchaseOrderService {

	public void queryPurchaseOrderToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertPurchaseOrderMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deletePurchaseOrderMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryPurchaseOrderToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editPurchaseOrderMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editPurchaseOrderStateToSubExamineById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editPurchaseOrderStateToExamineById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryPurchaseOrderToTurnPutById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertPurchaseOrderToTurnPut(InputObject inputObject, OutputObject outputObject) throws Exception;

}
