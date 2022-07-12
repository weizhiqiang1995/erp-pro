/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface UserEmailService {

    void queryEmailListByUserId(InputObject inputObject, OutputObject outputObject);

    void insertEmailListByUserId(InputObject inputObject, OutputObject outputObject);

    void insertEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject);

    void queryInboxEmailListByEmailId(InputObject inputObject, OutputObject outputObject);

    void queryEmailMationByEmailId(InputObject inputObject, OutputObject outputObject);

    void insertSendedEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject);

    void querySendedEmailListByEmailId(InputObject inputObject, OutputObject outputObject);

    void insertDelsteEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject);

    void queryDeleteEmailListByEmailId(InputObject inputObject, OutputObject outputObject);

    void insertDraftsEmailListFromServiceByUserId(InputObject inputObject, OutputObject outputObject);

    void queryDraftsEmailListByEmailId(InputObject inputObject, OutputObject outputObject);

    void insertToSendEmailMationByUserId(InputObject inputObject, OutputObject outputObject);

    void insertToDraftsEmailMationByUserId(InputObject inputObject, OutputObject outputObject);

    void queryDraftsEmailMationToEditByUserId(InputObject inputObject, OutputObject outputObject);

    void editToDraftsEmailMationByUserId(InputObject inputObject, OutputObject outputObject);

    void insertToSendEmailMationByEmailId(InputObject inputObject, OutputObject outputObject);

    void queryForwardEmailMationToEditByUserId(InputObject inputObject, OutputObject outputObject);

    void insertForwardToSendEmailMationByUserId(InputObject inputObject, OutputObject outputObject);

}
