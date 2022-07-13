/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyTalkGroupService {

    void insertGroupMation(InputObject inputObject, OutputObject outputObject);

    void queryGroupInvitationMation(InputObject inputObject, OutputObject outputObject);

    void editAgreeInGroupInvitationMation(InputObject inputObject, OutputObject outputObject);

    void editRefuseInGroupInvitationMation(InputObject inputObject, OutputObject outputObject);

    void queryGroupMationList(InputObject inputObject, OutputObject outputObject);

    void insertGroupMationToTalk(InputObject inputObject, OutputObject outputObject);

    void queryGroupMemberByGroupId(InputObject inputObject, OutputObject outputObject);

    void queryChatLogByType(InputObject inputObject, OutputObject outputObject);

    void editUserToExitGroup(InputObject inputObject, OutputObject outputObject);

    void editCreateToExitGroup(InputObject inputObject, OutputObject outputObject);

}
