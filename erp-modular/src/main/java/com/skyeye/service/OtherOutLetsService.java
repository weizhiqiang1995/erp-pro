package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface OtherOutLetsService {

	public void queryOtherOutLetsToList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void insertOtherOutLetsMation(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryOtherOutLetsToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editOtherOutLetsMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;

}
