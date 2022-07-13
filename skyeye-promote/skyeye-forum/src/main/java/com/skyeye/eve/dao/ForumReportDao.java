/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface ForumReportDao {

    int insertForumReportMation(Map<String, Object> map);

    List<Map<String, Object>> queryReportNoCheckList(Map<String, Object> map);

    int editReportCheckMationById(Map<String, Object> map);

    List<Map<String, Object>> queryReportCheckedList(Map<String, Object> map);

    Map<String, Object> queryForumReportMationToDetails(Map<String, Object> map);

    Map<String, Object> queryForumReportStateById(Map<String, Object> map);

    Map<String, Object> queryForumReportMationById(Map<String, Object> map);

    int insertForumNoticeMation(Map<String, Object> map);

}
