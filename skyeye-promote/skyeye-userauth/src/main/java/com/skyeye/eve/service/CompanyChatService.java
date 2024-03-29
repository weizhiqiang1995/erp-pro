/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyChatService {

    void getList(InputObject inputObject, OutputObject outputObject);

    void editUserSignByUserId(InputObject inputObject, OutputObject outputObject);

}
