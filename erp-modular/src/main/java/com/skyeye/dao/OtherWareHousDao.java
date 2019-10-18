package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface OtherWareHousDao {

	public List<Map<String, Object>> queryOtherWareHousToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertOtherWareHousMation(Map<String, Object> map) throws Exception;

	public int insertOtherWareHousChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryOtherWareHousToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryOtherWareHousNormsToEditById(Map<String, Object> map) throws Exception;

	public int editOtherWareHousMationById(Map<String, Object> depothead) throws Exception;

	public int deleteOtherWareHousNormsMationById(Map<String, Object> map) throws Exception;

}
