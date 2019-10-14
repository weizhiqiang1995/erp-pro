package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface PurchaseOrderDao {

	public List<Map<String, Object>> queryPurchaseOrderToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertPurchaseOrderMation(Map<String, Object> map) throws Exception;

	public int insertPurchaseOrderChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryPurchaseOrderStateById(Map<String, Object> map) throws Exception;

	public int deletePurchaseOrderMationById(Map<String, Object> map) throws Exception;

	public int deletePurchaseOrderNormsMationById(Map<String, Object> map) throws Exception;

}
