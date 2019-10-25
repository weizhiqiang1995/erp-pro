package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SalesOrderService {

	public void querySalesOrderToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSalesOrderMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteSalesOrderMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySalesOrderToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSalesOrderMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSalesOrderStateToSubExamineById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSalesOrderStateToExamineById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySalesOrderToTurnPutById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSalesOrderToTurnPut(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
