package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveMenuAuthPointService {

	public void querySysEveMenuAuthPointListByMenuId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSysEveMenuAuthPointMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySysEveMenuAuthPointMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteSysEveMenuAuthPointMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

}
