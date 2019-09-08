package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysEveWinService {

	public void queryWinMationList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertWinMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryWinMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editWinMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void deleteWinMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editAuthorizationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editCancleAuthorizationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryWinMationListToShow(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertWinMationImportantSynchronization(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryWinMationImportantSynchronizationData(InputObject inputObject, OutputObject outputObject) throws Exception;

}
