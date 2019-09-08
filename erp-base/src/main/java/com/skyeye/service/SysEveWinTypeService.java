package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinTypeService {

	public void querySysWinTypeList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySysWinFirstTypeList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertSysWinTypeMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySysWinTypeMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSysWinTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySysWinFirstTypeListNotIsThisId(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteSysWinTypeMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSysWinTypeMationOrderNumUpById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSysWinTypeMationOrderNumDownById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSysWinTypeMationStateUpById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editSysWinTypeMationStateDownById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySysWinTypeFirstMationStateIsUp(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void querySysWinTypeSecondMationStateIsUp(InputObject inputObject, OutputObject outputObject) throws Exception;

}
