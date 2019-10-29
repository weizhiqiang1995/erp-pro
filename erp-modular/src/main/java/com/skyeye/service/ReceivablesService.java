package com.skyeye.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

/**
 * @Author: 卫志强
 * @Description: TODO
 * @Date: 2019/10/20 10:22
 */
public interface ReceivablesService {
	
    public void queryReceivablesByList(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void insertReceivables(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryReceivablesToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void editReceivablesById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void deleteReceivablesById(InputObject inputObject, OutputObject outputObject) throws Exception;

    public void queryReceivablesByDetail(InputObject inputObject, OutputObject outputObject) throws Exception;

	public void queryMationToExcel(InputObject inputObject, OutputObject outputObject) throws Exception;
    
}
