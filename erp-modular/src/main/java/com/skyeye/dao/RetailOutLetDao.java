package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface RetailOutLetDao {

	public List<Map<String, Object>> queryRetailOutLetToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertRetailOutLetMation(Map<String, Object> depothead) throws Exception;

	public int insertRetailOutLetChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryRetailOutLetMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryRetailOutLetItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deleteRetailOutLetChildMation(Map<String, Object> bean) throws Exception;

	public int editRetailOutLetMationById(Map<String, Object> depothead) throws Exception;

}
