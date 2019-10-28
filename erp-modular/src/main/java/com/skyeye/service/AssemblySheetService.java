package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface AssemblySheetService {

	public void queryAssemblySheetToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertAssemblySheetMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryAssemblySheetMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editAssemblySheetMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
