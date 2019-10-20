package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SalesReturnsDao {

	public List<Map<String, Object>> querySalesReturnsToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertSalesReturnsMation(Map<String, Object> depothead) throws Exception;

	public int insertSalesReturnsChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> querySalesReturnsMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySalesReturnsItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deleteSalesReturnsChildMation(Map<String, Object> bean) throws Exception;

	public int editSalesReturnsMationById(Map<String, Object> depothead) throws Exception;

	public List<Map<String, Object>> querySalesManUserInfoById(Map<String, Object> bean) throws Exception;

}
