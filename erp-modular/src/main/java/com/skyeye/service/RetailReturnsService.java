package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface RetailReturnsService {

	public void queryRetailReturnsToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertRetailReturnsMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryRetailReturnsMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editRetailReturnsMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
