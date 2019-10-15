package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface PurchasePutDao {

	public List<Map<String, Object>> queryPurchasePutToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

}
