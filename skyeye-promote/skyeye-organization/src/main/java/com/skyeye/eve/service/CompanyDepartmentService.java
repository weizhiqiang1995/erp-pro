/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface CompanyDepartmentService {

    void queryCompanyDepartmentList(InputObject inputObject, OutputObject outputObject) throws Exception;

    void insertCompanyDepartmentMation(InputObject inputObject, OutputObject outputObject) throws Exception;

    void deleteCompanyDepartmentMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryCompanyDepartmentMationToEditById(InputObject inputObject, OutputObject outputObject) throws Exception;

    void editCompanyDepartmentMationById(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryCompanyDepartmentListTreeByCompanyId(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryCompanyDepartmentListByCompanyIdToSelect(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryCompanyDepartmentListToChoose(InputObject inputObject, OutputObject outputObject) throws Exception;

    void queryCompanyDepartmentListByIds(InputObject inputObject, OutputObject outputObject) throws Exception;

    /**
     * 根据部门id获取部门加班结算方式
     *
     * @param departmentId 部门id
     * @return 部门加班结算方式
     * @throws Exception
     */
    int getDepartmentOvertimeSettlementType(String departmentId) throws Exception;

    void queryDepartmentListByCurrentUserBelong(InputObject inputObject, OutputObject outputObject) throws Exception;
}
