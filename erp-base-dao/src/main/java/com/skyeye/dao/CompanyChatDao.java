package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyChatDao {
	
	public Map<String, Object> queryUserMineByUserId(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryCompanyDepartmentByUserId(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryDepartmentUserByDepartId(Map<String, Object> depart) throws Exception;

	public List<Map<String, Object>> queryUserGroupByUserId(Map<String, Object> map) throws Exception;

	public int editUserSignByUserId(Map<String, Object> map) throws Exception;
	
}
