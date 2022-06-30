/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface JobMateMationService {

    void queryJobMateMationByBigTypeList(InputObject inputObject, OutputObject outputObject);

    void sendMQProducer(String jsonStr, String userId);

    void comMQJobMation(String jobId, String status, String responseBody);

    void sendMQProducer(InputObject inputObject, OutputObject outputObject);

}
