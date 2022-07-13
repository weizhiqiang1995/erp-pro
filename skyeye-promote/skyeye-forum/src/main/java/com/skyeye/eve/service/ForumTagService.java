/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ForumTagService {

    void queryForumTagList(InputObject inputObject, OutputObject outputObject);

    void insertForumTagMation(InputObject inputObject, OutputObject outputObject);

    void deleteForumTagById(InputObject inputObject, OutputObject outputObject);

    void updateUpForumTagById(InputObject inputObject, OutputObject outputObject);

    void updateDownForumTagById(InputObject inputObject, OutputObject outputObject);

    void selectForumTagById(InputObject inputObject, OutputObject outputObject);

    void editForumTagMationById(InputObject inputObject, OutputObject outputObject);

    void editForumTagMationOrderNumUpById(InputObject inputObject, OutputObject outputObject);

    void editForumTagMationOrderNumDownById(InputObject inputObject, OutputObject outputObject);

    void queryForumTagUpStateList(InputObject inputObject, OutputObject outputObject);

}
