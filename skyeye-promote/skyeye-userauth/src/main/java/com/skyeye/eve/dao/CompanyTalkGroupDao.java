/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CompanyTalkGroupDao
 * @Description: 群组信息管理数据层
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/7 22:52
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface CompanyTalkGroupDao {

    int insertGroupMation(Map<String, Object> map);

    int insertGroupInviteMation(List<Map<String, Object>> beans);

    int insertMakeGroupUserMation(Map<String, Object> map);

    List<Map<String, Object>> queryGroupInvitationMation(Map<String, Object> map);

    Map<String, Object> queryGroupInvitationMationById(Map<String, Object> map);

    int editAgreeInGroupInvitationMation(Map<String, Object> map);

    int editRefuseInGroupInvitationMation(Map<String, Object> map);

    Map<String, Object> queryGroupMationByGroupId(Map<String, Object> bean);

    List<Map<String, Object>> queryGroupMationList(Map<String, Object> map);

    Map<String, Object> queryInGroupByUserAndGroupId(Map<String, Object> map);

    Map<String, Object> queryInGroupInviteByUserAndGroupId(Map<String, Object> map);

    int insertInGroupInviteByUserAndGroupId(Map<String, Object> map);

    Map<String, Object> queryCreateGroupUserByGroupId(Map<String, Object> map);

    List<Map<String, Object>> queryGroupMemberByGroupId(Map<String, Object> map);

    List<Map<String, Object>> queryGroupMemberByGroupIdAndNotThisUser(Map<String, Object> map);

    int insertPersonToPersonMessage(Map<String, Object> map);

    int insertPersonToGroupMessage(Map<String, Object> map);

    List<Map<String, Object>> queryChatLogByPerToPer(Map<String, Object> map);

    List<Map<String, Object>> queryChatLogByPerToGroup(Map<String, Object> map);

    Map<String, Object> queryGroupCreateIdById(Map<String, Object> map);

    int deleteUserToExitGroup(Map<String, Object> map);

    int editCreateToExitGroup(Map<String, Object> map);

    Map<String, Object> queryGroupStateById(Map<String, Object> map1);

}
