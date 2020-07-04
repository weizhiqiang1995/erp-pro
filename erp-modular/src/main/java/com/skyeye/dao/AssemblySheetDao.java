/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface AssemblySheetDao {

	public List<Map<String, Object>> queryAssemblySheetToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertAssemblySheetMation(Map<String, Object> depothead) throws Exception;

	public int insertAssemblySheetChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryAssemblySheetMationToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryAssemblySheetItemMationToEditById(Map<String, Object> bean) throws Exception;

	public int deleteAssemblySheetChildMation(Map<String, Object> bean) throws Exception;

	public int editAssemblySheetMationById(Map<String, Object> depothead) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;

}
