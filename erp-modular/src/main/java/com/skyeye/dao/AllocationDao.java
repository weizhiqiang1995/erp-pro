package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface AllocationDao {

	public List<Map<String, Object>> queryAllocationToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertAllocationMation(Map<String, Object> depothead) throws Exception;

	public int insertAllocationChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryAllocationMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryAllocationItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deleteAllocationChildMation(Map<String, Object> bean) throws Exception;

	public int editAllocationMationById(Map<String, Object> depothead) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;

}
