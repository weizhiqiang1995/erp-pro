/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.service;

import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;

public interface SysWorkPlanService {

    void querySysWorkPlanList(InputObject inputObject, OutputObject outputObject);

    void insertSysWorkPlanISPeople(InputObject inputObject, OutputObject outputObject);

    void insertSysWorkPlanISDepartMent(InputObject inputObject, OutputObject outputObject);

    void insertSysWorkPlanISCompany(InputObject inputObject, OutputObject outputObject);

    void deleteSysWorkPlanTimingById(InputObject inputObject, OutputObject outputObject);

    void deleteSysWorkPlanById(InputObject inputObject, OutputObject outputObject);

    void querySysWorkPlanToEditById(InputObject inputObject, OutputObject outputObject);

    void editSysWorkPlanISPeople(InputObject inputObject, OutputObject outputObject);

    void editSysWorkPlanISDepartMentOrCompany(InputObject inputObject, OutputObject outputObject);

    void editSysWorkPlanTimingSend(InputObject inputObject, OutputObject outputObject);

    void querySysWorkPlanDetailsById(InputObject inputObject, OutputObject outputObject);

    void queryMySysWorkPlanListByUserId(InputObject inputObject, OutputObject outputObject);

    void subEditWorkPlanStateById(InputObject inputObject, OutputObject outputObject);

    void queryMyCreateSysWorkPlanList(InputObject inputObject, OutputObject outputObject);

    void queryAllSysWorkPlanList(InputObject inputObject, OutputObject outputObject);

    void queryMyBranchSysWorkPlanList(InputObject inputObject, OutputObject outputObject);
}
