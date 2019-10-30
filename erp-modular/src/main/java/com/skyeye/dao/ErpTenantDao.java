package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface ErpTenantDao {
	
	public int insertErpTenantGroupMation(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> selectAllErpTenantGroupMation(Map<String, Object> map) throws Exception;

	public int insertErpTenantGroupUserByGroupId(List<Map<String, Object>> beans) throws Exception;

	public int editErpTenantGroupNameByGroupId(Map<String, Object> map) throws Exception;

	public int deleteErpTenantGroupByGroupId(Map<String, Object> map) throws Exception;

	public int deleteErpTenantGroupUserByGroupId(Map<String, Object> map) throws Exception;

	public int deleteErpTenantGroupUserByGroupIdAndUserId(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> selectUserInfoOnErpTenantGroup(Map<String, Object> map, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> queryUserInGroupByUserId(Map<String, Object> item) throws Exception;
	
}
