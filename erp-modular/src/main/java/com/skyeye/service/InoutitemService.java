package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @Author: 奈何繁华如云烟
 * @Description: TODO
 * @Date: 2019/10/6 15:42
 */
public interface InoutitemService {

    public void queryInoutitemByList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertInoutitem(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryInoutitemById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteInoutitemById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editInoutitemById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryInoutitemByIdAndInfo(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryInoutitemISExpenditureToSelect(InputObject inputObject, OutputObject outputObject) throws Exception;
}
