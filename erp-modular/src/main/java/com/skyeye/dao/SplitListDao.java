package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SplitListDao {

	public List<Map<String, Object>> querySplitListToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertSplitListMation(Map<String, Object> depothead) throws Exception;

	public int insertSplitListChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> querySplitListMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySplitListItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deleteSplitListChildMation(Map<String, Object> bean) throws Exception;

	public int editSplitListMationById(Map<String, Object> depothead) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;

}
