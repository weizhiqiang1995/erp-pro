/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommonDao {

	public int insertCodeModelHistory(List<Map<String, Object>> inBeans) throws Exception;

	public List<Map<String, Object>> queryAllPeopleToTree(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryCompanyMationByUserId(Map<String, Object> user) throws Exception;

	public List<Map<String, Object>> queryCompanyPeopleToTreeByUserBelongCompany(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryDepartmentPeopleToTreeByUserBelongDepartment(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryJobPeopleToTreeByUserBelongJob(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySimpleDepPeopleToTreeByUserBelongSimpleDep(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryTalkGroupUserListByUserId(Map<String, Object> map) throws Exception;

}
