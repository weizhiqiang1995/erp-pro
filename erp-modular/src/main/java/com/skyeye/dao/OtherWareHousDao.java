package com.skyeye.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public interface OtherWareHousDao {

	public List<Map<String, Object>> queryOtherWareHousToList(Map<String, Object> params, PageBounds pageBounds) throws Exception;

}
