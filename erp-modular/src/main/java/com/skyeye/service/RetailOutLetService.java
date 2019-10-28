package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface RetailOutLetService {

	public void queryRetailOutLetToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertRetailOutLetMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryRetailOutLetMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editRetailOutLetMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
