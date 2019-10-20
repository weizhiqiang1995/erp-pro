package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SalesReturnsService {

	public void querySalesReturnsToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSalesReturnsMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySalesReturnsMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSalesReturnsMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
