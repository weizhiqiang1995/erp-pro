package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface OtherOutLetsDao {

	public List<Map<String, Object>> queryOtherOutLetsToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

	public Map<String, Object> queryMaterialsById(Map<String, Object> bean) throws Exception;

	public int insertOtherOutLetsMation(Map<String, Object> map) throws Exception;

	public int insertOtherOutLetsChildMation(List<Map<String, Object>> entitys) throws Exception;

	public Map<String, Object> queryOtherOutLetsToEditById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryOtherOutLetsNormsToEditById(Map<String, Object> map) throws Exception;

	public int editOtherOutLetsMationById(Map<String, Object> depothead) throws Exception;

	public int deleteOtherOutLetsNormsMationById(Map<String, Object> map) throws Exception;

}
