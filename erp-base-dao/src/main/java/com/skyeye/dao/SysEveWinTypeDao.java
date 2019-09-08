package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysEveWinTypeDao {

	public List<Map<String, Object>> querySysWinTypeList(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySysWinFirstTypeList(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeByNameANDLevel(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeBySimpleLevel(Map<String, Object> map) throws Exception;

	public int insertSysWinTypeMation(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeMationToEditById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeByNameANDLevelAndId(Map<String, Object> map) throws Exception;

	public int editSysWinTypeMationById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySysWinFirstTypeListNotIsThisId(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeParentMationById(Map<String, Object> map) throws Exception;

	public int deleteSysWinTypeMationById(Map<String, Object> map) throws Exception;

	public int deleteSysWinTypeChildMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeUpMationById(Map<String, Object> map) throws Exception;

	public int editSysWinTypeMationOrderNumUpById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeDownMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySysWinTypeStateById(Map<String, Object> map) throws Exception;

	public int editSysWinTypeMationStateUpById(Map<String, Object> map) throws Exception;

	public int editSysWinTypeMationStateDownById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySysWinTypeFirstMationStateIsUp(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySysWinTypeSecondMationStateIsUp(Map<String, Object> map) throws Exception;
	
	
}
