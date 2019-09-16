package com.skyeye.dao;

import java.util.List;
import java.util.Map;

public interface MaterialCategoryDao {

	public List<Map<String, Object>> queryMaterialCategoryList(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> queryMaterialCategoryMationByName(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> queryMaterialCategoryMationByNameAndId(Map<String, Object> map) throws Exception;

	public int insertMaterialCategoryMation(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialCategoryBySimpleLevel(Map<String, Object> map) throws Exception;

	public int deleteMaterialCategoryById(Map<String, Object> map) throws Exception;

	public int updateUpMaterialCategoryById(Map<String, Object> map) throws Exception;

	public int updateDownMaterialCategoryById(Map<String, Object> map) throws Exception;

	public Map<String, Object> selectMaterialCategoryById(Map<String, Object> map) throws Exception;

	public int editMaterialCategoryMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialCategoryUpMationById(Map<String, Object> map) throws Exception;

	public int editMaterialCategoryMationOrderNumUpById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialCategoryDownMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialCategoryStateById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryFirstMaterialCategoryUpStateList(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryAllFirstMaterialCategoryStateList(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySecondMaterialCategoryUpStateList(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialCategoryById(Map<String, Object> map) throws Exception;

	public int deleteMaterialCategoryByParentId(Map<String, Object> map) throws Exception;


}
