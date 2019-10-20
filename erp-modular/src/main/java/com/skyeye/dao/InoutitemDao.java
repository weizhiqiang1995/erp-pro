package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/10/6 15:42
 */
public interface InoutitemDao {
    public List<Map<String, Object>> queryInoutitemByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public Map<String, Object> queryInoutitemByName(Map<String, Object> params) throws Exception;

    public int insertInoutitem(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryInoutitemById(Map<String, Object> params) throws Exception;

    public int editInoutitemByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryInoutitemByIdAndName(Map<String, Object> params) throws Exception;

    public int editInoutitemById(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryInoutitemByIdAndInfo(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryInoutitemISExpenditureToSelect(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryInoutitemISExpenditureIncomeToSelect(Map<String, Object> params) throws Exception;
}
