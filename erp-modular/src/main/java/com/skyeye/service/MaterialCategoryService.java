/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface MaterialCategoryService {

	public void queryMaterialCategoryList(InputObject inputObject, OutputObject outputObject) throws Exception;
	
	public void insertMaterialCategoryMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void updateUpMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void updateDownMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void selectMaterialCategoryById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMaterialCategoryMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMaterialCategoryMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editMaterialCategoryMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryFirstMaterialCategoryUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryAllFirstMaterialCategoryStateList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySecondMaterialCategoryUpStateList(InputObject inputObject, OutputObject outputObject) throws Exception;

}
