/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SmProjectService {

    void querySmProjectList(InputObject inputObject, OutputObject outputObject);

    void insertSmProjectMation(InputObject inputObject, OutputObject outputObject);

    void deleteSmProjectById(InputObject inputObject, OutputObject outputObject);

    void querySmProjectMationToEditById(InputObject inputObject, OutputObject outputObject);

    void editSmProjectMationById(InputObject inputObject, OutputObject outputObject);

    void queryGroupMationList(InputObject inputObject, OutputObject outputObject);

    void queryGroupMemberMationList(InputObject inputObject, OutputObject outputObject);

}
