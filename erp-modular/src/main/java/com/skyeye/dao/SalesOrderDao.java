/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SalesOrderDao {

	public List<Map<String, Object>> querySalesOrderToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertSalesOrderMation(Map<String, Object> map) throws Exception;

	public int insertSalesOrderChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> querySalesOrderStateById(Map<String, Object> map) throws Exception;

	public int deleteSalesOrderMationById(Map<String, Object> map) throws Exception;

	public int deleteSalesOrderNormsMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySalesOrderToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySalesOrderNormsToEditById(Map<String, Object> map) throws Exception;

	public int editSalesOrderMationById(Map<String, Object> depothead) throws Exception;

	public int editSalesOrderStateToSubExamineById(Map<String, Object> map) throws Exception;

	public int editSalesOrderStateToExamineById(Map<String, Object> map) throws Exception;

	public Map<String, Object> querySalesOrderToTurnPutById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySalesOrderNormsToTurnPutById(Map<String, Object> map) throws Exception;

	public int insertPurchasePutMation(Map<String, Object> depothead) throws Exception;

	public int insertPurchasePutChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryOrderIsStandardById(Map<String, Object> entity) throws Exception;

	public int editSalesOrderStateToTurnById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;

}
