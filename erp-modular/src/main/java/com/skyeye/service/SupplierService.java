package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/9/16 21:24
 */
public interface SupplierService {
    public void querySupplierByList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertSupplier(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void querySupplierById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteSupplierById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editSupplierById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editSupplierByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editSupplierByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception;
}
