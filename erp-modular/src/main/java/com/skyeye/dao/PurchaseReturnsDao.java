package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface PurchaseReturnsDao {

	public List<Map<String, Object>> queryPurchaseReturnsToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertPurchaseReturnsMation(Map<String, Object> depothead) throws Exception;

	public int insertPurchaseReturnsChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryPurchaseReturnsMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryPurchaseReturnsItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deletePurchaseReturnsChildMation(Map<String, Object> bean) throws Exception;

	public int editPurchaseReturnsMationById(Map<String, Object> depothead) throws Exception;

}
