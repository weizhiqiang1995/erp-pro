/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface PurchasePutDao {

	public List<Map<String, Object>> queryPurchasePutToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertPurchasePutMation(Map<String, Object> depothead) throws Exception;

	public int insertPurchasePutChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryPurchasePutMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryPurchasePutItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deletePurchasePutChildMation(Map<String, Object> bean) throws Exception;

	public int editPurchasePutMationById(Map<String, Object> depothead) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;

}
