package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface PurchaseOutDao {

	public List<Map<String, Object>> queryPurchaseOutToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertPurchaseOutMation(Map<String, Object> depothead) throws Exception;

	public int insertPurchaseOutChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryPurchaseOutMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryPurchaseOutItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deletePurchaseOutChildMation(Map<String, Object> bean) throws Exception;

	public int editPurchaseOutMationById(Map<String, Object> depothead) throws Exception;

}
