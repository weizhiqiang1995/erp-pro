package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/10/6 15:43
 */
public interface AccountDao {
    public List<Map<String, Object>> queryAccountByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public Map<String, Object> queryAccountByName(Map<String, Object> params) throws Exception;

    public void insertAccount(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryAccountById(Map<String, Object> params) throws Exception;

    public void editAccountByDeleteFlag(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryAccountByIdAndName(Map<String, Object> params) throws Exception;

    public void editAccountById(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryAccountByIdAndIsDeafault(Map<String, Object> params) throws Exception;

    public void editAccountByIsDefault(Map<String, Object> params) throws Exception;

    public void editAccountByIdAndIsDefault(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryAccountByIdAndInfo(Map<String, Object> params) throws Exception;

    public List<Map<String, Object>> queryAccountStreamById(Map<String, Object> params, PageBounds pageBounds) throws Exception;
}
