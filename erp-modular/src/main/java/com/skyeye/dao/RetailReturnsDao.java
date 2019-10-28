package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface RetailReturnsDao {

	public List<Map<String, Object>> queryRetailReturnsToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertRetailReturnsMation(Map<String, Object> depothead) throws Exception;

	public int insertRetailReturnsChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryRetailReturnsMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryRetailReturnsItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deleteRetailReturnsChildMation(Map<String, Object> bean) throws Exception;

	public int editRetailReturnsMationById(Map<String, Object> depothead) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;

}
