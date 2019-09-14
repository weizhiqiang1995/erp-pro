package com.skyeye.dao;

import java.util.List;
import java.util.Map;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/9/14 10:45
 */
public interface StoreHouseDao {
    /**
     * 查询仓库信息
     * @param params
     * @return
     */
    List<Map<String, Object>> queryStoreHouseByList(Map<String, Object> params) throws Exception;

    /**
     * 查询名称为xxx的仓库信息
     * @param params
     * @return
     */
    Map<String, Object> queryStoreHouseByName(Map<String, Object> params) throws Exception;

    /**
     * 添加仓库信息
     * @param params
     */
    void insertStoreHouse(Map<String, Object> params) throws Exception;

    /**
     * 查询单个仓库信息
     * @param params
     * @return
     */
    Map<String, Object> queryStoreHouseById(Map<String, Object> params) throws Exception;

    /**
     * 删除仓库信息
     * @param params
     */
    void deleteStoreHouseById(Map<String, Object> params) throws Exception;

    /**
     * 编辑仓库信息
     * @param params
     */
    void editStoreHouseById(Map<String, Object> params) throws Exception;
}
