package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:23
 */
public interface ReceivablesDao {
	
    public List<Map<String, Object>> queryReceivablesByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public int insertReceivables(Map<String, Object> params) throws Exception;

    public int insertReceivablesItem(List<Map<String, Object>> entitys) throws Exception;

    public Map<String, Object> queryReceivablesToEditById(Map<String, Object> params) throws Exception;

    public int editReceivablesById(Map<String, Object> params) throws Exception;

    public int editReceivablesByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryReceivablesDetailById(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryReceivablesItemsDetailById(Map<String, Object> bean) throws Exception;

    public List<Map<String, Object>> queryReceivablesItemsToEditById(Map<String, Object> params) throws Exception;

    public int editReceivablesItemsByDeleteFlag(Map<String, Object> params) throws Exception;

    public int deleteReceivablesItemById(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryUserInfoById(Map<String, Object> bean) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;
	
}
