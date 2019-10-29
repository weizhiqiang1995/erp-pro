package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:23
 */
public interface PayMentDao {
	
    public List<Map<String, Object>> queryPayMentByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public int insertPayMent(Map<String, Object> params) throws Exception;

    public int insertPayMentItem(List<Map<String, Object>> entitys) throws Exception;

    public Map<String, Object> queryPayMentToEditById(Map<String, Object> params) throws Exception;

    public int editPayMentById(Map<String, Object> params) throws Exception;

    public int editPayMentByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryPayMentDetailById(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryPayMentItemsDetailById(Map<String, Object> bean) throws Exception;

    public List<Map<String, Object>> queryPayMentItemsToEditById(Map<String, Object> params) throws Exception;

    public int editPayMentItemsByDeleteFlag(Map<String, Object> params) throws Exception;

    public int deletePayMentItemById(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryUserInfoById(Map<String, Object> bean) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;
	
}
