package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


@Repository
@Mapper
public interface CompanyTalkGroupDao {

	public int insertGroupMation(Map<String, Object> map) throws Exception;

	public int insertGroupInviteMation(List<Map<String, Object>> beans) throws Exception;

	public int insertMakeGroupUserMation(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryGroupInvitationMation(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryGroupInvitationMationById(Map<String, Object> map) throws Exception;

	public int editAgreeInGroupInvitationMation(Map<String, Object> map) throws Exception;

	public int editRefuseInGroupInvitationMation(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryGroupMationByGroupId(Map<String, Object> bean) throws Exception;

	public List<Map<String, Object>> queryGroupMationList(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryInGroupByUserAndGroupId(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryInGroupInviteByUserAndGroupId(Map<String, Object> map) throws Exception;

	public int insertInGroupInviteByUserAndGroupId(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryCreateGroupUserByGroupId(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryGroupCreaterById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryGroupMemberByGroupId(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryGroupMemberByGroupIdAndNotThisUser(Map<String, Object> map) throws Exception;

	public int insertPersonToPersonMessage(Map<String, Object> map) throws Exception;

	public int insertPersonToGroupMessage(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryChatLogByPerToPer(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> queryChatLogByPerToGroup(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryGroupCreateIdById(Map<String, Object> map) throws Exception;

	public int deleteUserToExitGroup(Map<String, Object> map) throws Exception;

	public int editCreateToExitGroup(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryGroupStateById(Map<String, Object> map1) throws Exception;

}
