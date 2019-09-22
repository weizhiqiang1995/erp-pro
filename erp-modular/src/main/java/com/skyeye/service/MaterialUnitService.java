package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface MaterialUnitService {

	public void queryMaterialUnitList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertMaterialUnitMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteMaterialUnitMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMaterialUnitMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMaterialUnitMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
