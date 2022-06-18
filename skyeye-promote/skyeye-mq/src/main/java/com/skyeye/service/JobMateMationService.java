/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface JobMateMationService {

	void queryJobMateMationByBigTypeList(InputObject inputObject, OutputObject outputObject) throws Exception;

	void sendMQProducer(String jsonStr, String userId) throws Exception;
	
	void comMQJobMation(String jobId, String status, String responseBody) throws Exception;

	void sendMQProducer(InputObject inputObject, OutputObject outputObject) throws Exception;
	
}
