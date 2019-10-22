package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface StatisticsService {

	public void queryWarehousingDetails(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryOutgoingDetails(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryInComimgDetails(InputObject inputObject, OutputObject outputObject) throws Exception;

}
