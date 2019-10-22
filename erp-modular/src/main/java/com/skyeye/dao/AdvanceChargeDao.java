package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:23
 */
public interface AdvanceChargeDao {
	
    public List<Map<String, Object>> queryAdvanceChargeByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public int insertAdvanceCharge(Map<String, Object> params) throws Exception;

    public int insertAdvanceChargeItem(List<Map<String, Object>> entitys) throws Exception;

    public Map<String, Object> queryAdvanceChargeToEditById(Map<String, Object> params) throws Exception;

    public int editAdvanceChargeById(Map<String, Object> params) throws Exception;

    public int editAdvanceChargeByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryAdvanceChargeDetailById(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryAdvanceChargeItemsDetailById(Map<String, Object> bean) throws Exception;

    public List<Map<String, Object>> queryAdvanceChargeItemsToEditById(Map<String, Object> params) throws Exception;

    public int editAdvanceChargeItemsByDeleteFlag(Map<String, Object> params) throws Exception;

    public int deleteAdvanceChargeItemById(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryUserInfoById(Map<String, Object> bean) throws Exception;
	
}
