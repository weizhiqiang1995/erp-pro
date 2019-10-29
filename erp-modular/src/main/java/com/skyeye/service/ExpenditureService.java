package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:22
 */
public interface ExpenditureService {
	
    public void queryExpenditureByList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertExpenditure(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryExpenditureToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editExpenditureById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteExpenditureById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryExpenditureByDetail(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;
    
}
