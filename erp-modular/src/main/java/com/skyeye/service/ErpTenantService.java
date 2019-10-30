package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ErpTenantService {
	
	public void insertErpTenantGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void selectAllErpTenantGroupMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertErpTenantGroupUserByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editErpTenantGroupNameByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteErpTenantGroupByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteErpTenantGroupUserByGroupIdAndUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void selectUserInfoOnErpTenantGroup(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteAllErpTenantGroupUserByGroupId(InputObject inputObject, OutputObject outputObject) throws Exception;
	
}
