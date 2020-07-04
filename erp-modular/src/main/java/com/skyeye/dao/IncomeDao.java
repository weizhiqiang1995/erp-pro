/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:23
 */
public interface IncomeDao {
    public List<Map<String, Object>> queryIncomeByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public int insertIncome(Map<String, Object> params) throws Exception;

    public int insertIncomeItem(List<Map<String, Object>> entitys) throws Exception;

    public Map<String, Object> queryIncomeToEditById(Map<String, Object> params) throws Exception;

    public int editIncomeById(Map<String, Object> params) throws Exception;

    public int editIncomeByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryIncomeDetailById(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryIncomeItemsDetailById(Map<String, Object> bean) throws Exception;

    public List<Map<String, Object>> queryIncomeItemsToEditById(Map<String, Object> params) throws Exception;

    public int editIncomeItemsByDeleteFlag(Map<String, Object> params) throws Exception;

    public int deleteIncomeItemById(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryUserInfoById(Map<String, Object> bean) throws Exception;

	public List<Map<String, Object>> queryMationToExcel(Map<String, Object> params) throws Exception;
}
