package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyChatService {

	public void getList(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void editUserSignByUserId(InputObject inputObject, OutputObject outputObject) throws Exception;

}
