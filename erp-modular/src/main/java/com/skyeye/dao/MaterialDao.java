package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface MaterialDao {

	public List<Map<String, Object>> queryMaterialListByUserId(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public List<Map<String, Object>> queryMaterialUnitListToSelectByUserId(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryMaterialUnitById(Map<String, Object> bean) throws Exception;

	public Map<String, Object> queryMaterialUnitByUnitId(Map<String, Object> bean) throws Exception;

	public int insertMaterialNormsList(List<Map<String, Object>> materialNorms) throws Exception;

	public int insertMaterialMation(Map<String, Object> material) throws Exception;

	public int insertMaterialNorms(Map<String, Object> entity) throws Exception;

	public Map<String, Object> queryMaterialEnabledByIdAndUserId(Map<String, Object> map) throws Exception;

	public int editMaterialEnabledToDisablesById(Map<String, Object> map) throws Exception;

	public int editMaterialEnabledToEnablesById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialDeleteFlagByIdAndUserId(Map<String, Object> map) throws Exception;

	public int deleteMaterialMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialMationDetailsById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryMaterialNormsMationDetailsById(Map<String, Object> bean) throws Exception;

	public int deleteMaterialNormsMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialByNameAndModel(Map<String, Object> map) throws Exception;

	public Map<String, Object> queryMaterialMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryMaterialNormsMationToEditById(Map<String, Object> bean) throws Exception;

	public Map<String, Object> queryMaterialByNameAndModelAndId(Map<String, Object> map) throws Exception;

	public int editMaterialMationById(Map<String, Object> material) throws Exception;

	public Map<String, Object> queryMaterialById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryMaterialNormsById(Map<String, Object> map) throws Exception;

	public void editMaterialNormsById(Map<String, Object> entity) throws Exception;

	public List<Map<String, Object>> queryMaterialListToSelect(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryMaterialUnitByIdToSelect(Map<String, Object> bean) throws Exception;

	public Map<String, Object> queryMaterialTockByNormsIdAndDepotId(Map<String, Object> params) throws Exception;

}
