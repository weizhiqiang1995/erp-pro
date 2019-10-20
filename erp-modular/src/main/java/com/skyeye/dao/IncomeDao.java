package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/10/20 10:23
 */
public interface IncomeDao {
    public List<Map<String, Object>> queryIncomeByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public void insertIncome(Map<String, Object> params) throws Exception;

    public void insertIncomeItem(List<Map<String, Object>> entitys) throws Exception;

    public Map<String, Object> queryIncomeById(Map<String, Object> params) throws Exception;

    public void editIncomById(Map<String, Object> params) throws Exception;

    public void editIncomeByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryIncomeByDetail(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryIncomeItemsByDetail(Map<String, Object> bean) throws Exception;

    public List<Map<String, Object>> queryIncomeItemsById(Map<String, Object> params) throws Exception;

    public void editIncomesByDeleteFlag(Map<String, Object> params) throws Exception;

    public void editIncomItemById(List<Map<String, Object>> entitys) throws Exception;

    public void deleteIncomItemById(Map<String, Object> params) throws Exception;
}
