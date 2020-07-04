/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface SalesOutLetDao {

	public List<Map<String, Object>> querySalesOutLetToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertSalesOutLetMation(Map<String, Object> depothead) throws Exception;

	public int insertSalesOutLetChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> querySalesOutLetMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> querySalesOutLetItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deleteSalesOutLetChildMation(Map<String, Object> bean) throws Exception;

	public int editSalesOutLetMationById(Map<String, Object> depothead) throws Exception;

	public List<Map<String, Object>> querySalesManUserInfoById(Map<String, Object> bean) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;

}
