package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/9/14 10:44
 */
public interface StoreHouseService {
    public void queryStoreHouseByList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertStoreHouse(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editStoreHouseById(InputObject inputObject, OutputObject outputObject) throws Exception;
}
