/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface ForumSensitiveWordsDao {

    List<Map<String, Object>> queryForumSensitiveWordsList(Map<String, Object> map);

    Map<String, Object> queryForumSensitiveWordsMationByName(Map<String, Object> map);

    int insertForumSensitiveWordsMation(Map<String, Object> map);

    int deleteForumSensitiveWordsById(Map<String, Object> map);

    Map<String, Object> selectForumSensitiveWordsById(Map<String, Object> map);

    int editForumSensitiveWordsMationById(Map<String, Object> map);

    List<Map<String, Object>> queryForumSensitiveWordsListAll();
}
