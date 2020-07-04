/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import java.util.List;
import java.util.Map;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/9/16 21:22
 */
public interface CustomerDao {

    public List<Map<String, Object>> queryCustomerByList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

    public Map<String, Object> queryCustomerByUserIdAndCustomer(Map<String, Object> params) throws Exception;

    public void insertCustomer(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryCustomerById(Map<String, Object> params) throws Exception;

    public int editCustomerByDeleteFlag(Map<String, Object> params) throws Exception;

    public int editCustomerById(Map<String, Object> params) throws Exception;

    public int editCustomerByEnabled(Map<String, Object> params) throws Exception;

    public int editCustomerByNotEnabled(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryCustomerByIdAndName(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryCustomerByEnabled(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryCustomerByIdAndInfo(Map<String, Object> params) throws Exception;

	public List<Map<String, Object>> queryCustomerListToSelect(Map<String, Object> params) throws Exception;
}
