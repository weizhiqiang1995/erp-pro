package com.skyeye.dao;

import java.util.List;
import java.util.Map;

public interface ErpCommonDao {

	public Map<String, Object> queryDepotHeadDetailsMationById(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> queryDepotItemDetailsMationById(Map<String, Object> bean) throws Exception;

	public Map<String, Object> queryOddNumberBySubType(Map<String, Object> bean) throws Exception;

	public Map<String, Object> queryInoutitemMationById(Map<String, Object> map) throws Exception;

	public Map<String, Object> getAccountOrderNumBySubType(Map<String, Object> bean) throws Exception;

	public int deleteDepotHeadDetailsMationById(Map<String, Object> map) throws Exception;

	public int deleteDepotHeadDetailsNormsMationById(Map<String, Object> map) throws Exception;

}
