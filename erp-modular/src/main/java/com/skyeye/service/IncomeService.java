package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:22
 */
public interface IncomeService {
    public void queryIncomeByList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertIncome(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryIncomeToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteIncomeById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryIncomeByDetail(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;
}
