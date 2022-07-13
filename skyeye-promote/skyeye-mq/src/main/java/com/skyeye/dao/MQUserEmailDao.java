/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MQUserEmailDao {

    List<Map<String, Object>> queryEmailListByEmailAddress(Map<String, Object> map);

    int insertEmailListToServer(List<Map<String, Object>> enclosureBeans);

    int insertEmailEnclosureListToServer(List<Map<String, Object>> beans);

    List<Map<String, Object>> queryEmailListByEmailFromAddress(Map<String, Object> map);

    List<Map<String, Object>> queryDeleteEmailListByEmailFromAddress(Map<String, Object> map);

    List<Map<String, Object>> queryDraftsEmailListByEmailFromAddress(Map<String, Object> map);

    Map<String, Object> queryEmailMessageIdByEmailId(Map<String, Object> map);

    int editEmailMessageIdByEmailId(Map<String, Object> emailEditMessageId);

    Map<String, Object> queryServiceMationBySericeId(@Param("serviceId") String serviceId);

    List<Map<String, Object>> queryCooperationUserNameById(@Param("serviceId") String serviceId);

    int insertNoticeListMation(List<Map<String, Object>> notices);

}
