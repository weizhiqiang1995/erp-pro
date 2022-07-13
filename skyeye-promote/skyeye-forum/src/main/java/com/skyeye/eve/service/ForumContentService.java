/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface ForumContentService {

    void queryMyForumContentList(InputObject inputObject, OutputObject outputObject);

    void insertForumContentMation(InputObject inputObject, OutputObject outputObject);

    void deleteForumContentById(InputObject inputObject, OutputObject outputObject);

    void queryForumContentMationById(InputObject inputObject, OutputObject outputObject);

    void editForumContentMationById(InputObject inputObject, OutputObject outputObject);

    void queryForumContentMationToDetails(InputObject inputObject, OutputObject outputObject);

    void queryNewForumContentList(InputObject inputObject, OutputObject outputObject);

    void insertForumCommentMation(InputObject inputObject, OutputObject outputObject);

    void queryForumCommentList(InputObject inputObject, OutputObject outputObject);

    void insertForumReplyMation(InputObject inputObject, OutputObject outputObject);

    void queryForumReplyList(InputObject inputObject, OutputObject outputObject);

    void queryForumMyBrowerList(InputObject inputObject, OutputObject outputObject);

    void queryNewCommentList(InputObject inputObject, OutputObject outputObject);

    void queryForumListByTagId(InputObject inputObject, OutputObject outputObject);

    void queryHotTagList(InputObject inputObject, OutputObject outputObject);

    void queryActiveUsersList(InputObject inputObject, OutputObject outputObject);

    void queryHotForumList(InputObject inputObject, OutputObject outputObject);

    void querySearchForumList(InputObject inputObject, OutputObject outputObject);

    void querySolrSynchronousTime(InputObject inputObject, OutputObject outputObject);

    void updateSolrSynchronousData(InputObject inputObject, OutputObject outputObject);

    void queryMyCommentList(InputObject inputObject, OutputObject outputObject);

    void deleteCommentById(InputObject inputObject, OutputObject outputObject);

    void queryMyNoticeList(InputObject inputObject, OutputObject outputObject);

    void deleteNoticeById(InputObject inputObject, OutputObject outputObject);

}
