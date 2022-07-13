/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ForumContentDao
 * @Description: 论坛管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 11:06
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface ForumContentDao {

    List<Map<String, Object>> queryMyForumContentList(Map<String, Object> map);

    int insertForumContentMation(Map<String, Object> map);

    int deleteForumContentById(Map<String, Object> map);

    Map<String, Object> queryForumContentMationById(Map<String, Object> map);

    int editForumContentMationById(Map<String, Object> map);

    Map<String, Object> queryForumContentMationToDetails(Map<String, Object> map);

    List<Map<String, Object>> queryNewForumContentList(Map<String, Object> map);

    int insertForumCommentMation(Map<String, Object> map);

    List<Map<String, Object>> queryForumCommentList(Map<String, Object> map);

    int insertForumReplyMation(Map<String, Object> map);

    List<Map<String, Object>> queryForumReplyList(Map<String, Object> map);

    Map<String, Object> selectForumCommentNumById(Map<String, Object> map);

    List<Map<String, Object>> queryNewCommentList(Map<String, Object> map);

    List<Map<String, Object>> queryForumListByTagId(Map<String, Object> map);

    List<Map<String, Object>> queryHotTagList(Map<String, Object> map);

    List<Map<String, Object>> queryActiveUsersList(Map<String, Object> map);

    List<Map<String, Object>> queryHotForumList(Map<String, Object> map);

    List<Map<String, Object>> queryAllHotForumList(Map<String, Object> map);

    List<Map<String, Object>> queryAllForumList(Map<String, Object> map);

    List<Map<String, Object>> selectForumTagById(Map<String, Object> bean);

    List<Map<String, Object>> queryMyCommentList(Map<String, Object> map);

    int deleteCommentById(Map<String, Object> map);

    List<Map<String, Object>> queryMyNoticeList(Map<String, Object> map);

    int deleteNoticeById(Map<String, Object> map);

    int insertForumHotByList(List<Map<String, Object>> list);

    int insertForumStatisticsDayByList(List<Map<String, Object>> list);

}
