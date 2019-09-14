package com.skyeye.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/9/14 10:45
 */
public interface StoreHouseDao {
    public List<Map<String, Object>> queryStoreHouseByList(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryStoreHouseByName(Map<String, Object> params) throws Exception;

    public void insertStoreHouse(Map<String, Object> params) throws Exception;

    public Map<String, Object> queryStoreHouseById(Map<String, Object> params) throws Exception;

    public void deleteStoreHouseById(Map<String, Object> params) throws Exception;

    public void editStoreHouseById(Map<String, Object> params) throws Exception;
}
