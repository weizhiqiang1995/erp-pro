package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/9/16 21:23
 */
public interface CustomerService {
    public void queryCustomerByList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertCustomer(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editCustomerById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editCustomerByEnabled(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editCustomerByNotEnabled(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryCustomerByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryCustomerListToSelect(InputObject inputObject, OutputObject outputObject) throws Exception;
}
